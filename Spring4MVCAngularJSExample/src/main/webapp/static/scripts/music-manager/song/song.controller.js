(function() {
	'use strict';

	angular.module('music.manager.song').controller('musicSongController',
			controllerFunction);

	controllerFunction.$inject = [ '$scope', '$http', '$timeout' ];

	function controllerFunction($scope, $http, $timeout) {
		var vm = this;
		vm.data = [
//			USDT-BTC	Bitcoin
//		    USDT-BCC	Bitcoin Cash
//		    USDT-BTG	Bitcoin Gold
//		    USDT-DASH	Dash
//		    USDT-ETH	Ethereum
//		    USDT-ETC	Ethereum Classic
//		    USDT-LTC	Litecoin
//		    USDT-XMR	Monero
//		    USDT-NEO	Neo
//		    USDT-OMG	OmiseGo
//		    USDT-XRP	Ripple
//		    USDT-ZEC	ZCash
			{stt:"0", id:"USDT-BTC",name:"Bitcoin",lastPrice:0,input:10,percent:0},
			{stt:"1", id:"USDT-BCC",name:"Bitcoin Cash",lastPrice:0,input:10,percent:0},
			{stt:"2", id:"USDT-BTG",name:"Bitcoin Gold",lastPrice:0,input:10,percent:0},
		
			{stt:"3", id:"USDT-DASH",name:"Dash",lastPrice:0,input:10,percent:0},
			{stt:"4", id:"USDT-ETH",name:"Ethereum",lastPrice:0,input:10,percent:0},
			{stt:"5", id:"USDT-ETC",name:"Ethereum Classic",lastPrice:0,input:10,percent:0},
			
			{stt:"6", id:"USDT-LTC",name:"Litecoin",lastPrice:0,input:10,percent:0},
			{stt:"7", id:"USDT-XMR",name:"Monero",lastPrice:0,input:10,percent:0},
			{stt:"8", id:"USDT-NEO",name:"Neo",lastPrice:0,input:10,percent:0},
			
			{stt:"9", id:"USDT-OMG",name:"OmiseGo",lastPrice:0,input:10,percent:0},
			{stt:"10", id:"USDT-XRP",name:"Ripple",lastPrice:0,input:10,percent:0},
			{stt:"11", id:"USDT-ZEC",name:"ZCasj",lastPrice:0,input:10,percent:0}

		];
//		vm.time = -1;
		vm.averageTotal = [];
		
		function getInput() {
			$http
					.get(
							"/Spring4MVCAngularJSExample/api/bittrex/averagetotal")
					.then(function(response) {
						vm.averageTotal.length = 0;
						var list = _.get(response, 'data.UsdtTotalResponseObject.list[0].list', []);
						for(var key in list) {
							var t = new Date(key);
							vm.averageTotal.push({
								time: moment(new Date(Number(key))).format('HH:mm'),
								value:list[key].toFixed(1) 
							});
						}
						vm.averageTotal.reverse();
						
						$timeout();
					});
		}
		getInput();

		var stompClient = null;
		function connect() {
			var socket = new SockJS('/Spring4MVCAngularJSExample/register');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				stompClient.subscribe('/topic/greetings', function(calResult) {
					vm.dataStr = calResult.body.toString();
					$timeout();
				});
			
				stompClient.subscribe('/topic/usdtMarkets', function(calResult) {
					var result = JSON.parse(calResult.body);
					getInput();
					
//					vm.time = moment(new Date(result.time)).format('HH:mm:ss YYYY-MM-DD'); 
					angular.forEach(vm.data, function(obj) {
						obj.lastPrice = result.lastPrice[obj.stt - 1].toFixed(2);
						obj.percent = result.percent[obj.stt - 1].toFixed(1);
						obj.input = result.input[obj.stt - 1].toFixed(2);
					});
					
					$timeout();
				});

			});
		}
		connect();


		/*
		 * vm.songManagerView = true;
		 * 
		 * vm.listSelected = {}; vm.listSelected.list = new Set([]);
		 * vm.listObject = musicManagerService.findAllSongs(); vm.listCol =
		 * musicManagerService.getSongCols(); vm.ctrlButton = {};
		 * 
		 * vm.createButton = true;
		 * 
		 * 
		 * vm.addSong = addSong; vm.editSong = editSong; vm.deleteSong =
		 * deleteSong; vm.viewAll = viewAll;
		 * 
		 * function viewAll() { vm.songManagerView = true;
		 * updateCtrlButton(vm.listSelected.list.size); } function addSong() {
		 * vm.songManagerView = false; vm.action = 'Create';
		 * vm.listSelected.song = null; vm.listSelected.list.clear(); var list =
		 * vm.listObject; for (var i = 0; i < list.length; i++) {
		 * list[i].checked = false; } disableAllCtrButton();
		 *  }
		 * 
		 * function editSong() { vm.songManagerView = false; vm.action = 'Edit';
		 * disableAllCtrButton(); }
		 * 
		 * 
		 * function deleteSong() { var names = ''; // convert set to plain Array
		 * (with Array comprehensions) var listId =
		 * Array.from(vm.listSelected.list); for (var i = 0; i < listId.length;
		 * i++) { var song = musicManagerService.findSongById(listId[i]);
		 * if(song){ if( i > 0){ names = names + ', '; } names = names +
		 * song.name; } }
		 * 
		 * var r = confirm("Do you want to delete the Song(s): " + names + ".");
		 * if (r == true) {
		 * musicManagerService.deleteSongs(vm.listSelected.list); }
		 *  } function disableAllCtrButton(){ vm.ctrlButton.add = true;
		 * vm.ctrlButton.edit = true; vm.ctrlButton.delete = true; }
		 * $scope.$watch( 'vm.listSelected.list.size', function (newVal) {
		 * updateCtrlButton(newVal); }); function
		 * updateCtrlButton(sizeSelected){ if(sizeSelected < 1) {
		 * vm.ctrlButton.add = false; vm.ctrlButton.edit = true;
		 * vm.ctrlButton.delete = true; } else if(sizeSelected == 1) {
		 * vm.ctrlButton.add = false; vm.ctrlButton.edit = false;
		 * vm.ctrlButton.delete = false;
		 *  } else{ vm.ctrlButton.add = false; vm.ctrlButton.edit = true;
		 * vm.ctrlButton.delete = false; } }
		 */
		
//	    

	}
})();
