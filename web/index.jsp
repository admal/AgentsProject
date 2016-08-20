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
        .angular-google-map-container { height: 800px; }
    </style>
</head>
<body ng-app="app">

<div class="container-fluid">
    <div ui-view>
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
    <!--COMMON-->

    <%--SERVICES--%>
    <script src="app/home/homeServices.js"></script>
    <!--CONTROLLERS-->
    <script src="app/home/homeController.js"></script>
</body>
</html>