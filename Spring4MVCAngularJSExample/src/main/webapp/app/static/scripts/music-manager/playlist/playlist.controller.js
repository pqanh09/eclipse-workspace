(function () {
    'use strict';

    angular.module('music.manager.playlist').controller('musicPlayListController', controllerFunction);

    controllerFunction.$inject = [ '$scope', '$http', '$timeout' ,'musicManagerService'];

    function controllerFunction($scope, $http, $timeout, musicManagerService) {
        var vm = this;
        vm.data = angular.copy(musicManagerService.defaultData);
        vm.create = create;
        vm.profileName = 'AAA';
        vm.configShowAlert = configShowAlert;
        vm.alertData = angular.copy(musicManagerService.alertDefaultData);
        vm.time = '00:00';
        vm.jobId = musicManagerService.jobId;
		    vm.jobId = 'PlayList';
        function init() {
            //get last price
            $http.get("api/bittrex/getLatestLastPriceData")
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
                        		  vm.data[i].input = lastList[i].toFixed(2);
                        		  vm.data[i].show = true;
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
        init();

        /////
        function create() {
            var request = angular.copy(musicManagerService.defaultRequest);
            angular.forEach(vm.data, function(data){
                if(data.show){
                    request.UsdtTotalRequestObject.model.coins.push(data.stt);
                    request.UsdtTotalRequestObject.model.inputs.push(data.input);
                }
            });
            request.UsdtTotalRequestObject.model.name = vm.profileName;

            $http({
                method: 'POST',
                url: 'http://localhost:8080/Spring4MVCAngularJSExample/api/bittrex/total',
                data: request,
                headers: {'Content-Type': 'application/json'}
            }).then(function(response){
                var jobResponse = _.get(response, 'data.UsdtTotalResponseObject');
                if(angular.isDefined(jobResponse)) {
                    if(jobResponse.success){
                        musicManagerService.showAlert(vm.alertData, $timeout, 'success', jobResponse.message);
                    } else {
                        musicManagerService.showAlert(vm.alertData, $timeout, 'error', jobResponse.message);
                    }
                } else {
                	 musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
                    console.error(response);
                }
            }, function(error){
                musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
                console.error(error);
            });
        }

       

        function configShowAlert(){
            musicManagerService.configShowAlert(vm.alertData);
        }


    }
})();
