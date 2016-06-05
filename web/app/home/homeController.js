/**
 * Created by adam on 6/4/16.
 */
app.controller('HomeController',['$scope', 'AgentsService', function ($scope, AgentsService) {
    $scope.msg = 'home controller msg';
    $scope.agents =  AgentsService.GetAgents();
}]);