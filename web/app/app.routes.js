/**
 * Created by adam on 6/4/16.
 */
app.config(['uiGmapGoogleMapApiProvider', '$stateProvider', '$urlRouterProvider',function( uiGmapGoogleMapApiProvider, $stateProvider, $urlRouterProvider){
    uiGmapGoogleMapApiProvider.configure({
        key: 'AIzaSyBQsJAcRshc4kGL6FUJLpxJCjBqJvBUQIA',
        v: '3.20', //defaults to latest 3.X anyhow
        libraries: 'weather,geometry,visualization, places, directions'
    });

    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state('home',{
            url: '/',
            templateUrl: 'app/home/homeView.html',
            controller: 'HomeController',
            controllerAs: 'home'
        });
}]);