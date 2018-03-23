/**
 * Created by pqanh09 on 9/7/16.
 */
(function () {
  'use strict';

  var detail2App = angular.module('music.manager.sampleone.detail2').controller('musicSampleoneDetail2Controller', controllerFunction);

  detail2App.filter('setDecimal', function ($filter) {
      return function (input, places) {
          if (isNaN(input)) return input;
          // If we want 1 decimal place, we want to mult/div by 10
          // If we want 2 decimal places, we want to mult/div by 100, etc
          // So use the following to create that factor
          var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
          return Math.round(input * factor) / factor;
      };
  });
  
  controllerFunction.$inject = ['$scope', '$http', '$timeout', 'musicManagerService','sampleoneService', 'musicConstant'];

  function controllerFunction($scope, $http, $timeout, musicManagerService,sampleoneService, musicConstant) {
    var vmDetail2 = this;
    vmDetail2.configShowAlert = configShowAlert;
    vmDetail2.viewAll = viewAll;
    vmDetail2.currentView = sampleoneService.currentView;
    vmDetail2.countdownStr = 'Ready......';
    vmDetail2.alertData = angular.copy(musicManagerService.alertDefaultData);
    vmDetail2.countdown = 0;
    vmDetail2.currentProfileId = sampleoneService.currentSelected[0];
    
    vmDetail2.data = [];
    vmDetail2.inputs = [];
    vmDetail2.costs = [];
    vmDetail2.units = [];
    vmDetail2.pair = [];
    vmDetail2.chanId = [];
    
    vmDetail2.percentProfitList = [];
    vmDetail2.percentProfit = 0;
    vmDetail2.totalProfit = 0;
    vmDetail2.realInput = 0;
    var connection;
    function init(){
        if(vmDetail2.currentProfileId){
        	vmDetail2.data.length = 0;
        	vmDetail2.totalProfit = 0;
        	//getTotal(vmDetail2.currentProfileId);
        	getTotal1(vmDetail2.currentProfileId).then(function(){
	    		connection = new WebSocket('wss://api.bitfinex.com/ws/2');
    			connection.onopen = function() {
    				var i = 0;
    	    		for(i = 0; i < vmDetail2.pair.length; i ++){
    	    			connection.send(JSON.stringify({'event':'subscribe','channel':'ticker','pair': vmDetail2.pair[i]}));
    	    		}
    			};
    			connection.onclose = function() {
    				console.log('Connection closed');
    			};
    			connection.onmessage = function(msg) {
    				var response = JSON.parse(msg.data);
    				
    				if(Array.isArray(response)){
    					if(response[1] !== 'hb'){
    						var index = vmDetail2.chanId.indexOf(response[0]);
    						if(index > -1){
    							var lastprice = response[1][6];
    							vmDetail2.data[index].lastPrice = lastprice;
    							var input = vmDetail2.data[index].input;
    							//profit 
    							vmDetail2.data[index].profit = (lastprice - input)*vmDetail2.data[index].unit;
    							//percent
    							vmDetail2.data[index].percent = (lastprice - input)*100/input;
    							getTotalProfit();
    							$timeout();
    						}
    					}
    				} else{
    					if(response.chanId){
    						vmDetail2.chanId[vmDetail2.pair.indexOf(response.pair)] = response.chanId;
    					}
    					
    				}
    			};
    			connection.onerror = function(error) {
    				console.error('Error detected: ' + error);
    			};
	    		
	    	});
        } else {
        	musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Please select profile');
        	return;
        }
    };
    init();
    
    function getTotal1(totalId){
		return $http.get(musicConstant.restApi.total.getOne + totalId)
				.then(function(response) {
					var responseObj = _.get(response, 'data.UsdtTotalResponseObject');
					if(angular.isDefined(responseObj)) {
	                    if(responseObj.success){
	                    	var totalObj = _.get(response, 'data.UsdtTotalResponseObject.list[0]');
	                    	if(angular.isDefined(responseObj)) {
	                    		//Market table
	                    		var coins = totalObj.coins;
	                    		vmDetail2.inputs = totalObj.inputs;
	                    		vmDetail2.costs = totalObj.costs;
	                    		vmDetail2.units = totalObj.units;
	                    		var i = 0, stt = 0, pair; 
	                    		for(i = 0; i < coins.length; i ++){
	                    			stt = coins[i];
	                    			pair = musicManagerService.bitfinexPairs[stt].id;
	                    			vmDetail2.pair.push(pair);
	                    			vmDetail2.chanId.push(0);
	                    			vmDetail2.realInput = vmDetail2.realInput + (vmDetail2.inputs[i]*vmDetail2.units[i]);
                    				vmDetail2.data.push({
                    				stt: i,
                    	          	chanId: 0,
                    	            pair: pair,
                    	            input: vmDetail2.inputs[i],
                    	            unit: vmDetail2.units[i],
                    	            cost: vmDetail2.costs[i],
                    	            lastPrice: 0,
                    	            percent: 0,
                    	            profit: 0
                    	          });
	                    	    }
	                    		//Total table
	                    		var percentProfit = totalObj.profitPercent;
	                    		vmDetail2.percentProfitList.length = 0;
		  						for(var key in percentProfit) {
		  							vmDetail2.percentProfitList.push({
		  								time: moment(new Date(Number(key))).format('HH:mm'),
		  								value:percentProfit[key]
		  							});
		  						}
		  						vmDetail2.percentProfitList.reverse();
	                    	} else {
	                    		musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Unknown error. Please see log.');
			                    console.error(response);
	                    	}
	                    } else {
	                        musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', responseObj.message);
	                    }
	                } else {
	                	musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Unknown error. Please see log.');
	                    console.error(response);
	                }
				}, function(error){
            musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Unknown error. Please see log.');
            console.error(error);
        });
	};
    function getTotal(totalId){
		return $http.get(musicConstant.restApi.total.getOne + totalId)
				.then(function(response) {
					var responseObj = _.get(response, 'data.UsdtTotalResponseObject');
					if(angular.isDefined(responseObj)) {
	                    if(responseObj.success){
	                    	var totalObj = _.get(response, 'data.UsdtTotalResponseObject.list[0]');
	                    	if(angular.isDefined(responseObj)) {
	                    		//Total table
	                    		var percentProfit = totalObj.profitPercent;
	                    		vmDetail2.percentProfitList.length = 0;
		  						for(var key in percentProfit) {
		  							vmDetail2.percentProfitList.push({
		  								time: moment(new Date(Number(key))).format('HH:mm'),
		  								value:percentProfit[key].toFixed(1)
		  							});
		  						}
		  						vmDetail2.percentProfitList.reverse();
	                    		//musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'success', responseObj.message);
	                    	} else {
	                    		musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Unknown error. Please see log.');
			                    console.error(response);
	                    	}
	                        //musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'success', responseObj.message);
	                    } else {
	                        musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', responseObj.message);
	                    }
	                } else {
	                	musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Unknown error. Please see log.');
	                    console.error(response);
	                }
				}, function(error){
            musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Unknown error. Please see log.');
            console.error(error);
        });
	};
	function getTotalProfit(){
    	vmDetail2.totalProfit = 0;
    	var i = 0;
    	for(i = 0 ; i < vmDetail2.data.length; i ++){
    		vmDetail2.totalProfit = vmDetail2.totalProfit + vmDetail2.data[i].profit;
    	}
    	vmDetail2.percentProfit = vmDetail2.totalProfit/vmDetail2.realInput*100;
    };
	var stompClient = null;
    function connect() {
      var socket = new SockJS(musicConstant.wsApi.register);
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        stompClient.subscribe(musicConstant.wsApi.subscribe, function (calResult) {
          getTotal(vmDetail2.currentProfileId).then(function(){
        	  vmDetail2.countdown = 59;
          });
          $timeout();
        });
      });
    }
	connect();

    function countdownFunc() {
      $timeout(function () {
        if (vmDetail2.countdown > 0) {
          vmDetail2.countdownStr = vmDetail2.countdown.toString();
          vmDetail2.countdown = vmDetail2.countdown - 1;
        }
        else if (vmDetail2.countdown === 0) {
          vmDetail2.countdownStr = 'Getting data......';
        } else {
          vmDetail2.countdownStr = ':ASLKFASK:LAS';
        }
        countdownFunc();
      }, 1000);
    }

    countdownFunc();

    function configShowAlert() {
      musicManagerService.configShowAlert(vmDetail2.alertData);
    }
    function viewAll() {
    	if(connection){
    		connection.close();
    	}
    	vmDetail2.currentView.url = musicConstant.templateUrl.musicManager.sampleone.viewTmp;
    }
    function getTotalPercent(totalId){
		return $http.get(musicConstant.restApi.total.getOne + totalId)
				.then(function(response) {
					var responseObj = _.get(response, 'data.UsdtTotalResponseObject');
					if(angular.isDefined(responseObj)) {
	                    if(responseObj.success){
	                    	var totalObj = _.get(response, 'data.UsdtTotalResponseObject.list[0]');
	                    	if(angular.isDefined(responseObj)) {
	                    		//Total table
	                    		vmDetail2.percentProfit.length = 0;
	                    		var percentProfit = totalObj.percentProfit;
		  						for(var key in percentProfit) {
		  							vmDetail2.percentProfit.push({
		  								time: moment(new Date(Number(key))).format('HH:mm'),
		  								value:percentProfit[key].toFixed(1)
		  							});
		  						}
		  						vmDetail2.percentProfit.reverse();
	                    		//musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'success', responseObj.message);
	                    	} else {
	                    		musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Unknown error. Please see log.');
			                    console.error(response);
	                    	}
	                        //musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'success', responseObj.message);
	                    } else {
	                        musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', responseObj.message);
	                    }
	                } else {
	                	musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Unknown error. Please see log.');
	                    console.error(response);
	                }
				}, function(error){
            musicManagerService.showAlert(vmDetail2.alertData, $timeout, 'error', 'Unknown error. Please see log.');
            console.error(error);
        });
	};

  }
})();
