 /**
 * Created by pqanh09 on 9/6/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.viewall').config(routeFunction);

    routeFunction.$inject = ['$routeProvider'];

    function routeFunction($routeProvider){
        var routeManager = '/music-manager/viewall';
        var configManger = {
            template: '<div viewall></div>'
        };
        $routeProvider.when(routeManager, configManger)
            .when(routeManager + '/:route*', configManger);
    }
})();