/**
 * Created by adam on 8/26/16.
 */
app.service("AgentsApi", function($http ) {
    var vm = this;
    var baseUrl = 'http://localhost:1337/';

    /**
     * Sends GET request to server with given url
     * @param url
     * @param success callback function on success
     * @param error callback function on error
     */
    function getResource(url, success, error) {
        $http({
            method: 'GET',
            url: baseUrl + url
        }).then(success, error);
    }

    /**
     * Sends POST request to server with given url and data
     * @param url
     * @param postData data to send
     * @param success callback function on success
     * @param error callback function on error
     */
    function postResource(url, postData, success, error) {
        $http({
            method: 'POST',
            url: baseUrl + url,
            headers: {
                'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
            },
            data: postData
        }).then(success, error);
    }

    vm.GetStationaryAgents = function(success, error) {
        getResource('api/stations', success, error);
    };

    vm.GetCars = function(success, error){
        getResource('api/cars', success, error);
    };

    vm.AddStation = function(station, success, error){
        var data = $.param({
            x: station.x,
            y: station.y
        });
        postResource('api/addstation', data, success, error);
    };

    vm.addClient = function (client) {
        var data = $.param({
            x: client.x,
            y: client.y
        });
        var config = {
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        };

        return $http.post('api/addclient', data, config);
    };
    
    vm.addCar = function(success, error) {
        postResource('api/cars', success, error);
    };

    vm.startAgentPlatform = function (success, error) {
        getResource('api/start_simulation', success, error);
    };

    vm.getScenarios = function (success, error) {
        getResource('api/scenario', success, error);
    };

    vm.startScenario = function (index, success, error) {
        postResource('api/scenario', $.param({index: index}), success, error);
    };

    vm.stopScenario = function (index, success, error) {
        postResource('api/scenario/stop', $.param({index: index}), success, error);
    }

});