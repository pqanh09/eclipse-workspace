/**
 * Created by pqanh09 on 9/6/16.
 */

(function(){
    'use strict';

    angular.module('music.manager.sampleone').directive('sampleone', directiveFunction);

<<<<<<< HEAD
    directiveFunction.$inject = ['musicConstant'];

    function directiveFunction(musicConstant){
        return {
            templateUrl : musicConstant.templateUrl.musicManager.sampleone.url,
=======
    directiveFunction.$inject = [];

    function directiveFunction(){
        return {
            templateUrl : 'app/static/scripts/music-manager/sampleone/sampleone.html',
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
            restrict: 'EA',
            controller: 'musicSampleoneController',
            controllerAs: 'vm',
            scope: {},
            bindToController: {}
        }
    }
})();