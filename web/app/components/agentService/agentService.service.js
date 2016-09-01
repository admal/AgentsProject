/**
 * Created by adam on 8/26/16.
 */
app.service("AgentsService", function($interval, AgentsApi, $log){
    var vm = this;

    vm.cars = [];
    vm.stations = [];
    var geocoder = new google.maps.Geocoder();

    vm.getAgentsData = function () {
        AgentsApi.GetCars(function(response){
            vm.cars = response.data;
            $log.info('cars updated');
        }, onApiError);

        AgentsApi.GetStationaryAgents(function(response){
            vm.stations = response.data;
            // vm.stations.forEach(function(curr){
            //     geocoder.geocode({location: {"lat": curr.position.x, "lng": curr.position.y}}, function(result, status){
            //         curr.address = result[0].formatted_address;
            //     });
            // });
            $log.info('stations updated');
        }, onApiError);
    };

    var updateWatch = $interval(vm.getAgentsData, 10000); //every 10 sec update

    function onApiError(reason) {
        $log.error(reason);
    }

    vm.getAgentsData();
});