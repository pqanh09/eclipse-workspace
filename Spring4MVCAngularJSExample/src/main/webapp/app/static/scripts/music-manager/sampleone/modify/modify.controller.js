/**
 * Created by pqanh09 on 9/7/16.
 */
(function () {
  'use strict';

  angular.module('music.manager.sampleone.modify').controller('musicSampleoneModifyController', controllerFunction);

<<<<<<< HEAD
  controllerFunction.$inject = ['$scope', '$http', '$timeout', 'musicManagerService','sampleoneService','musicConstant'];

  function controllerFunction($scope, $http, $timeout, musicManagerService,sampleoneService, musicConstant) {
=======
  controllerFunction.$inject = ['$scope', '$http', '$timeout', 'musicManagerService','sampleoneService'];

  function controllerFunction($scope, $http, $timeout, musicManagerService,sampleoneService) {
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
    var vmModify = this;
    vmModify.data = angular.copy(musicManagerService.defaultData);
    vmModify.configShowAlert = configShowAlert;
    vmModify.viewAll = viewAll;
    vmModify.currentView = sampleoneService.currentView;
    vmModify.alertData = angular.copy(musicManagerService.alertDefaultData);
    vmModify.apply = apply;
    vmModify.time = '00:00';
    vmModify.profileName = '';
    vmModify.isCreate = true;
    vmModify.apllyLabel = 'Create';
<<<<<<< HEAD
    vmModify.currentProfileId = sampleoneService.currentSelected[0];
    function init(){
      //update
      if(vmModify.currentProfileId){
        vmModify.isCreate = false;
        vmModify.apllyLabel = 'Update';
        getTotal(vmModify.currentProfileId);
=======
    vmModify.currentSelected = sampleoneService.currentSelected;
    function init(){
      //update
      if(vmModify.currentSelected && vmModify.currentSelected.length > 0){
        vmModify.isCreate = false;
        vmModify.apllyLabel = 'Update';
        getTotal(vmModify.currentSelected[0]);
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
      } else {
        vmModify.isCreate = true;
        vmModify.apllyLabel = 'Create';
      }
      getLastPrice();


    };

    init();

    function getTotal(totalId){
<<<<<<< HEAD
      return $http.get(musicConstant.restApi.total.getOne + totalId)
=======
      return $http.get('http://localhost:8080/Spring4MVCAngularJSExample/api/bittrex/total/'+ totalId)
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
        .then(function(response) {
          var responseObj = _.get(response, 'data.UsdtTotalResponseObject');
          if(angular.isDefined(responseObj)) {
            if(responseObj.success){
              var totalObj = _.get(response, 'data.UsdtTotalResponseObject.list[0]');
              if(angular.isDefined(responseObj)) {
                //Market table
                var coins = totalObj.coins;
                var inputs = totalObj.inputs;
                vmModify.profileName = totalObj.name;
                for(var i = 0; i < coins.length; i++){
                  vmModify.data[coins[i]].show = true;
                  vmModify.data[coins[i]].input = inputs[i].toFixed(2);
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
<<<<<<< HEAD
      $http.get(musicConstant.restApi.lastPrice.getLatest)
=======
      $http.get("http://localhost:8080/Spring4MVCAngularJSExample/api/bittrex/getLatestLastPriceData")
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
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
                  vmModify.data[i].input = lastList[i].toFixed(2);
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
<<<<<<< HEAD
      var requestObject = angular.copy(musicManagerService.defaultRequest);
      angular.forEach(vmModify.data, function(data){
        if(data.show){
        	requestObject.UsdtTotalRequestObject.model.coins.push(data.stt);
          requestObject.UsdtTotalRequestObject.model.inputs.push(data.input);
        }
      });
      requestObject.UsdtTotalRequestObject.model.name = vmModify.profileName;
      //update
      if(!vmModify.isCreate && vmModify.currentProfileId){
    	  requestObject.UsdtTotalRequestObject.model.objectId = vmModify.currentProfileId;
      }
      var requestObj = {
        method: (vmModify.isCreate)? 'POST' : 'PUT',
        url: musicConstant.restApi.total.dfault,
        data: requestObject,
=======
      var usdtTotalRequestObject = angular.copy(musicManagerService.defaultRequest);
      angular.forEach(vmModify.data, function(data){
        if(data.show){
          usdtTotalRequestObject.UsdtTotalRequestObject.model.coins.push(data.stt);
          usdtTotalRequestObject.UsdtTotalRequestObject.model.inputs.push(data.input);
        }
      });
      usdtTotalRequestObject.UsdtTotalRequestObject.model.name = vmModify.profileName;

      var requestObj = {
        method: (vmModify.isCreate)? 'POST' : 'PUT',
        url: 'http://localhost:8080/Spring4MVCAngularJSExample/api/bittrex/total',
        data: usdtTotalRequestObject,
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
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
<<<<<<< HEAD
      vmModify.currentView.url = musicConstant.templateUrl.musicManager.sampleone.viewTmp;
=======
      vmModify.currentView.url = sampleoneService.listView.viewAll;
>>>>>>> 480a8650a0738e4d7efde273ffb838c08376ecec
    }


  }
})();
