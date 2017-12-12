(function () {
    'use strict';

    //module
    angular.module('myApp', ['ngRoute', 'music.manager']);

    //controller
    angular.module('myApp').controller('homeController', homeController);

    homeController.$inject = [];

    function homeController() {
        var vm = this;
    }

    //routing
    angular.module('myApp').config(HomeRoute);

    HomeRoute.$inject = ['$routeProvider'];

    function HomeRoute($routeProvider) {
        $routeProvider
            .otherwise({
                templateUrl: 'static/scripts/home.html',
                controller: "homeController"
            });

    }

})();