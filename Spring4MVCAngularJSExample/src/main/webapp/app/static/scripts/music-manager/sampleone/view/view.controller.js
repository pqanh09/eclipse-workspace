/**
 * Created by pqanh09 on 9/7/16.
 */
(function () {
  'use strict';

  angular.module('music.manager.sampleone.view').controller('musicSampleoneViewController', controllerFunction);

  controllerFunction.$inject = ['$scope', '$http', '$timeout', 'musicManagerService','sampleoneService'];

  function controllerFunction($scope, $http, $timeout, musicManagerService,sampleoneService) {
    var vmView = this;
    vmView.data = [];
    vmView.configShowAlert = configShowAlert;
    vmView.viewDetail = viewDetail;
    vmView.viewModify = viewModify;
    vmView.deleteFunc = deleteFunc;
    vmView.checkFunc = checkFunc;
    vmView.currentView = sampleoneService.currentView;
    vmView.countdownStr = 'Ready......';
    vmView.alertData = angular.copy(musicManagerService.alertDefaultData);
    vmView.countdown = 0;
    vmView.lastPrice = [];
    vmView.currentSelected = sampleoneService.currentSelected;
    vmView.action = 0;
    vmView.lastPriceTime = '';
    //action 0 : can create, view,...
    //action 1 : update
    //action 2 : delete


    function getTotals() {
      return $http.get('http://localhost:8080/Spring4MVCAngularJSExample/api/bittrex/total')
        .then(function (response) {
          var responseObj = _.get(response, 'data.UsdtTotalResponseObject');
          if (angular.isDefined(responseObj)) {
            if (responseObj.success) {
              var totalList = _.get(response, 'data.UsdtTotalResponseObject.list');
              if (angular.isDefined(responseObj)) {
                vmView.data.length = 0;
                for (var i = 0; i < totalList.length; i++) {
                  var tmpCoins = totalList[i].coins;
                  var coins = [];
                  for (var j = 0; j < tmpCoins.length; j++) {
                    coins.push(musicManagerService.martketConst[tmpCoins[j]].id);
                  }
                  var tmpTotals = totalList[i].totals;
                  var lastTotal = undefined;
                  for (var lastTotal in tmpTotals);
                  if(lastTotal){
                    vmView.data.push({
                      totalId: totalList[i].objectId,
                      stt: i + 1,
                      checked: false,
                      name: totalList[i].name,
                      coins: coins,
                      avgTotal: tmpTotals[lastTotal].toFixed(2),
                      time: moment(new Date(Number(lastTotal))).format('HH:mm')
                    });
                  }
                  vmView.currentSelected.length = 0;

                }
              } else {
                musicManagerService.showAlert(vmView.alertData, $timeout, 'error', 'Unknown error. Please see log.');
                console.error(response);
              }

            } else {
              musicManagerService.showAlert(vmView.alertData, $timeout, 'error', responseObj.message);
            }
          } else {
            musicManagerService.showAlert(vmView.alertData, $timeout, 'error', 'Unknown error. Please see log.');
            console.error(response);
          }
        }, function (error) {
          musicManagerService.showAlert(vmView.alertData, $timeout, 'error', 'Unknown error. Please see log.');
          console.error(error);
        });
    };
    function getLatestLastPrice() {
      return $http.get("http://localhost:8080/Spring4MVCAngularJSExample/api/bittrex/getLatestLastPriceData")
        .then(function (response) {
          var lastPriceResponse = _.get(response, 'data.UsdtLastPriceResponseObject');
          if (angular.isDefined(lastPriceResponse)) {
            if (lastPriceResponse.success) {
              var lastPrice = _.get(response, 'data.UsdtLastPriceResponseObject.list[0]');
              //console.error(response);
              if (angular.isDefined(lastPrice)) {
                vmView.lastPrice.length = 0;
                var latestTime = 0, latestList;
                for (var key in lastPrice.list) {
                  if (Number(key) > latestTime) {
                    latestTime = Number(key);
                    latestList = lastPrice.list[key];
                  }
                }
//                      	  vmView.time = moment(new Date(lastTime)).format('HH:mm');
                for (var i = 0; i < latestList.length; i++) {
                  vmView.lastPrice.push({
                    id: musicManagerService.martketConst[i].id,
                    value: latestList[i].toFixed(2)
                  });
                }
                vmView.lastPriceTime = moment(new Date(Number(latestTime))).format('HH:mm');
              } else {
                musicManagerService.showAlert(vmView.alertData, $timeout, 'error', 'Unknown');
                console.error(response);
              }
            } else {
              musicManagerService.showAlert(vmView.alertData, $timeout, 'error', lastPriceResponse.message);
            }
          } else {
            musicManagerService.showAlert(vmView.alertData, $timeout, 'error', 'Unknown');
            console.error(response);
          }
        }, function (error) {
          musicManagerService.showAlert(vmView.alertData, $timeout, 'error', 'Unknown error. Please see log.');
          console.error(error);
        });
    }

    function getData() {
      //get market 
      getTotals();
      getLatestLastPrice();

    }

    getData();

    var stompClient = null;

    function connect() {
      var socket = new SockJS('http://localhost:8080/Spring4MVCAngularJSExample/register');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/usdtMarkets', function (calResult) {
          // getData().then(function(){
          // vmView.countdown = 59;
          // });
          //vmView.countdown = 60;
          $timeout();
        });

      });
    }

   // connect();
    function countdownFunc() {
      $timeout(function () {
        if (vmView.countdown > 0) {
          vmView.countdownStr = vmView.countdown.toString();
          vmView.countdown = vmView.countdown - 1;
        }
        else if (vmView.countdown === 0) {
          vmView.countdownStr = 'Getting data......';
        } else {
          vmView.countdownStr = ':ASLKFASK:LAS';
        }
        countdownFunc();
      }, 1000);
    }

    countdownFunc();

    function configShowAlert() {
      musicManagerService.configShowAlert(vmView.alertData);
    }
    function viewDetail(obj) {
      vmView.currentSelected.length = 0;
      vmView.currentSelected.push(obj.totalId);
      vmView.currentView.url = sampleoneService.listView.detail;
    }
    function checkFunc(obj) {
      if(obj.checked){
        vmView.currentSelected.push(obj.totalId);
      } else {
        var removeIndex = -1;
        for(var i = 0; i < vmView.currentSelected.length; i ++){
          if(obj.totalId === vmView.currentSelected[i]){
            removeIndex = i;
          }
        }
        if(removeIndex > -1){
          vmView.currentSelected.splice(removeIndex, 1);
        }
      }
    }
    function viewModify() {
      vmView.currentView.url = sampleoneService.listView.modify;
    }
    function deleteFunc() {
      //
      console.log('Delete...........');
    }

    $scope.$watch(
      'vmView.currentSelected.length',
      function (newSize) {
        updateCtrlButton(newSize);
      });
    function updateCtrlButton(newSize){
      if(newSize === 1) {
        vmView.action = 1;
      } else if(newSize > 1){
        vmView.action = 2;
      } else {
        vmView.action = 0;
      }
    }
  }
})();
