(function () {
  'use strict';

  angular.module('music.manager.sampleone').controller('musicSampleoneController', controllerFunction);

<<<<<<< HEAD
  controllerFunction.$inject = ['$scope', 'sampleoneService','musicConstant'];

  function controllerFunction($scope, sampleoneService, musicConstant) {
    var vm = this;
    vm.currentView = sampleoneService.currentView;
    vm.currentView.url = musicConstant.templateUrl.musicManager.sampleone.viewTmp;
=======
  controllerFunction.$inject = ['$scope', 'sampleoneService'];

  function controllerFunction($scope, sampleoneService) {
    var vm = this;
    vm.currentView = sampleoneService.currentView;
    vm.currentView.url = sampleoneService.listView.viewAll;
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec


  }
})();
