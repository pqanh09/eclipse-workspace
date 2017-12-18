(function() {
	'use strict';

	angular.module('music.manager.viewall').controller('musicViewallController',
			controllerFunction);

	controllerFunction.$inject = [ '$scope', '$http', '$timeout' ,'musicManagerService'];

	function controllerFunction($scope, $http, $timeout, musicManagerService) {
		var vm = this;
		vm.data = [];
		vm.configShowAlert = configShowAlert;
		vm.countdownStr = 'Ready......';
		vm.alertData = angular.copy(musicManagerService.alertDefaultData);
		vm.countdown = 0;
		vm.lastPrice = [];
		function getTotals(){
			return $http.get('api/bittrex/total')
					.then(function(response) {
						var responseObj = _.get(response, 'data.UsdtTotalResponseObject');
						if(angular.isDefined(responseObj)) {
		                    if(responseObj.success){
		                    	var totalList = _.get(response, 'data.UsdtTotalResponseObject.list');
		                    	if(angular.isDefined(responseObj)) {
		                    		vm.data.length = 0;
		                    		for(var i = 0; i < totalList.length; i++){
		                    			var tmpCoins = totalList[i].coins;
		                    			var coins = [];
		                    			for(var j = 0; j < tmpCoins.length; j++){
		                    				coins.push(musicManagerService.martketConst[tmpCoins[j]].id);
		                    			}
		                    			var tmpTotals = totalList[i].totals;
		                    			var lastTotal = tmpTotals[tmpTotals.length-1];
//		                    			var avgTotal;
//		                    			var totalTime;
//		                    			for(var key in lastTotal) {
//		                    				totalTime = moment(new Date(Number(key))).format('HH:mm'),
//		                    				avgTotal = lastTotal[key].toFixed(1)
//		                    			}
		                    			vm.data.push({
		                    				stt: i + 1,
		                    				name: totalList[i].name,
		                    				coins: coins,
		                    				avgTotal: 'aaa',
		                    				time: 'bbb'
		                    			});
		                    			
		                    		}
		                    		//Market table
		                    		//Total table
//		                    		var totals = totalObj.totals;
//		                    		vm.averageTotal.length = 0;
//			  						for(var key in totals) {
//			  							vm.averageTotal.push({
//			  								time: moment(new Date(Number(key))).format('HH:mm'),
//			  								value:totals[key].toFixed(1)
//			  							});
//			  						}
//			  						vm.averageTotal.reverse();
		                    	} else {
		                    		musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
				                    console.error(response);
		                    	}
		                        
		                    } else {
		                        musicManagerService.showAlert(vm.alertData, $timeout, 'error', responseObj.message);
		                    }
		                } else {
		                	musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
		                    console.error(response);
		                }
					}, function(error){
                musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
                console.error(error);
            });
		};
		function getLatestLastPrice(){
			return $http.get("api/bittrex/getLatestLastPriceData")
            .then(function(response) {
                var lastPriceResponse = _.get(response, 'data.UsdtLastPriceResponseObject');
                if(angular.isDefined(lastPriceResponse)){
                    if(lastPriceResponse.success){
                        var lastPrice = _.get(response, 'data.UsdtLastPriceResponseObject.list[0]');
                        console.error(response);
                        if(angular.isDefined(lastPrice)){
                          vm.lastPrice.length = 0;
                      	  var lastTime = 0, lastList;
                      	  for(var key in lastPrice.list) {
                      		if(Number(key) > lastTime){
                      			lastTime = Number(key);
                      			lastList = lastPrice.list[key];
                      		}
                      	  }
//                      	  vm.time = moment(new Date(lastTime)).format('HH:mm');
                      	  for(var i = 0; i < lastList.length; i ++){
                      		  vm.lastPrice.push({
                      			id: musicManagerService.martketConst[i].id,
                      			value: lastList[i].toFixed(2)
                      		  });
                      	  }
                        } else {
                            musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown');
                            console.error(response);
                        }
                    } else {
                        musicManagerService.showAlert(vm.alertData, $timeout, 'error', lastPriceResponse.message);
                    }
                } else {
                    musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown');
                    console.error(response);
                }
            }, function(error){
              musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
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
			stompClient.connect({}, function(frame) {
				stompClient.subscribe('/topic/usdtMarkets', function(calResult) {
					getData().then(function(){
						vm.countdown = 59;
					});
					vm.countdown = 60;
					$timeout();
				});

			});
		}
		connect();
		function countdownFunc(){
			$timeout(function() {
		    	if(vm.countdown > 0){
		    		vm.countdownStr = vm.countdown.toString();
		    		vm.countdown = vm.countdown - 1;
		    	}
		    	else if(vm.countdown === 0){
		    		vm.countdownStr = 'Getting data......';
		    	} else {
		    		vm.countdownStr = ':ASLKFASK:LAS';
		    	}
		    	countdownFunc();
		    }, 1000);
		}
		countdownFunc();
		
		function configShowAlert(){
            musicManagerService.configShowAlert(vm.alertData);
        }
	}
})();
