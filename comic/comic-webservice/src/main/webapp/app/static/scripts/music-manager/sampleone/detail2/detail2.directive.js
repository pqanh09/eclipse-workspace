/**
 * Created by pqanh09 on 9/7/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone.detail2').directive('sampleoneDetaila', directiveFunction);

    directiveFunction.$inject = ['musicConstant'];

    function directiveFunction(musicConstant){
        return {
            templateUrl : musicConstant.templateUrl.musicManager.sampleone.detail2,
            restrict: 'EA',
            controller: 'musicSampleoneDetail2Controller',
            controllerAs: 'vmDetail2',
            scope: {},
            bindToController: {
              listObject: '=',
              listCol: '=',
              listSelected: '='
            }
        }
    }
})();