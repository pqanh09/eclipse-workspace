/**
 * Created by pqanh on 8/30/16.
 */
(function(){
  'use strict';

  angular.module('music.manager.song').directive('song', directiveFunction);

  directiveFunction.$inject = ['musicConstant'];

  function directiveFunction(musicConstant){
    return {
      templateUrl : musicConstant.templateUrl.musicManager.song,
      restrict: 'EA',
      controller: 'musicSongController',
      controllerAs: 'vm',
      scope: {},
      bindToController: false
    }
  }
})();