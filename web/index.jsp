<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Autonomous electric car system</title>

    <link rel="stylesheet" href="assets/css/bootstrap.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-theme.css">
    <script type="text/javascript"
            src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <style>
        .angular-google-map-container { height: 45vh; border: solid #000000 thin;  }
        .navbar-inverse{border-radius: 0px;}
    </style>
</head>
<body ng-app="app">

<div class="container-fluid" style="padding: 0;">
    <div class="row">
        <div ng-controller="HomeController as home">
            <div class="col-lg-12">
                <ui-gmap-google-map center='home.map.center' zoom='home.map.zoom' control="home.map.control">
                    <ui-gmap-layer type="TrafficLayer"></ui-gmap-layer>
                    <ui-gmap-marker ng-repeat="m in home.map.markers" coords="m.coords" idkey="m.id"  options="m.options">
                    </ui-gmap-marker>
                </ui-gmap-google-map>
            </div>
        </div>
    </div>
    <main-navbar></main-navbar>
    <div class="container-fluid">
        <div ui-view></div>
    </div>
</div>
    <!--LIBRARIES-->
    <script src="assets/js/jquery-2.2.4.js"></script>
    <script src="assets/js/bootstrap.js"></script>
    <script src="assets/js/lodash.js"></script>
    <script src="assets/js/angular.js"> </script>
    <script src="assets/js/angular-ui-router.js"></script>
    <script src="assets/js/angular-simple-logger.js"></script>
    <script src="assets/js/angular-google-maps.js"></script>
    <!--APP-->
    <script src="app/app.module.js"></script>
    <script src="app/app.routes.js"></script>
    <!--COMPONENTS-->
    <script src="app/components/mainNavbar/mainNavbar.directive.controller.js"></script>
    <script src="app/components/mainNavbar/mainNavbar.directive.js"></script>
    <script src="app/components/agentsApi/agentsApi.service.js"></script>
    <script src="app/components/agentService/agentService.service.js"></script>
    <!--CONTROLLERS-->
    <script src="app/home/homeController.js"></script>
    <script src="app/requests/requests.controller.js"></script>
    <script src="app/cars/cars.controller.js"></script>
    <script src="app/stations/stations.controller.js"></script>
</body>
</html>