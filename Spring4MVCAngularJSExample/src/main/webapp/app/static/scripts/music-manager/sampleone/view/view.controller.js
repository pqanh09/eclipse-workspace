/**
 * Created by pqanh09 on 9/7/16.
 */
(function () {
  'use strict';

  var viewApp = angular.module('music.manager.sampleone.view').controller('musicSampleoneViewController', controllerFunction);

  viewApp.filter('setDecimal', function ($filter) {
      return function (input, places) {
          if (isNaN(input)) return input;
          // If we want 1 decimal place, we want to mult/div by 10
          // If we want 2 decimal places, we want to mult/div by 100, etc
          // So use the following to create that factor
          var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
          return Math.round(input * factor) / factor;
      };
  });

  controllerFunction.$inject = ['$scope', '$q', '$http', '$timeout', 'musicManagerService','sampleoneService', 'musicConstant'];

  function controllerFunction($scope, $q, $http, $timeout, musicManagerService, sampleoneService, musicConstant) {

    var vmView = this;
    vmView.data = [];
    vmView.configShowAlert = configShowAlert;
    vmView.viewDetail = viewDetail;
    vmView.viewDetail2 = viewDetail2;
    vmView.viewModify = viewModify;
    vmView.deleteFunc = deleteFunc;
    vmView.checkFunc = checkFunc;
    vmView.refresh = refresh;

    vmView.currentView = sampleoneService.currentView;
    vmView.countdownStr = 'Ready......';
    vmView.alertData = angular.copy(musicManagerService.alertDefaultData);
    vmView.countdown = 0;
    vmView.lastPrice = [];
    vmView.currentSelected = sampleoneService.currentSelected;
    vmView.disableCreate = false;
    vmView.disableUpdate = false;
    vmView.disableDelete = false;
    vmView.lastPriceTime = '';

    function getTotals() {
      return $http.get(musicConstant.restApi.total.dfault)
        .then(function (response) {
          var responseObj = _.get(response, 'data.UsdtTotalResponseObject');
          if (angular.isDefined(responseObj)) {
            if (responseObj.success) {
              var totalList = _.get(response, 'data.UsdtTotalResponseObject.list');
              if (angular.isDefined(responseObj)) {
                vmView.data.length = 0;
                for (var i = 0; i < totalList.length; i++) {
                  var tmpTotals = totalList[i].profitPercent;
                  var lastTotal = undefined;
                  for (var lastTotal in tmpTotals);
                  	var totalCost = 0;
                  	for( var j = 0; j < totalList[i].costs.length; j ++){
                  		totalCost += totalList[i].costs[j];
                  	}
	                vmView.data.push({
	                  totalId: totalList[i].objectId,
	                  stt: i + 1,
	                  checked: false,
	                  name: totalList[i].name,
	                  profitPercent: (lastTotal) ? tmpTotals[lastTotal]: 0,
	                  time: (lastTotal) ? moment(new Date(Number(lastTotal))).format('HH:mm') : 'Waiting',
	                  totalProfit: totalList[i].totalProfit,
            		  costs: totalCost
	                });
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
      return $http.get(musicConstant.restApi.lastPrice.getLatest)

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
                    value: latestList[i]
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
      var promises = [];
      promises.push(getTotals());
      promises.push(getLatestLastPrice());
      return $q.all(promises);
    }
    getData();

    var stompClient = null;

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

	$scope.$on("$destroy", function() {
        if (stompClient) {
        	stompClient.disconnect(function() {
        		console.log('See you next time!')
        	});
        }
    });
    countdownFunc();

    function configShowAlert() {
      musicManagerService.configShowAlert(vmView.alertData);
    }
    function viewDetail(obj) {
      vmView.currentSelected.length = 0;
      vmView.currentSelected.push(obj.totalId);
      vmView.currentView.url = musicConstant.templateUrl.musicManager.sampleone.detailTmp;
    }
    function viewDetail2(obj) {
        vmView.currentSelected.length = 0;
        vmView.currentSelected.push(obj.totalId);
        vmView.currentView.url = musicConstant.templateUrl.musicManager.sampleone.detailTmp2;
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
              vmView.currentSelected.length = 0;
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
    }

    $scope.$watch(
      'vmView.currentSelected.length',
      function (newSize) {
        updateCtrlButton(newSize);
      });
    function updateCtrlButton(newSize){
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

    }
  }
})();
