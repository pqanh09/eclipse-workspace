/**
 * Created by pqanh09 on 9/7/16.
 */
(function () {
  'use strict';

  angular.module('music.manager.sampleone.modify').controller('musicSampleoneModifyController', controllerFunction);


  controllerFunction.$inject = ['$scope', '$http', '$timeout', 'musicManagerService','sampleoneService','musicConstant'];

  function controllerFunction($scope, $http, $timeout, musicManagerService,sampleoneService, musicConstant) {

    var vmModify = this;
    vmModify.data = angular.copy(musicManagerService.defaultData);
    vmModify.configShowAlert = configShowAlert;
    vmModify.viewAll = viewAll;
    vmModify.currentView = sampleoneService.currentView;
    vmModify.alertData = angular.copy(musicManagerService.alertDefaultData);
    vmModify.apply = apply;
    vmModify.time = '00:00';
    vmModify.profileName = '';
    vmModify.isSell = true;
    vmModify.funcStr = 'Sell';
    vmModify.alarmNum = 5;
    vmModify.changeFuncType = changeFuncType;
    vmModify.isCreate = true;
    vmModify.apllyLabel = 'Create';
    vmModify.currentProfileId = sampleoneService.currentSelected[0];
    function init(){
      //update
      if(vmModify.currentProfileId){
        vmModify.isCreate = false;
        vmModify.apllyLabel = 'Update';
        getTotal(vmModify.currentProfileId);
      } else {
        vmModify.isCreate = true;
        vmModify.apllyLabel = 'Create';
      }
      getLastPrice();


    };

    init();

    function getTotal(totalId){
      return $http.get(musicConstant.restApi.total.getOne + totalId)
        .then(function(response) {
          var responseObj = _.get(response, 'data.UsdtTotalResponseObject');
          if(angular.isDefined(responseObj)) {
            if(responseObj.success){
              var totalObj = _.get(response, 'data.UsdtTotalResponseObject.list[0]');
              if(angular.isDefined(responseObj)) {
                //Market table
                var coins = totalObj.coins;
                var inputs = totalObj.inputs;
                var costs = totalObj.costs;
                var units = totalObj.units;
                vmModify.profileName = totalObj.name;
                vmModify.alarmNum = totalObj.alarmNum;
                vmModify.isSell = totalObj.sell;
                vmModify.funcStr = totalObj.sell ? 'Sell' : 'Buy';
                for(var i = 0; i < coins.length; i++){
                  vmModify.data[coins[i]].show = true;
                  vmModify.data[coins[i]].input = inputs[i];
                  vmModify.data[coins[i]].cost = costs[i];
                  vmModify.data[coins[i]].unit = units[i];
                }
              } else {
                musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', 'Unknown error. Please see log.');
                console.error(response);
              }
              //musicManagerService.showAlert(vmModify.alertData, $timeout, 'success', responseObj.message);

            } else {
              musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', responseObj.message);
            }
          } else {
            musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', 'Unknown error. Please see log.');
            console.error(response);
          }
        }, function(error){
          musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', 'Unknown error. Please see log.');
          console.error(error);
        });
    };
    function getLastPrice() {
      //get last price
      $http.get(musicConstant.restApi.lastPrice.getLatest)
        .then(function(response) {
          var lastPriceResponse = _.get(response, 'data.UsdtLastPriceResponseObject');
          if(angular.isDefined(lastPriceResponse)){
            if(lastPriceResponse.success){
              var lastPrice = _.get(response, 'data.UsdtLastPriceResponseObject.list[0]');
              if(angular.isDefined(lastPrice)){
                var lastTime = 0, lastList;
                for(var key in lastPrice.list) {
                  if(Number(key) > lastTime){
                    lastTime = Number(key);
                    lastList = lastPrice.list[key];
                  }
                }
                vmModify.time = moment(new Date(lastTime)).format('HH:mm');
                for(var i = 0; i < vmModify.data.length; i ++){
                  vmModify.data[i].lastPrice = lastList[i].toFixed(2);
                  if(vmModify.isCreate){
                	  vmModify.data[i].input = lastList[i].toFixed(2);
                  }
                  
                  //vmModify.data[i].show = true;
                }
              } else {
                musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', 'Unknown');
                console.error(response);
              }
            } else {
              musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', lastPriceResponse.message);
            }
          } else {
            musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', 'Unknown');
            console.error(response);
          }
        }, function(error){
          musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', 'Unknown error. Please see log.');
          console.error(error);
        });
    }

    function apply() {
      var requestObject = angular.copy(musicManagerService.defaultRequest);
      angular.forEach(vmModify.data, function(data){
        if(data.show){
        	requestObject.UsdtTotalRequestObject.model.coins.push(data.stt);
        	requestObject.UsdtTotalRequestObject.model.inputs.push(data.input);
        	requestObject.UsdtTotalRequestObject.model.costs.push(data.cost);
        	requestObject.UsdtTotalRequestObject.model.units.push(data.unit);
        }
      });
      requestObject.UsdtTotalRequestObject.model.name = vmModify.profileName;
      requestObject.UsdtTotalRequestObject.model.sell = vmModify.isSell;
      requestObject.UsdtTotalRequestObject.model.alarmNum = vmModify.alarmNum;
      //update
      if(!vmModify.isCreate && vmModify.currentProfileId){
    	  requestObject.UsdtTotalRequestObject.model.objectId = vmModify.currentProfileId;
      }
      var requestObj = {
        method: (vmModify.isCreate)? 'POST' : 'PUT',
        url: musicConstant.restApi.total.dfault,
        data: requestObject,
        headers: {'Content-Type': 'application/json'}
      }
      $http(requestObj).then(function(response){
        var jobResponse = _.get(response, 'data.UsdtTotalResponseObject');
        if(angular.isDefined(jobResponse)) {
          if(jobResponse.success){
            musicManagerService.showAlert(vmModify.alertData, $timeout, 'success', jobResponse.message);
          } else {
            musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', jobResponse.message);
          }
        } else {
          musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', 'Unknown error. Please see log.');
          console.error(response);
        }
      }, function(error){
        musicManagerService.showAlert(vmModify.alertData, $timeout, 'error', 'Unknown error. Please see log.');
        console.error(error);
      });
    }

    function configShowAlert() {
      musicManagerService.configShowAlert(vmModify.alertData);
    }
    function viewAll() {
      vmModify.currentView.url = musicConstant.templateUrl.musicManager.sampleone.viewTmp;
    }
    function changeFuncType() {
    	if(vmModify.isSell){
    		vmModify.funcStr = 'Sell';
    	} else {
    		vmModify.funcStr = 'Buy';
    	}
    }


  }
})();
