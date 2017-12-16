(function() {
	'use strict';

	angular.module('music.manager.song').controller('musicSongController',
			controllerFunction);

	controllerFunction.$inject = [ '$scope', '$http', '$timeout' ,'musicManagerService'];

	function controllerFunction($scope, $http, $timeout, musicManagerService) {
		var vm = this;
		vm.data = [];
		vm.configShowAlert = configShowAlert;
		vm.disableFrom = false;
		vm.alertData = angular.copy(musicManagerService.alertDefaultData);
//		vm.time = -1;

		//$scope.$digest();
		vm.averageTotal = [];
		function getJob(){
			return $http.get("api/bittrex/getmartket")
					.then(function(response) {
						var job = _.get(response, 'data.JobResponseObject.list[0]');
						if(angular.isDefined(job)){
							if(job.status === 'stop'){
								musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Please start get data');
								vm.disableFrom = true;
							}else {
								vm.data = angular.copy(musicManagerService.defaultData);
								// init data
								var coins = job.coins;
								var inputs = job.inputs;
								for(var i = 0; i < coins.length; i++){
									vm.data[coins[i]].input = inputs[i];
									vm.data[coins[i]].show = true;
								}
								vm.disableFrom = false;
							}
						} else {
							musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'No inputs to start');
							vm.disableFrom = true;
						}
					});
		};
		function getAverageTotal(){
			return $http.get("api/bittrex/averagetotal")
					.then(function(response) {
						vm.averageTotal.length = 0;
						var list = _.get(response, 'data.UsdtTotalResponseObject.list[0].list', []);
						for(var key in list) {
							vm.averageTotal.push({
								time: moment(new Date(Number(key))).format('HH:mm'),
								value:list[key].toFixed(1)
							});
						}
						vm.averageTotal.reverse();
					});
		};
		function init() {
			//get market 
			getJob().then(function(){
				if(!vm.disableFrom){
					getAverageTotal();
				}
			});
		}
		init();

		var stompClient = null;
		function connect() {
			var socket = new SockJS('register');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				stompClient.subscribe('/topic/usdtMarkets', function(calResult) {
					if (vm.disableFrom) {
						getJob();
						getAverageTotal();
					} else {
						var result = JSON.parse(calResult.body);
						vm.averageTotal.unshift({
							time: moment(new Date(result.time)).format('HH:mm'),
							value: result.total.toFixed(1)
						});
					}
//					vm.time = moment(new Date(result.time)).format('HH:mm:ss YYYY-MM-DD'); 
					angular.forEach(vm.data, function(obj) {
						obj.lastPrice = result.lastPrice[obj.stt].toFixed(2);
						obj.percent = result.percent[obj.stt].toFixed(1);
					});
					$timeout();
				});

			});
		}
		connect();
		function configShowAlert(){
            musicManagerService.configShowAlert(vm.alertData);
        }
	}
})();
