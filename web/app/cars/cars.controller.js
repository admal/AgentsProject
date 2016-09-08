/**
 * Created by adam on 8/26/16.
 */
app.controller('CarsController', CarsController);

function CarsController($scope, $log, AgentsService) {
    var vm = this;

    vm.cars = [];


    $scope.$watch(function (scope) {
        return AgentsService.cars;
    }, function (newVal, oldVal) {
        vm.cars = AgentsService.cars;
        //$log.info(vm.cars);
    }, true);
}