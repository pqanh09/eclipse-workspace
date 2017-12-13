(function() {
	'use strict';

	angular.module('music.manager.song').controller('musicSongController',
			controllerFunction);

	controllerFunction.$inject = [ '$scope', '$http', '$timeout' ];

	function controllerFunction($scope, $http, $timeout) {
		var vm = this;
		vm.data = [
			{stt:"1", id:"",name:"Mot",last:0,input:10,percent:0},
			{stt:"2", id:"",name:"Mot",last:0,input:10,percent:0},
			{stt:"3", id:"",name:"Mot",last:0,input:10,percent:0},
		
			{stt:"4", id:"",name:"Mot",last:0,input:10,percent:0},
			{stt:"5", id:"",name:"Mot",last:0,input:10,percent:0},
			{stt:"6", id:"",name:"Mot",last:0,input:10,percent:0},
			
			{stt:"7", id:"",name:"Mot",last:0,input:10,percent:0},
			{stt:"8", id:"",name:"Mot",last:0,input:10,percent:0},
			{stt:"9", id:"",name:"Mot",last:0,input:10,percent:0},
			
			{stt:"10", id:"",name:"Mot",last:0,input:10,percent:0},
			{stt:"11", id:"",name:"Mot",last:0,input:10,percent:0},
			{stt:"12", id:"",name:"Mot",last:0,input:10,percent:0}

		];
		vm.time = -1;
		vm.total = 0;
	
		
		function getInput() {
			return $http
					.get(
							"/Spring4MVCAngularJSExample/api/bittrex/input")
					.then(function(response) {
						var list = response.data.UsdtInputResponseObject.list[0].list;
						angular.forEach(vm.data, function(obj) {
							obj.input = list[obj.stt - 1].toFixed(2);
						});
					});
		}
		getInput();

		var stompClient = null;
		function connect() {
			var socket = new SockJS('/Spring4MVCAngularJSExample/register');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				// console.log(frame);
				stompClient.subscribe('/topic/greetings', function(calResult) {
					vm.dataStr = calResult.body.toString();
					console.log(vm.dataStr);
//					$scope.$digest();
					$timeout();
				});
			
				stompClient.subscribe('/topic/usdtMarkets', function(calResult) {
					console.log(calResult);
					var result = JSON.parse(calResult.body);
					console.log(result);
					getInput().then(function(){
						vm.time = moment(new Date(result.time)).format('HH:mm:ss YYYY-MM-DD'); 
						var total = 0;
						angular.forEach(vm.data, function(obj) {
							obj.last = result.list[obj.stt - 1].toFixed(2);
							obj.percent = ((obj.last - obj.input)*100/obj.input).toFixed(1);
							total = total+ obj.percent/12;
							
						});
						vm.total = total.toFixed(1);
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
		
//	    USDT-BTC	Bitcoin
//	    USDT-BCC	Bitcoin Cash
//	    USDT-BTG	Bitcoin Gold
//	    USDT-DASH	Dash
//	    USDT-ETH	Ethereum
//	    USDT-ETC	Ethereum Classic
//	    USDT-LTC	Litecoin
//	    USDT-XMR	Monero
//	    USDT-NEO	Neo
//	    USDT-OMG	OmiseGo
//	    USDT-XRP	Ripple
//	    USDT-ZEC	ZCash

	}
})();