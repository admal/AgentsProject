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
    $scope.map = { center: { latitude: 52, longitude: 21 }, zoom: 10 };


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
    }
    
}]);