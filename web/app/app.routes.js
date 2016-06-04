/**
 * Created by adam on 6/4/16.
 */
app.config(['$routeProvider',function($routeProvider){
    $routeProvider.
    when('/',{
        templateUrl: 'app/home/homeView.html',
        controller: 'HomeController'
    });
}]);