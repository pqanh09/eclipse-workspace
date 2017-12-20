(function() {
	'use strict';

	// module
	angular.module('myApp', [ 'ngRoute', 'music.manager' ]);

	// controller
	angular.module('myApp').controller('homeController', homeController);

	homeController.$inject = [];

	function homeController() {
		var vm = this;
//		var connection = new WebSocket('wss://api.bitfinex.com/ws');
//		connection.onopen = function() {
//			/*
//			 * Send a small message to the console once the connection is
//			 * established
//			 */
//			var BTCpair = 'BCCUSD';
//			connection.send(JSON.stringify({"event":"subscribe", "channel":"ticker", "pair":BTCpair}));
////			connection.send(JSON.stringify({"event":"subscribe", "channel":"ticker", "pair":"ETHUSD"}));
//			console.log('Connection open!');
//		};
//		connection.onclose = function() {
//			console.log('Connection closed');
//		};
//		connection.onmessage = function(msg) {
//			var response = JSON.parse(msg.data);
////			console.log(response);
//			if(Array.isArray(response)){
//				console.log(response);
////				console.log(response);
////				if(response.length > 2){
////					console.log(response);
////				}
//			} else{
//				if(response.chanId){
//					console.log('chanId: ' + response.chanId);
//					console.log('pair: ' + response.pair);
//				}
//				
//			}
////			console.log(response.chanId);
////			console.log(response.pair);
//		};
//		connection.onerror = function(error) {
//			console.log('Error detected: ' + error);
//		};
		
		
		
		
		
		
	}

	// routing
	angular.module('myApp').config(HomeRoute);

	HomeRoute.$inject = [ '$routeProvider', 'musicConstant' ];

	function HomeRoute($routeProvider, musicConstant) {
		$routeProvider.otherwise({
			templateUrl : musicConstant.templateUrl.home.url,
			controller : "homeController"
		});

	}

})();