/**
 * Created by adam on 6/4/16.
 */
app.controller('HomeController',['$scope', 'AgentsService','uiGmapGoogleMapApi' , function ($scope, AgentsService, uiGmapGoogleMapApi) {
    $scope.msg = 'home controller msg';
    $scope.stationaryAgents =[];
    $scope.carAgents = [];
    $scope.clientsCoordinants = [];

   // $scope.markers = [];

    function setMarkers()
    {
        $scope.map.markers.length=0; //reset the array
        $scope.stationaryAgents.forEach(function(curr, index, arr){
            $scope.map.markers.push(new marker(index,curr.position.x, curr.position.y, "assets/images/ChargingStation.png", curr.name));
        });
        $scope.carAgents.forEach(function (curr, index, arr){
            $scope.map.markers.push(new marker(index+$scope.stationaryAgents.length,curr.position.x, curr.position.y, "assets/images/Car.png", curr.name));
        });
        // $scope.map.markers.length=0; //reset the array
        // $scope.stationaryAgents.forEach(function(curr, index, arr){
        //     $scope.map.markers.push({
        //         "id": index,
        //         "latitude": curr.position.x.toString(),
        //         "longitude": curr.position.y.toString(),
        //         "icon": "assets/images/ChargingStation.png"
        //     });
        // });
        // $scope.carAgents.forEach(function (curr, index, arr){
        //     $scope.markers.push(new marker(index+$scope.stationaryAgents.length,curr.position.x, curr.position.y, "assets/images/Car.png"));
        // });
    }

    $scope.getData = function(){
        AgentsService.GetStationaryAgents().success(function(stations){
            $scope.stationaryAgents = stations;
            setMarkers();
        });

        AgentsService.GetCars().success(function(cars){
            $scope.carAgents = cars;
            setMarkers();
        });

    };

    // uiGmapGoogleMapApi is a promise.
    // The "then" callback function provides the google.maps object.
    uiGmapGoogleMapApi.then(function(maps) {

    });
    $scope.map = { center: { latitude: 52.25469, longitude: 21.03508}, zoom: 14, markers:[] };


    //not completely correct (should be put into directive I guess)
    $scope.stationErrors = false;
    $scope.addStation = function(station)
    {
        AgentsService.AddStation(station).success(function () {
            alert("Station added!");
        }).error(function () {
            $scope.stationErrors = true;
            $scope.errorMsg = "Sth went wrong!";
        });
    };

    $scope.addClient = function (client) {
        AgentsService.AddClient(client).success(function (){
           alert("Client car request added.");
        }).error(function(){
            $scope.errorMsg = "Sth went wrong :(";
        });
    };
}]);

function marker(id, x, y, icon, name) {
    this.id = id;
    this.coords = {"latitude": x, "longitude": y};
    this.options = {
        "icon": icon,
        "labelContent": name,
    }
}