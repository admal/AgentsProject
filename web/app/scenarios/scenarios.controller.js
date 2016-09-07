/**
 * Created by adam on 9/7/16.
 */
app.controller('ScenariosController', ScenariosController);

function ScenariosController($interval, $log, AgentsApi, AgentsService) {
    var vm = this;

    vm.scenarios = [];


    function getScenarios() {
        AgentsApi.getScenarios(function (response) {
            $log.info('scenarios', response.data);
            vm.scenarios = response.data;
        }, function (response) {
            $log.error('Api error', response);
        });
    }

    vm.startScenario = function (index) {
        $log.info(index);
        AgentsApi.startScenario(index, function (response) {
            getScenarios();
            AgentsService.getAgentsData();
            alert('Scenario started!');
        }, function (response) {
            alert('Error');
            $log.error('starting scenario', response);
        });
    };

    vm.stopScenario = function (index) {
        $log.info(index);
        AgentsApi.stopScenario(index, function (response) {
            getScenarios();
            AgentsService.getAgentsData();
            alert('Scenario stopped!');
        }, function (response) {
            alert('Error');
            $log.error('stopping scenario', response);
        });
    };

    $interval(function () {
        getScenarios();
    }, 5000);

    getScenarios();
}