app.directive('mainNavbar', mainNavbar);

function mainNavbar() {
    var directive = {
        restrict: 'E',
        templateUrl: 'app/components/mainNavbar/mainNavbar.html',
        bindToController:{

        },
        controller: 'MainNavbarController',
        controllerAs: 'mainNavbar'
    };

    return directive;
}