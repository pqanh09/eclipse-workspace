/**
 * Created by pqanh09 on 9/7/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone.view').directive('sampleoneView', directiveFunction);

    directiveFunction.$inject = ['musicConstant'];

    function directiveFunction(musicConstant){
        return {
            templateUrl : musicConstant.templateUrl.musicManager.sampleone.view,
            restrict: 'EA',
            controller: 'musicSampleoneViewController',
            controllerAs: 'vmView',
            scope: {},
            bindToController: {
              listObject: '=',
              listCol: '=',
              listSelected: '='
            }
        }
    }
})();