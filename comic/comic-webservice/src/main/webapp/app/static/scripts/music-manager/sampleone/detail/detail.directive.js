/**
 * Created by pqanh09 on 9/7/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone.detail').directive('sampleoneDetail', directiveFunction);

    directiveFunction.$inject = ['musicConstant'];

    function directiveFunction(musicConstant){
        return {
            templateUrl : musicConstant.templateUrl.musicManager.sampleone.detail,
            restrict: 'EA',
            controller: 'musicSampleoneDetailController',
            controllerAs: 'vmDetail',
            scope: {},
            bindToController: {
              listObject: '=',
              listCol: '=',
              listSelected: '='
            }
        }
    }
})();