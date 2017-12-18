/**
 * Created by pqanh09 on 9/7/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone.view').directive('sampleoneView', directiveFunction);

    directiveFunction.$inject = [];

    function directiveFunction(){
        return {
            templateUrl : 'app/static/scripts/music-manager/sampleone/view/view.html',
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