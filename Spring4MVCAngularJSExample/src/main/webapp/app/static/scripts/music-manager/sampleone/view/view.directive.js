/**
 * Created by pqanh09 on 9/7/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone.view').directive('sampleoneView', directiveFunction);

<<<<<<< HEAD
    directiveFunction.$inject = ['musicConstant'];

    function directiveFunction(musicConstant){
        return {
            templateUrl : musicConstant.templateUrl.musicManager.sampleone.view,
=======
    directiveFunction.$inject = [];

    function directiveFunction(){
        return {
            templateUrl : 'app/static/scripts/music-manager/sampleone/view/view.html',
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
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