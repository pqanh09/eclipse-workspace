(function () {
    'use strict';

    var songApp = angular.module('music.manager.song').controller('musicSongController', controllerFunction);
    
    songApp.filter('setDecimal', function ($filter) {
        return function (input, places) {
            if (isNaN(input)) return input;
            // If we want 1 decimal place, we want to mult/div by 10
            // If we want 2 decimal places, we want to mult/div by 100, etc
            // So use the following to create that factor
            var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
            return Math.round(input * factor) / factor;
        };
    });
    
    controllerFunction.$inject = [ '$scope', '$http', '$timeout' ,'musicManagerService', '$q','musicConstant'];

    function controllerFunction($scope, $http, $timeout, musicManagerService, $q, musicConstant) {
        var vm = this;
        //https://docs.bitfinex.com/v1/reference#ws-public-ticker
        //https://gist.github.com/bitgord/6ac91702c66267d152dafd7aec3486cd
        //wss://api.bitfinex.com/ws/2
    	//https://www.bitfinex.com/account/_bootstrap/?_=1513737224182
        vm.data = [];
        vm.inputs = [];
        vm.costs = [];
        vm.units = [];
        vm.pair = [];
        vm.chanId = [];
        
        vm.totalProfit = 0;
        vm.percentProfit = 0;
        vm.realInput = 0;
        function getTotalProfit(){
        	vm.totalProfit = 0;
        	var i = 0;
        	for(i = 0 ; i < vm.data.length; i ++){
        		vm.totalProfit = vm.totalProfit + vm.data[i].profit;
        	}
        	vm.percentProfit = vm.totalProfit/vm.realInput*100;
        };
        
        function init(){
	    	vm.data.length = 0;
	    	getTotal('5a39bd8d540f7365b953505d').then(function(){
	    		var connection = new WebSocket('wss://api.bitfinex.com/ws/2');
    			connection.onopen = function() {
    				var i = 0;
    	    		for(i = 0; i < vm.pair.length; i ++){
    	    			connection.send(JSON.stringify({'event':'subscribe','channel':'ticker','pair': vm.pair[i]}));
    	    		}
    			};
    			connection.onclose = function() {
    				console.log('Connection closed');
    			};
    			connection.onmessage = function(msg) {
    				var response = JSON.parse(msg.data);
    				
    				if(Array.isArray(response)){
    					if(response[1] !== 'hb'){
    						var index = vm.chanId.indexOf(response[0]);
    						if(index > -1){
    							var lastprice = response[1][6];
    							vm.data[index].lastPrice = lastprice;
    							var input = vm.data[index].input;
    							//profit 
    							vm.data[index].profit = (lastprice - input)*vm.data[index].unit;
    							//percent
    							vm.data[index].percent = (lastprice - input)*100/input;
    							getTotalProfit();
    							$timeout();
    						}
    					}
    				} else{
//    					console.log(response);
    					if(response.chanId){
    						vm.chanId[vm.pair.indexOf(response.pair)] = response.chanId;
//    						console.log(vm.chanId[vm.pair.indexOf(response.pair)]);
//    						console.log('chanId: ' + response.chanId);
//    						console.log('pair: ' + response.pair);
    					}
    					
    				}
    			};
    			connection.onerror = function(error) {
    				console.error('Error detected: ' + error);
    			};
	    		
	    	});
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
    	                    		vm.inputs = totalObj.inputs;
    	                    		vm.costs = totalObj.costs;
    	                    		vm.units = totalObj.units;
    	                    		var i = 0, stt = 0, pair; 
    	                    		for(i = 0; i < coins.length; i ++){
    	                    			stt = coins[i];
    	                    			pair = musicManagerService.bitfinexPairs[stt].id;
    	                    			vm.pair.push(pair);
    	                    			vm.chanId.push(0);
    	                    			vm.realInput = vm.realInput + (vm.inputs[i]*vm.units[i]);
	                    				vm.data.push({
	                    				stt: i,
	                    	          	chanId: 0,
	                    	            pair: pair,
	                    	            input: vm.inputs[i],
	                    	            unit: vm.units[i],
	                    	            cost: vm.costs[i],
	                    	            lastPrice: 0,
	                    	            percent: 0,
	                    	            profit: 0
	                    	          });
    	                    	    }
    	                    		vm.totalProfit = vm.data[0].profit + vm.data[1].profit + vm.data[2].profit + vm.data[3].profit + vm.data[4].profit + vm.data[5].profit + vm.data[6].profit + vm.data[7].profit + vm.data[8].profit;
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
    }
})();
