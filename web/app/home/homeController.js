/**
 * Created by adam on 6/4/16.
 */
app.controller('HomeController',['$scope', 'AgentsService','uiGmapGoogleMapApi' , function ($scope, AgentsService, uiGmapGoogleMapApi) {
    $scope.msg = 'home controller msg';
    $scope.stationaryAgents =[];
    $scope.carAgents = [];


    $scope.getData = function(){
        AgentsService.GetStationaryAgents().success(function(stations){
            $scope.stationaryAgents = stations;
        });

        AgentsService.GetCars().success(function(cars){
            $scope.carAgents = cars;
        });
    };

    // uiGmapGoogleMapApi is a promise.
    // The "then" callback function provides the google.maps object.
    uiGmapGoogleMapApi.then(function(maps) {

    });
    $scope.map = { center: { latitude: 45, longitude: -73 }, zoom: 8 };
}]);