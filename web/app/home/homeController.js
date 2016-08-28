/**
 * Created by adam on 6/4/16.
 */
app.controller('HomeController', function ($scope, uiGmapIsReady, $timeout, $log, AgentsService, AgentsApi) {
//app.controller('HomeController',['$scope', 'AgentsService','uiGmapIsReady' , function ($scope, AgentsService, uiGmapIsReady) {
    var vm = this;
    
    vm.msg = 'home controller msg';
    vm.stationaryAgents =[];
    vm.carAgents = [];
    vm.clientsCoordinants = [];
    function setMarkers()
    {

        vm.map.markers.length= []; //reset the array
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
    uiGmapIsReady.promise().then(function(maps) {});

    vm.addStation = function(station)
    {
        AgentsApi.AddStation(station, function (response) {
            $log.info(response);
            alert('Station added!');
        }, onError);
    };

    vm.addClient = function (client) {
        AgentsApi.AddClient(client).success(function (){
            vm.getData();
            alert("Client car request added.");
        }).error(function(){
            vm.errorMsg = "Sth went wrong :(";
        });
    };

    function onError(reason) {
        $log.error(reason);
    }

    $scope.$watch(function (scope) {
        return AgentsService.cars;
    }, function (newVal, oldVal) {
        vm.carAgents = AgentsService.cars;
        setMarkers();
    }, true);

    $scope.$watch(function (scope) {
        return AgentsService.stations;
    }, function (newVal, oldVal) {
        vm.stationaryAgents = AgentsService.stations;
        setMarkers();
    }, true);
});

function marker(id, x, y, icon, name) {
    this.id = id;
    this.coords = {"latitude": x, "longitude": y};
    this.options = {
        "icon": icon,
        "labelContent": name
    }
}