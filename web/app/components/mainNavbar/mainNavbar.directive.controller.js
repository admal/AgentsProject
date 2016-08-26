app.controller('MainNavbarController', MainNavbarController);

function MainNavbarController($state) {
    var vm = this;

    vm.isStateSelected = function (currState) {
        var c = $state.includes(currState);
        return $state.includes(currState);
    }
}