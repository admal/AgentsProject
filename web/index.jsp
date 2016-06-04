<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Autonomous electric car system</title>

    <link rel="stylesheet" href="assets/css/bootstrap.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-theme.css">
</head>
<body ng-app="app">

<div class="container">
    <div ng-view>
    </div>
</div>

    <!--LIBRARIES-->
    <script src="assets/js/jquery-2.2.4.js"></script>
    <script src="assets/js/bootstrap.js"></script>
    <script src="assets/js/angular.js"> </script>
    <script src="assets/js/angular-route.js"></script>
    <!--APP-->
    <script src="app/app.module.js"></script>
    <script src="app/app.routes.js"></script>
    <!--COMMON-->

    <!--CONTROLLERS-->
    <script src="app/home/homeController.js"></script>
</body>
</html>