/**
 * Created by adam on 6/5/16.
 */
app.service("AgentsService", ["$http", '$log',function($http, $log ){
    var vm = this;
    var baseUrl = 'localhost:8000/';

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


    this.GetStationaryAgents = function() {
        return $http.get("/api/stations").success(function (data) {
            this.stations = data;
        });
    };

    this.GetCars = function(){
       return $http.get("/api/cars").success(function (data) {
            this.cars = data;
        });
    };
    this.AddStation = function(station){
        var data = $.param({
            id: station.id,
            x: station.x,
            y: station.y
        });
        var config = {
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        };

        return $http.post('/api/addstation', data, config);
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

        return $http.post('/api/addclient', data, config);
    };
}]);