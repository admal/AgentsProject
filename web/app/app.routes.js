/**
 * Created by adam on 6/4/16.
 */
app.config(['$routeProvider','uiGmapGoogleMapApiProvider',function($routeProvider, uiGmapGoogleMapApiProvider){
    uiGmapGoogleMapApiProvider.configure({
        key: 'AIzaSyBQsJAcRshc4kGL6FUJLpxJCjBqJvBUQIA',
        v: '3.20', //defaults to latest 3.X anyhow
        libraries: 'weather,geometry,visualization'
    });
    $routeProvider.
    when('/',{
        templateUrl: 'app/home/homeView.html',
        controller: 'HomeController'
    });
}]);