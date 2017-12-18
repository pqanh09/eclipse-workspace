(function () {
  'use strict';

  angular.module('music.manager.sampleone').controller('musicSampleoneController', controllerFunction);

  controllerFunction.$inject = ['$scope', 'sampleoneService'];

  function controllerFunction($scope, sampleoneService) {
    var vm = this;
    vm.currentView = sampleoneService.currentView;
    vm.currentView.url = sampleoneService.listView.viewAll;


  }
})();
