(function() {
	'use strict';

	angular.module('music.manager.song').controller('musicSongController',
			controllerFunction);

	controllerFunction.$inject = [ '$scope', '$http', '$timeout' ];

	function controllerFunction($scope, $http, $timeout) {
		var vm = this;
		var urlPrefix = 'https://bittrex.com/api/v2.0/pub/Markets/GetMarketSummaries?_=';
		vm.dataStr = "Hello";
		vm.data = [];

		function getAll() {
			$http
					.get(
							"http://localhost:8080/Spring4MVCAngularJSExample/api/manga")
					.then(function(response) {
						vm.data = response.data;
						// console.log(vm.data);
					});
		}
		getAll();

		var stompClient = null;
		function connect() {
			var socket = new SockJS('/Spring4MVCAngularJSExample/register');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				// console.log(frame);
				stompClient.subscribe('/topic/greetings', function(calResult) {
					vm.dataStr = calResult.body.toString();
					console.log(vm.dataStr);
					$timeout();
				});
				stompClient.subscribe('/topic/pollData', function(calResult) {
					pollDataBittrex(calResult.body);
					$timeout();
				});

			});
		}
		connect();

		function pollDataBittrex(response) {
			console.log(response);
//			var url = urlPrefix + new Date().getTime().toString();
			var url = 'http://localhost:8080/Spring4MVCAngularJSExample/api/manga';
			console.log(url);
			
			$http
			.get(url)
			.then(function(response) {
//				console.log(response);
				var list = _.filter(response.data.MangaResponseObject.list, 'objectId', '5a2a1261d2b918d3cefe26d1');
				var list2 = _.find(response.data.MangaResponseObject.list, function(chr) {
					 return chr.objectId === '5a2a1261d2b918d3cefe26d1';
				});
//				var list = _.filter(response.result, 'Market.BaseCurrency', 'USDT');
				console.log(list2);
			});
			
		}

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

	}
})();
