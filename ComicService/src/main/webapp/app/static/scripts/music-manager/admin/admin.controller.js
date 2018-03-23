(function () {
    'use strict';

    angular.module('music.manager.admin').controller('musicAdminController', controllerFunction);

    controllerFunction.$inject = [ '$scope', '$http', '$timeout' ,'musicManagerService', '$q'];

    function controllerFunction($scope, $http, $timeout, musicManagerService, $q) {
        var vm = this;
        vm.disableFromManageLastPriceJob = true;
				//
				vm.state = 0;
				//state 0 : create Last Price Job
        //state 1 : start Last Price Job
        //state 2 : stop Last Price Job
        vm.alertData = angular.copy(musicManagerService.alertDefaultData);
        vm.alertData.show = true;
        vm.alertData.autoHide = false;
        vm.alertData.label = 'Keep showing alert.';
        
        //func
        vm.createLastPriceJob = createLastPriceJob;
        vm.startLastPriceJob = startLastPriceJob;
        vm.stopLastPriceJob = stopLastPriceJob;
        vm.configShowAlert = configShowAlert;
        
        //
        function init() {
            $http.get("api/bittrex/job/getLastPriceJob")
              .then(function(response) {
            	  var jobResponse = _.get(response, 'data.JobResponseObject');
            	  if(angular.isDefined(jobResponse)){
	        		  if(jobResponse.success){
	        			  vm.disableFromManageLastPriceJob = false;
	            		  var lastPriceJob = _.get(response, 'data.JobResponseObject.list[0]');
	            		  if(angular.isDefined(lastPriceJob)){
	            			  if(lastPriceJob.status === 'running' || lastPriceJob.status === 'scheduled'){
	            				  vm.state = 2;//stop
	            			  } else if(lastPriceJob.status === 'stop'){
	            				  vm.state = 1;//start
	            			  } else {
	            				  vm.disableFromManageLastPriceJob = true;
	                    		  musicManagerService.showAlert(vm.alertData, $timeout, 'error', jobResponse.message);
	            				  console.error(jobResponse);
	            			  }
	            		  } else {
	            			  vm.state = 0;//create
	            		  }
            	  		} else {
                  		  vm.disableFromManageLastPriceJob = true;
                		  musicManagerService.showAlert(vm.alertData, $timeout, 'error', jobResponse.message);
            	  		}
            	  } else {
            		  vm.disableFromManageLastPriceJob = true;
            		  musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
            		  console.error(jobResponse);
            	  }
            	  
              }, function(error){
            	  vm.disableFromManageLastPriceJob = true;
        		  musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
        		  console.error(error);
              });
        }
        init();
        
        function createLastPriceJob() {
        	var urlStr = 'api/bittrex/job/createLastPriceJob';
        	performJob(urlStr).then(function(result){
        		vm.state = 1;
        	});
        }
        function startLastPriceJob() {
        	var urlStr = 'api/bittrex/job/startLastPriceJob';
        	performJob(urlStr).then(function(data){
        		vm.state = 2;
        	});
        }
        function stopLastPriceJob() {
        	var urlStr = 'api/bittrex/job/stopLastPriceJob';
        	performJob(urlStr).then(function(data){
        		vm.state = 1;
        	});
        }
        //create-start-stop
        function performJob(urlStr) {
        	var deferred = $q.defer();
            $http.get(urlStr)
              .then(function(response) {
            	  var jobResponse = _.get(response, 'data.JobResponseObject');
            	  if(angular.isDefined(jobResponse)){
	        		  if(jobResponse.success){
	                      musicManagerService.showAlert(vm.alertData, $timeout, 'success', jobResponse.message);
	                      deferred.resolve(true);
            	  		} else {
                		  musicManagerService.showAlert(vm.alertData, $timeout, 'error', jobResponse.message);
                		  deferred.resolve(false);
            	  		}
            	  } else {
            		  musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
            		  console.error(jobResponse);
            		  deferred.resolve(false);
            	  }
              }, function(error){
        		  musicManagerService.showAlert(vm.alertData, $timeout, 'error', 'Unknown error. Please see log.');
        		  console.error(error);
        		  deferred.resolve(false);
              });
            return deferred.promise;
        }
        function configShowAlert(){
            musicManagerService.configShowAlert(vm.alertData);
        }
    }
})();
