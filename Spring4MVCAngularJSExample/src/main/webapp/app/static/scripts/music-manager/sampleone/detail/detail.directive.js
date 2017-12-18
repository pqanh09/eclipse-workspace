/**
 * Created by pqanh09 on 9/7/16.
 */
(function(){
    'use strict';

    angular.module('music.manager.sampleone.detail').directive('sampleoneDetail', directiveFunction);

<<<<<<< HEAD
    directiveFunction.$inject = ['musicConstant'];

    function directiveFunction(musicConstant){
        return {
            templateUrl : musicConstant.templateUrl.musicManager.sampleone.detail,
=======
    directiveFunction.$inject = [];

    function directiveFunction(){
        return {
            templateUrl : 'app/static/scripts/music-manager/sampleone/detail/detail.html',
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
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