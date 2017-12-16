(function () {
    'use strict';

    angular.module('music.manager.playlist').controller('musicPlayListController', controllerFunction);

    controllerFunction.$inject = [ '$scope', '$http', '$timeout' ,'musicManagerService'];

    function controllerFunction($scope, $http, $timeout, musicManagerService) {
        var vm = this;
        vm.data = angular.copy(musicManagerService.defaultData);
        vm.update = update;
        vm.startJob = startJob;
        vm.stopJob = stopJob;
        vm.configShowAlert = configShowAlert;
        vm.disableFrom = false;
        vm.alertData = angular.copy(musicManagerService.alertDefaultData);
        vm.time = '00:00';
        function init() {

            //get market
            $http.get("api/bittrex/getmartket")
              .then(function(response) {
                  var job = _.get(response, 'data.JobResponseObject.list[0]');
                  if(angular.isDefined(job)){
                      // init data
                      var coins = job.coins;
                      var inputs = job.inputs;
                      for(var i = 0; i < coins.length; i++){
                          vm.data[coins[i]].input = inputs[i];
                          vm.data[coins[i]].show = true;
                      }
                      if(job.status !== 'stop'){
                          vm.disableFrom = true;
                      }
                  } else {
                      //create new Job
                      vm.disableFrom = false;
                  }
              });
            //get last price
            $http.get("api/bittrex/lastPrice")
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
                        	  vm.time = moment(new Date(lastTime)).format('HH:mm');
                        	  for(var i = 0; i < vm.data.length; i ++){
                        		  vm.data[i].lastPrice = lastList[i].toFixed(2);
                        	  }
                          } else {
                              musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown');
                              console.log(response);
                          }
                      } else {
                          musicManagerService.showAlert(vm.alertData, $timeout, 'error', lastPriceResponse.message);
                      }
                  } else {
                      musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown');
                      console.log(response);
                  }
              });

        }
        init();

        /////
        function update() {
            var request = angular.copy(musicManagerService.defaultRequest);
            angular.forEach(vm.data, function(data){
                if(data.show){
                    request.UsdtJobRequestObject.model.coins.push(data.stt);
                    request.UsdtJobRequestObject.model.inputs.push(data.input);
                }
            });

            $http({
                method: 'POST',
                url: 'api/bittrex/job',
                data: request,
                headers: {'Content-Type': 'application/json'}
            }).then(function(response){
                var jobResponse = _.get(response, 'data.JobResponseObject');
                if(angular.isDefined(jobResponse)) {
                    if(jobResponse.success){
                        musicManagerService.showAlert(vm.alertData, $timeout, 'success', jobResponse.message);
                    } else {
                        musicManagerService.showAlert(vm.alertData, $timeout, 'error', jobResponse.message);
                    }
                } else {
                    console.log(response);
                }
            }, function(error){
                musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error.');
                console.log(error);
            });
        }

        function startJob() {
        	$http.get("api/bittrex/startmartket")
                    .then(function(response) {
                        var jobResponse = _.get(response, 'data.JobResponseObject');
                        if(angular.isDefined(job)){
                        	if(job.success){
                        		musicManagerService.showAlert(vm.alertData, $timeout, 'success', 'Start Job successfully.');
                        	} else {
                        		musicManagerService.showAlert(vm.alertData, $timeout, 'error', job.message);	
                        	}
                        } else {
                            vm.disableFrom = false;
                            musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown');
                            console.error(response);
                        }
                    });
        }

        function stopJob() {
        	
        	$http.get("api/bittrex/stopmartket")
            .then(function(response) {
                var jobResponse = _.get(response, 'data.JobResponseObject');
                if(angular.isDefined(job)){
                	if(job.success){
                		musicManagerService.showAlert(vm.alertData, $timeout, 'success', 'Stop Job successfully.');
                	} else {
                		musicManagerService.showAlert(vm.alertData, $timeout, 'error', job.message);	
                	}
                } else {
                    vm.disableFrom = false;
                    musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown');
                    console.error(response);
                }
            });
        }

        function configShowAlert(){
            musicManagerService.configShowAlert(vm.alertData);
        }


    }
})();
