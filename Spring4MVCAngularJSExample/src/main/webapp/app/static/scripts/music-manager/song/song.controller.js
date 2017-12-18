(function() {
	'use strict';

	angular.module('music.manager.song').controller('musicSongController',
			controllerFunction);

	controllerFunction.$inject = [ '$scope', '$http', '$timeout' ,'musicManagerService'];

	function controllerFunction($scope, $http, $timeout, musicManagerService) {
		var vm = this;
		vm.data = angular.copy(musicManagerService.defaultData);
		vm.configShowAlert = configShowAlert;
		vm.alertData = angular.copy(musicManagerService.alertDefaultData);
		vm.coins = [];
		vm.countdown = 0;
		vm.lastPriceTime = '';
		vm.countdownStr = 'Ready......';
		vm.jobId = musicManagerService.jobId;
		vm.jobId = '5a36a98d954b971d723644f1';
		//$scope.$digest();
		vm.averageTotal = [];
		function getJob(){
			return $http.get('api/bittrex/total/'+vm.jobId)
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
		                    		vm.profileName = totalObj.name;
		                    		for(var i = 0; i < coins.length; i++){
		                    			vm.data[coins[i]].show = true;
		                    			vm.data[coins[i]].input = inputs[i].toFixed(2);
										vm.data[coins[i]].percent = percents[i].toFixed(1);
										vm.data[coins[i]].lastPrice = lastPrices[i].toFixed(2);
									}
		                    		//Total table
		                    		var totals = totalObj.totals;
		                    		vm.averageTotal.length = 0;
			  						for(var key in totals) {
			  							vm.averageTotal.push({
			  								time: moment(new Date(Number(key))).format('HH:mm'),
			  								value:totals[key].toFixed(1)
			  							});
			  						}
			  						vm.averageTotal.reverse();
		                    		musicManagerService.showAlert(vm.alertData, $timeout, 'success', responseObj.message);
		                    	} else {
		                    		musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
				                    console.error(response);
		                    	}
		                        //musicManagerService.showAlert(vm.alertData, $timeout, 'success', responseObj.message);
		                        
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
//		function getAverage
		
		function init() {
			//get market 
			getJob();

		}
		init();

		var stompClient = null;
		function connect() {
			var socket = new SockJS('http://localhost:8080/Spring4MVCAngularJSExample/register');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				stompClient.subscribe('/topic/usdtMarkets', function(calResult) {
					getJob().then(function(){
						vm.countdown = 59;
					});
//					vm.countdown = 60;
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
