/**
 * Created by pqanh09 on 9/7/16.
 */
(function () {
  'use strict';

  angular.module('music.manager.sampleone.detail').controller('musicSampleoneDetailController', controllerFunction);

   controllerFunction.$inject = ['$scope', '$http', '$timeout', 'musicManagerService','sampleoneService', 'musicConstant'];

  function controllerFunction($scope, $http, $timeout, musicManagerService,sampleoneService, musicConstant) {
    var vmDetail = this;
    vmDetail.data = [];
    vmDetail.configShowAlert = configShowAlert;
    vmDetail.viewAll = viewAll;
    vmDetail.currentView = sampleoneService.currentView;
    vmDetail.countdownStr = 'Ready......';
    vmDetail.alertData = angular.copy(musicManagerService.alertDefaultData);
    vmDetail.countdown = 0;

    vmDetail.currentProfileId = sampleoneService.currentSelected[0];
    vmDetail.averageTotal = [];
    function init(){
        //update
        if(vmDetail.currentProfileId){
        	vmDetail.data = angular.copy(musicManagerService.defaultData);
        	getTotal(vmDetail.currentProfileId);
        } else {
        	musicManagerService.showAlert(vmDetail.alertData, $timeout, 'error', 'Please select profile');
        	return;
        }
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
	                    		var percents = totalObj.percents;
	                    		var lastPrices = totalObj.lastPrices;
	                    		vmDetail.profileName = totalObj.name;
	                    		for(var i = 0; i < coins.length; i++){
	                    			vmDetail.data[coins[i]].show = true;
	                    			vmDetail.data[coins[i]].input = inputs[i].toFixed(2);
									vmDetail.data[coins[i]].percent = percents[i].toFixed(1);
									vmDetail.data[coins[i]].lastPrice = lastPrices[i].toFixed(2);
								}
	                    		//Total table
	                    		var totals = totalObj.totals;
	                    		vmDetail.averageTotal.length = 0;
		  						for(var key in totals) {
		  							vmDetail.averageTotal.push({
		  								time: moment(new Date(Number(key))).format('HH:mm'),
		  								value:totals[key].toFixed(1)
		  							});
		  						}
		  						vmDetail.averageTotal.reverse();
	                    		//musicManagerService.showAlert(vmDetail.alertData, $timeout, 'success', responseObj.message);
	                    	} else {
	                    		musicManagerService.showAlert(vmDetail.alertData, $timeout, 'error', 'Unknown error. Please see log.');
			                    console.error(response);
	                    	}
	                        //musicManagerService.showAlert(vmDetail.alertData, $timeout, 'success', responseObj.message);
	                    } else {
	                        musicManagerService.showAlert(vmDetail.alertData, $timeout, 'error', responseObj.message);
	                    }
	                } else {
	                	musicManagerService.showAlert(vmDetail.alertData, $timeout, 'error', 'Unknown error. Please see log.');
	                    console.error(response);
	                }
				}, function(error){
            musicManagerService.showAlert(vmDetail.alertData, $timeout, 'error', 'Unknown error. Please see log.');
            console.error(error);
        });
	};
	
	var stompClient = null;
    function connect() {
      var socket = new SockJS(musicConstant.wsApi.register);
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        stompClient.subscribe(musicConstant.wsApi.subscribe, function (calResult) {
          getTotal(vmDetail.currentProfileId).then(function(){
        	  vmDetail.countdown = 59;
          });
          $timeout();
        });
      });
    }
	connect();

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
