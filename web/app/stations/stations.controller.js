/**
 * Created by adam on 8/26/16.
 */
app.controller('StationsController', StationsController);

function StationsController($scope, $log, AgentsService) {
    var vm = this;


    vm.stations = [];


    $scope.$watch(function (scope) {
        return AgentsService.stations;
    }, function (newVal, oldVal) {
        vm.stations = AgentsService.stations;
        $log.info(vm.stations);
    }, true);
}