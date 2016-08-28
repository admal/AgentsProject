app.controller('MainNavbarController', MainNavbarController);

function MainNavbarController($state, $log, AgentsApi) {
    var vm = this;

    vm.isStateSelected = function (currState) {
        var c = $state.includes(currState);
        return $state.includes(currState);
    };

    vm.startPlatform = function(){
        AgentsApi.startAgentPlatform(function(){
            $log.info('Platform started');
        }, function (reason) {
            $log.error('Platform was not started');
            $log.error(reason);
        });
    }
}