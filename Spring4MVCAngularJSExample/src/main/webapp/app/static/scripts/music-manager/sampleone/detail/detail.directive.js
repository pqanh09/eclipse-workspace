/**
 * Created by pqanh09 on 9/7/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone.detail').directive('sampleoneDetail', directiveFunction);

    directiveFunction.$inject = [];

    function directiveFunction(){
        return {
            templateUrl : 'app/static/scripts/music-manager/sampleone/detail/detail.html',
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