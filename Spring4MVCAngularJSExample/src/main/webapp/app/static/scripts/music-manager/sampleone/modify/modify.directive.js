/**
 * Created by pqanh09 on 9/7/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone.modify').directive('sampleoneModify', directiveFunction);

    directiveFunction.$inject = [];

    function directiveFunction(){
        return {
            templateUrl : 'app/static/scripts/music-manager/sampleone/modify/modify.html',
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