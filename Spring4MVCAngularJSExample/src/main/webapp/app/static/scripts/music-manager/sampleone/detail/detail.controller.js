/**
 * Created by pqanh09 on 9/7/16.
 */
(function () {
  'use strict';

  angular.module('music.manager.sampleone.detail').controller('musicSampleoneDetailController', controllerFunction);

  controllerFunction.$inject = ['$scope', '$http', '$timeout', 'musicManagerService','sampleoneService'];

  function controllerFunction($scope, $http, $timeout, musicManagerService,sampleoneService) {
    var vmDetail = this;
    vmDetail.data = [];
    vmDetail.configShowAlert = configShowAlert;
    vmDetail.viewAll = viewAll;
    vmDetail.currentView = sampleoneService.currentView;
    vmDetail.countdownStr = 'Ready......';
    vmDetail.alertData = angular.copy(musicManagerService.alertDefaultData);
    vmDetail.countdown = 0;
    vmDetail.currentSelected = sampleoneService.currentSelected;
    function init(){
      console.log(vmDetail.currentSelected);
    };
    init();


   // connect();
    function countdownFunc() {
      $timeout(function () {
        if (vmDetail.countdown > 0) {
          vmDetail.countdownStr = vmDetail.countdown.toString();
          vmDetail.countdown = vmDetail.countdown - 1;
        }
        else if (vmDetail.countdown === 0) {
          vmDetail.countdownStr = 'Getting data......';
        } else {
          vmDetail.countdownStr = ':ASLKFASK:LAS';
        }
        countdownFunc();
      }, 1000);
    }

    countdownFunc();

    function configShowAlert() {
      musicManagerService.configShowAlert(vmDetail.alertData);
    }
    function viewAll() {
      vmDetail.currentView.url = sampleoneService.listView.viewAll;
    }


  }
})();
