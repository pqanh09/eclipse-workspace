/**
 * Created by pqanh09 on 9/7/16.
 */
(function () {
  'use strict';

  angular.module('music.manager.sampleone.view').controller('musicSampleoneViewController', controllerFunction);

<<<<<<< HEAD
  controllerFunction.$inject = ['$scope', '$q', '$http', '$timeout', 'musicManagerService','sampleoneService', 'musicConstant'];

  function controllerFunction($scope, $q, $http, $timeout, musicManagerService, sampleoneService, musicConstant) {
=======
  controllerFunction.$inject = ['$scope', '$http', '$timeout', 'musicManagerService','sampleoneService'];

  function controllerFunction($scope, $http, $timeout, musicManagerService,sampleoneService) {
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
    var vmView = this;
    vmView.data = [];
    vmView.configShowAlert = configShowAlert;
    vmView.viewDetail = viewDetail;
    vmView.viewModify = viewModify;
    vmView.deleteFunc = deleteFunc;
    vmView.checkFunc = checkFunc;
<<<<<<< HEAD
    vmView.refresh = refresh;
=======
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
    vmView.currentView = sampleoneService.currentView;
    vmView.countdownStr = 'Ready......';
    vmView.alertData = angular.copy(musicManagerService.alertDefaultData);
    vmView.countdown = 0;
    vmView.lastPrice = [];
    vmView.currentSelected = sampleoneService.currentSelected;
<<<<<<< HEAD
    vmView.disableCreate = false;
    vmView.disableUpdate = false;
    vmView.disableDelete = false;
    vmView.lastPriceTime = '';

    function getTotals() {
      return $http.get(musicConstant.restApi.total.dfault)
=======
    vmView.action = 0;
    vmView.lastPriceTime = '';
    //action 0 : can create, view,...
    //action 1 : update
    //action 2 : delete


    function getTotals() {
      return $http.get('http://localhost:8080/Spring4MVCAngularJSExample/api/bittrex/total')
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
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
<<<<<<< HEAD
	                vmView.data.push({
	                  totalId: totalList[i].objectId,
	                  stt: i + 1,
	                  checked: false,
	                  name: totalList[i].name,
	                  coins: coins,
	                  avgTotal: (lastTotal) ? tmpTotals[lastTotal].toFixed(2): 0,
	                  time: (lastTotal) ? moment(new Date(Number(lastTotal))).format('HH:mm') : 'Waiting'
	                });
=======
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
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
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
<<<<<<< HEAD
    
    function getLatestLastPrice() {
      return $http.get(musicConstant.restApi.lastPrice.getLatest)
=======
    function getLatestLastPrice() {
      return $http.get("http://localhost:8080/Spring4MVCAngularJSExample/api/bittrex/getLatestLastPriceData")
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
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
<<<<<<< HEAD
      var promises = [];
      promises.push(getTotals());
      promises.push(getLatestLastPrice());
      return $q.all(promises);
    }
    getData();

    var stompClient = null;
    function connect() {
      var socket = new SockJS(musicConstant.wsApi.register);
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        stompClient.subscribe(musicConstant.wsApi.subscribe, function (calResult) {
          getData().then(function(){
        	  vmView.countdown = 59;
          });
          $timeout();
        });
      });
    }
    connect();
=======
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
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
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
<<<<<<< HEAD
      vmView.currentView.url = musicConstant.templateUrl.musicManager.sampleone.detailTmp;
=======
      vmView.currentView.url = sampleoneService.listView.detail;
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
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
<<<<<<< HEAD
      vmView.currentView.url = musicConstant.templateUrl.musicManager.sampleone.modifyTmp;
    }
    
    function deleteFunc() {
    	var requestObject = angular.copy(musicManagerService.defaultRequest);
        angular.forEach(vmView.currentSelected, function(profileId){
        	requestObject.UsdtTotalRequestObject.ids.push(profileId);
        });
        var requestObj = {
          method: 'DELETE',
          url: musicConstant.restApi.total.dfault,
          data: requestObject,
          headers: {'Content-Type': 'application/json'}
        }
        $http(requestObj).then(function(response){
          var jobResponse = _.get(response, 'data.UsdtTotalResponseObject');
          if(angular.isDefined(jobResponse)) {
            if(jobResponse.success){
              musicManagerService.showAlert(vmView.alertData, $timeout, 'success', jobResponse.message);
              getTotals();
            } else {
              musicManagerService.showAlert(vmView.alertData, $timeout, 'error', jobResponse.message);
            }
          } else {
            musicManagerService.showAlert(vmView.alertData, $timeout, 'error', 'Unknown error. Please see log.');
            console.error(response);
          }
        }, function(error){
          musicManagerService.showAlert(vmView.alertData, $timeout, 'error', 'Unknown error. Please see log.');
          console.error(error);
        });
    }
    function refresh() {
    	getData();
=======
      vmView.currentView.url = sampleoneService.listView.modify;
    }
    function deleteFunc() {
      //
      console.log('Delete...........');
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
    }

    $scope.$watch(
      'vmView.currentSelected.length',
      function (newSize) {
        updateCtrlButton(newSize);
      });
    function updateCtrlButton(newSize){
<<<<<<< HEAD
        //newSize == 0 : can create, view,...
        //newSize == 1 : update
        //newSize >= : delete
        if(newSize > 1){
        	vmView.disableCreate = true;
            vmView.disableUpdate = true;
            vmView.disableDelete = false;
        } else if(newSize === 1){
        	vmView.disableCreate = true;
            vmView.disableUpdate = false;
            vmView.disableDelete = false;
        } else {
        	vmView.disableCreate = false;
            vmView.disableUpdate = true;
            vmView.disableDelete = true;
        }
        
=======
      if(newSize === 1) {
        vmView.action = 1;
      } else if(newSize > 1){
        vmView.action = 2;
      } else {
        vmView.action = 0;
      }
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
    }
  }
})();
