/**
 * Created by adam on 6/5/16.
 */
app.service("AgentsService", ["$http", '$log',function($http, $log ){
    var vm = this;
    var baseUrl = 'http://localhost:8080/';

    function getResource(url, success, error) {
        $http({
            method: 'GET',
            url: baseUrl + url
        }).then(success, error);
    }

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

    this.GetStationaryAgents = function(success, error) {
        getResource('api/stations', success, error);
    };

    this.GetCars = function(success, error){
        getResource('api/cars', success, error);
    };

    this.AddStation = function(station, success, error){
        var data = $.param({
            id: station.id,
            x: station.x,
            y: station.y
        });
        postResource('api/addstation', data, success, error);
    };

    this.AddClient = function (client) {
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
}]);