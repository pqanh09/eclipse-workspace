(function () {
  'use strict';

  angular.module('music.manager.sampleone').controller('musicSampleoneController', controllerFunction);

  controllerFunction.$inject = ['$scope', 'sampleoneService','musicConstant'];

  function controllerFunction($scope, sampleoneService, musicConstant) {
    var vm = this;
    vm.currentView = sampleoneService.currentView;
    vm.currentView.url = musicConstant.templateUrl.musicManager.sampleone.viewTmp;


  }
})();
