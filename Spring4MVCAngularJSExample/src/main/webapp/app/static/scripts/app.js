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

    HomeRoute.$inject = ['$routeProvider', 'musicConstant'];

    function HomeRoute($routeProvider, musicConstant) {
        $routeProvider
            .otherwise({
                templateUrl: musicConstant.templateUrl.home.url,
                controller: "homeController"
            });

    }

})();