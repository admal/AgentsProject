/**
 * Created by adam on 8/26/16.
 */
app.service("AgentsService", function($interval, AgentsApi, $log){
    var vm = this;
    var loadCount = 0;
    vm.cars = [];
    vm.stations = [];
    var geocoder = new google.maps.Geocoder();

    vm.getAgentsData = function () {
        AgentsApi.GetCars(function(response){
            vm.cars = response.data;
            vm.cars.forEach(function(curr){
                if(curr.destination !== null && angular.isDefined(curr.destination)) {
                    geocoder.geocode({location: {"lat": curr.destination.x, "lng": curr.destination.y}}, function(result, status){
                        if(result !== null)
                            curr.destinationAddress = result[0].formatted_address;
                    });
                }
                geocoder.geocode({location: {"lat": curr.position.x, "lng": curr.position.y}}, function(result, status){
                    if(result !== null)
                        curr.positionAddress = result[0].formatted_address;
                });
            });

            $log.info('cars updated');
        }, onApiError);

        AgentsApi.GetStationaryAgents(function(response){
            vm.stations = response.data;
            vm.stations.forEach(function(curr){
                if(loadCount === 0) { //only once get information about stations addresses
                    geocoder.geocode({location: {"lat": curr.position.x, "lng": curr.position.y}}, function(result, status){
                        if(result !== null)
                            curr.address = result[0].formatted_address;
                    });
                }

            });
            $log.info('stations updated');
        }, onApiError);
    };

    var updateWatch = $interval(vm.getAgentsData, 10000); //every 10 sec update

    function onApiError(reason) {
        $log.error(reason);
    }

    vm.getAgentsData();
});