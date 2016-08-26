/**
 * Created by adam on 6/4/16.
 */
app.config(['uiGmapGoogleMapApiProvider', '$stateProvider', '$urlRouterProvider',function( uiGmapGoogleMapApiProvider, $stateProvider, $urlRouterProvider){
    uiGmapGoogleMapApiProvider.configure({
        key: 'AIzaSyBQsJAcRshc4kGL6FUJLpxJCjBqJvBUQIA',
        v: '3.20', //defaults to latest 3.X anyhow
        libraries: 'weather,geometry,visualization, places, directions'
    });

    $urlRouterProvider.otherwise('/cars');
    $stateProvider
        .state('cars',{
            url: '/cars',
            templateUrl: 'app/cars/cars.html',
            controller: 'CarsController',
            controllerAs: 'cars'
        });
    $stateProvider
        .state('stations',{
            url: '/stations',
            templateUrl: 'app/stations/stations.html',
            controller: 'StationsController',
            controllerAs: 'stations'
        });
    $stateProvider
        .state('requests',{
            url: '/requests',
            templateUrl: 'app/requests/requests.html',
            controller: 'RequestsController',
            controllerAs: 'requests'
        });
}]);