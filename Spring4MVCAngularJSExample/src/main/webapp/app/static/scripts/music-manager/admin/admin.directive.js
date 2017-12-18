/**
 * Created by pqanh on 8/30/16.
 */
(function(){
  'use strict';

  angular.module('music.manager.admin').directive('admin', directiveFunction);

  directiveFunction.$inject = [];

  function directiveFunction(){
    return {
      templateUrl : 'app/static/scripts/music-manager/admin/admin.html',
      restrict: 'EA',
      controller: 'musicAdminController',
      controllerAs: 'vm',
      scope: {},
      bindToController: false
    }
  }
})();