/**
 * Created by pqanh09 on 9/6/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.admin').config(routeFunction);

    routeFunction.$inject = ['$routeProvider'];

    function routeFunction($routeProvider){
        var routeManager = '/music-manager/admin';
        var configManger = {
            template: '<admin></admin>'
        };
        $routeProvider.when(routeManager, configManger)
            .when(routeManager + '/:route*', configManger);
    }
})();
