/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('movieApp.module')
    .controller('MovieListViewComponentController', ['movieService', '$mdDialog', function MovieListViewComponentController(movieService, $mdDialog) {
      var vm   = this,
        helper = movieService;

      function openAlertDialog(dialogInfo) {
        dialogInfo = _.extend({
          title: 'Dialog Title',
          textContent: 'Dialog Content',
          okTitle: 'OK'
        }, dialogInfo);

        $mdDialog.show(
          $mdDialog.alert()
            .clickOutsideToClose(true)
            .title(dialogInfo.title)
            .textContent(dialogInfo.textContent)
            .ok(dialogInfo.okTitle)
        );
      }

      function addEventHandler() {
        vm.config.addCallback();
      }

      function editEventHandler() {
        if (vm.selectedItemList.length !== 1) {
          openAlertDialog({
            title: 'Warning',
            textContent: 'Only one item can be edited!'
          })
        } else {
          vm.config.editCallback(_.first(vm.selectedItemList));
        }
      }

      function deleteSelectedItemList(selectedItemList) {
        var parsedRequestBody = {
          movieList: []
        };

        _.map(selectedItemList, function loopSelectedItemList(selectedItem) {
          parsedRequestBody.movieList.push(selectedItem.id);
        });
        helper.sendDeleteAPIRequestPromise(parsedRequestBody)
          .then(function successCallback(successResponse) {
            console.log('Success Delete Message: ', successResponse);
            getData();
          }, function errorCallback(errorResponse) {
            console.log('Error Delete Message: ', errorResponse);
          });

      }

      function deleteEventHandler() {
        if (vm.selectedItemList.length === 0) {
          openAlertDialog({
            title: 'Warning',
            textContent: 'Select at least one item to delete!'
          })
        } else {
          deleteSelectedItemList(vm.selectedItemList);
        }
      }

      function refreshEventHandler() {
        getData();
      }

      function getData() {
        helper.sendGetDataAPIRequestPromise()
          .then(function successCallback(successResponse) {
            vm.data = successResponse;
          }, function errorCallback(errorResponse) {
            vm.data = [];
          });
      }

      vm.$onInit = function onInit() {
        if (!vm.config) {
          vm.config = {
            addCallback: angular.noop,
            editCallback: angular.noop,
            deleteCallback: angular.noop
          };
        }

        // vm.data = [
        //   {
        //     id: 1,
        //     title: 'English',
        //     description: 'en'
        //   },
        //   {
        //     id: 2,
        //     title: 'Vietnamese',
        //     description: 'vi'
        //   }
        // ];
        getData();

        vm.dataPromise = helper.sendGetDataAPIRequestPromise();

        vm.selectedItemList = [];

        vm.addEventHandler = addEventHandler;
        vm.editEventHandler = editEventHandler;
        vm.deleteEventHandler = deleteEventHandler;
        vm.refreshEventHandler = refreshEventHandler;
        vm.getData = getData;
      };



    }]);
})();
