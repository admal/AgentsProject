/**
 * Created by adam on 6/5/16.
 */
app.service("AgentsService", ["$http",function($http){
    
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
}]);