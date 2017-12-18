/**
 * Created by pqanh09 on 9/6/16.
 */

(function(){
    'use strict';

    angular.module('music.manager.sampleone').directive('sampleone', directiveFunction);

    directiveFunction.$inject = ['musicConstant'];

    function directiveFunction(musicConstant){
        return {
            templateUrl : musicConstant.templateUrl.musicManager.sampleone.url,
            restrict: 'EA',
            controller: 'musicSampleoneController',
            controllerAs: 'vm',
            scope: {},
            bindToController: {}
        }
    }
})();