/**
 * Created by adam on 8/26/16.
 */
app.service("AgentsService", function($interval, AgentsApi, $log){
    var vm = this;

    vm.cars = [];
    vm.stations = [];

    vm.getAgentsData = function () {
        AgentsApi.GetCars(function(response){
            vm.cars = response.data;
            $log.info('cars updated');
        }, onApiError);

        AgentsApi.GetStationaryAgents(function(response){
            vm.stations = response.data;
            $log.info('stations updated');
        }, onApiError);
    };

    var updateWatch = $interval(vm.getAgentsData, 10000); //every 10 sec update

    function onApiError(reason) {
        $log.error(reason);
    }

    vm.getAgentsData();
});