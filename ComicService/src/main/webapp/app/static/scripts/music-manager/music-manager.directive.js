/**
 * Created by pqanh09 on 9/7/16.
 */
(function () {
  'use strict';

  angular.module('music.manager').directive('musicManager', directiveFunction);

  directiveFunction.$inject = ['musicConstant'];

  function directiveFunction(musicConstant) {
    return {
      templateUrl: musicConstant.templateUrl.musicManager.url,
      restrict: 'EA',
      controller: 'musicManagerController',
      controllerAs: 'vm',
      scope: {},
      bindToController: {

      }
    }
  }
})();