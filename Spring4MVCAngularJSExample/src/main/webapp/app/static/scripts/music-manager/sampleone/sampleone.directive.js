/**
 * Created by pqanh09 on 9/6/16.
 */

(function(){
    'use strict';

    angular.module('music.manager.sampleone').directive('sampleone', directiveFunction);

    directiveFunction.$inject = [];

    function directiveFunction(){
        return {
            templateUrl : 'app/static/scripts/music-manager/sampleone/sampleone.html',
            restrict: 'EA',
            controller: 'musicSampleoneController',
            controllerAs: 'vm',
            scope: {},
            bindToController: {}
        }
    }
})();