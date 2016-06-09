/**
 * Created by adam on 6/4/16.
 */
app.controller('HomeController',['$scope', 'AgentsService', function ($scope, AgentsService) {
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

}]);