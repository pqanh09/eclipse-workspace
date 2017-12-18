/**
 * Created by pqanh09 on 9/6/16.
 */

(function(){
    'use strict';

    angular.module('music.manager.song').directive('viewall', directiveFunction);

    directiveFunction.$inject = [];

    function directiveFunction(){
        return {
            templateUrl : 'app/static/scripts/music-manager/viewall/viewall.html',
            restrict: 'EA',
            controller: 'musicViewallController',
            controllerAs: 'vm',
            scope: {},
            bindToController: {}
        }
    }
})();