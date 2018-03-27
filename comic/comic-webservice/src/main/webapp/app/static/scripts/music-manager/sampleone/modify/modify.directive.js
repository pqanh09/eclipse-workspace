/**
 * Created by pqanh09 on 9/7/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone.modify').directive('sampleoneModify', directiveFunction);

    directiveFunction.$inject = ['musicConstant'];

    function directiveFunction(musicConstant){
        return {
            templateUrl : musicConstant.templateUrl.musicManager.sampleone.modify,
            restrict: 'EA',
            controller: 'musicSampleoneModifyController',
            controllerAs: 'vmModify',
            scope: {},
            bindToController: {
              listObject: '=',
              listCol: '=',
              listSelected: '='
            }
        }
    }
})();