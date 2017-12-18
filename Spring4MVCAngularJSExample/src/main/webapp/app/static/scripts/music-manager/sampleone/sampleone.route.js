 /**
 * Created by pqanh09 on 9/6/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone').config(routeFunction);

    routeFunction.$inject = ['$routeProvider'];

    function routeFunction($routeProvider){
        var routeManager = '/music-manager/usdt';
        var configManger = {
            template: '<div sampleone></div>'
        };
        $routeProvider.when(routeManager, configManger)
            .when(routeManager + '/:route*', configManger);
    }
})();