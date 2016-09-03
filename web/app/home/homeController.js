/**
 * Created by adam on 6/4/16.
 */
app.controller('HomeController', function ($scope, uiGmapIsReady, $timeout, $log, AgentsService, AgentsApi, ngDialog) {
//app.controller('HomeController',['$scope', 'AgentsService','uiGmapIsReady' , function ($scope, AgentsService, uiGmapIsReady) {
    var vm = this;
    
    vm.msg = 'home controller msg';
    vm.stationaryAgents =[];
    vm.carAgents = [];
    vm.clientsCoordinants = [];
    function setMarkers()
    {
        vm.map.markers = []; //reset the array
        if(vm.stationaryAgents !== null){
            vm.stationaryAgents.forEach(function(curr, index, arr){
  
                vm.map.markers.push(new marker(index,curr.position.x, curr.position.y, "assets/images/ChargingStation.png", curr.name));
            });
        }
        if(vm.carAgents !== null){
            vm.carAgents.forEach(function (curr, index, arr){
                vm.map.markers.push(new marker(index+vm.stationaryAgents.length,curr.position.x, curr.position.y, "assets/images/Car.png", curr.name));
                if(curr.destination !== null && angular.isDefined(curr.destination)) {
                    vm.map.markers.push(new marker(100 + index, curr.destination.x, curr.destination.y, "assets/images/Destination.png", curr.name));
                }
            });
            //vm.calcRoutes();
            //$log.info('markers', vm.map.markers);
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

    vm.addStationClick = function () {
        ngDialog.open({
            template: 'app/home/addStationModal.html',
            className: 'ngdialog-theme-default',
            controller: 'StationModalController',
            controllerAs: 'stationModal',
        });
    };
    vm.addStation = function(station)
    {
        AgentsApi.AddStation(station, function (response) {
            $log.info(response);
            alert('Station added!');
        }, onError);
    };

    vm.addClientClick = function() {
        ngDialog.open({
            template: 'app/home/addClientModal.html',
            className: 'ngdialog-theme-default',
            controller: 'ClientModalController',
            controllerAs: 'clientModal',
        });
    };

    vm.addClient = function (client) {
        AgentsApi.AddClient(client).success(function (){
            vm.getData();
            alert("Client car request added.");
        }).error(function(){
            vm.errorMsg = "Sth went wrong :(";
        });
    };

    vm.addCar = function () {
        AgentsApi.addCar(function (response) {
            $log.info('car created');
        }, onError);
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


app.controller('StationModalController', function ($scope, $log, AgentsService, AgentsApi) {
    var vm = this;
    vm.addStation = function(station)
    {
        AgentsApi.AddStation(station, function (response) {
            $log.info(response);
            alert('Station added!');
            $scope.closeThisDialog();
        }, function (reason) {
            $log.error(reason);
            $scope.closeThisDialog();
        });
    };
});

app.controller('ClientModalController', function ($scope, $log, AgentsService, AgentsApi) {
    var vm = this;
    vm.addClient = function(client)
    {
        AgentsApi.addClient(client, function (response) {
            $log.info(response);
            alert('Client added!');
            $scope.closeThisDialog();
        }, function (reason) {
            $log.error(reason);
            $scope.closeThisDialog();
        });
    };
});