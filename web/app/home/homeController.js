/**
 * Created by adam on 6/4/16.
 */
app.controller('HomeController', function ($scope, AgentsService, uiGmapIsReady, $timeout, $log) {
//app.controller('HomeController',['$scope', 'AgentsService','uiGmapIsReady' , function ($scope, AgentsService, uiGmapIsReady) {
    var vm = this;
    
    vm.msg = 'home controller msg';
    vm.stationaryAgents =[];
    vm.carAgents = [];
    vm.clientsCoordinants = [];

   // $scope.markers = [];

    function setMarkers()
    {
        vm.map.markers.length=0; //reset the array
        if(vm.stationaryAgents !== null){
            vm.stationaryAgents.forEach(function(curr, index, arr){
                vm.map.markers.push(new marker(index,curr.position.x, curr.position.y, "assets/images/ChargingStation.png", curr.name));
            });
        }
        if(vm.carAgents !== null){
            vm.carAgents.forEach(function (curr, index, arr){
                vm.map.markers.push(new marker(index+vm.stationaryAgents.length,curr.position.x, curr.position.y, "assets/images/Car.png", curr.name));
            });
            vm.calcRoutes();
        }

    }

    vm.map = { center: { latitude: 52.25469, longitude: 21.03508}, zoom: 14, markers:[], control:{} };

    vm.calcRoutes = function () {
        var directionsDisplay = new google.maps.DirectionsRenderer();
        var directionsService = new google.maps.DirectionsService();

        directionsDisplay.setMap(vm.map.control.getGMap());
        vm.carAgents.forEach(function(curr, index, arr){
            if(curr.destination == null)
                return;
            var start = ""+curr.position.x+", " + curr.position.y;
            var end = ""+curr.destination.x + ", " + curr.destination.y;
            var request = {
                origin: start,
                destination: end,
                optimizeWaypoints: true,
                travelMode: google.maps.TravelMode.DRIVING
            };
            directionsService.route(request, function(response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                    vm.directions =[];// response.routes;
                    response.routes[0].overview_path.forEach(function(curr, index,arr){
                        vm.directions.push(curr);
                    });
                }
            });
        });

    };
    uiGmapIsReady.promise().then(function(maps) {
    });

    vm.getData = function(){
        AgentsService.GetStationaryAgents().success(function(stations){
            vm.stationaryAgents = stations;
            setMarkers();
        });

        AgentsService.GetCars().success(function(cars){
            vm.carAgents = cars;
            setMarkers();
        });

    };

    //not completely correct (should be put into directive I guess)
    vm.stationErrors = false;
    vm.addStation = function(station)
    {
        AgentsService.AddStation(station).success(function () {
            vm.getData();
            alert("Station added!");
        }).error(function () {
            vm.stationErrors = true;
            vm.errorMsg = "Sth went wrong!";
        });
    };

    vm.addClient = function (client) {
        AgentsService.AddClient(client).success(function (){
            vm.getData();
            alert("Client car request added.");
        }).error(function(){
            vm.errorMsg = "Sth went wrong :(";
        });
    };
});

function marker(id, x, y, icon, name) {
    this.id = id;
    this.coords = {"latitude": x, "longitude": y};
    this.options = {
        "icon": icon,
        "labelContent": name
    }
}