/**
 * Created by pqanh on 8/30/16.
 */
(function(){
  'use strict';

  angular.module('music.manager.admin').directive('admin', directiveFunction);

  directiveFunction.$inject = ['musicConstant'];

  function directiveFunction(musicConstant){
    return {
      templateUrl : musicConstant.templateUrl.musicManager.admin,
      restrict: 'EA',
      controller: 'musicAdminController',
      controllerAs: 'vm',
      scope: {},
      bindToController: false
    }
  }
})();