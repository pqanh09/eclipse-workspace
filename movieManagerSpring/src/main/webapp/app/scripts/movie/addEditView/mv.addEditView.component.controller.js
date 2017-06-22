/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('movieApp.module')
    .controller('MovieAddEditViewComponentController', ['movieConstant', 'movieService', function MovieAddEditViewComponentController(movieConstant, movieService) {
      var vm    = this,
        myConst = movieConstant,
        helper  = movieService,
        applyFn = {},
        initFn  = {};

      vm.$onInit = function onInit() {
        vm.form = {
          field: {}
        };

        vm.const = {
          editType: myConst.movieConst.view.type.addEdit.editType
        };

        vm.applyEventHandler = applyEventHandler;
        vm.cancelEventHandler = cancelEventHandler;
        vm.isShowImage = isShowImage;

        if (!vm.config) {
          vm.config = {
            editType: vm.const.editType.add,
            afterCancelCallback: angular.noop,
            afterApplyCallback: angular.noop
          };
        }

        applyFn[vm.const.editType.add] = function applyAddFn(afterApplyCallback) {
          var parsedRequestBody = getParsedAddRequestBody(vm.form.field);
          helper.sendAddAPIRequestPromise(parsedRequestBody)
            .then(function successCallback(successResponse) {
              console.log('Success Message: ', successResponse);
              afterApplyCallback();
            }, function errorCallback(errorResponse) {
              console.log('Failed Message: ', errorResponse);
            });
        };

        applyFn[vm.const.editType.edit] = function applyEditFn(afterApplyCallback) {
          var parsedRequestBody = getParsedAddRequestBody(vm.form.field);
          helper.sendEditAPIRequestPromise(vm.config.selectedItem.id, parsedRequestBody)
            .then(function successCallback(successResponse) {
              console.log('Success Message: ', successResponse);
              afterApplyCallback();
            }, function errorCallback(errorResponse) {
              console.log('Failed Message: ', errorResponse);
            });
        };

        initFn[vm.const.editType.add] = function initAddFn() {
          vm.form.field = helper.getDefaultMovieFormData()
        };

        initFn[vm.const.editType.edit] = function initEditFn() {
          if (!vm.config.selectedItem) {
            vm.form.field = helper.getDefaultMovieFormData()
          } else {
            var selectedItem = vm.config.selectedItem;
            vm.form.field = {
              title: selectedItem.title,
              description: selectedItem.description,
              price: selectedItem.price,
              imageFilename: selectedItem.imageFilename,
              imageUrl: selectedItem.imageUrl
            };
          }
        };

        initFn[vm.config.editType]();
      };

      function applyEventHandler() {
        applyFn[vm.config.editType](vm.config.afterApplyCallback);
      }

      function cancelEventHandler() {
        vm.config.afterCancelCallback();
      }

      function getParsedAddRequestBody(formField) {
        var formData = new FormData();
        var imageFile = new Blob();
        if (formField.imageFile.length > 0) {
          imageFile = formField.imageFile[0].lfFile;
        }
        formData.append('imageFile', imageFile);
        formData.append('otherAttributes', new Blob([JSON.stringify({
            title: formField.title,
            description: formField.description,
            language: 'English',
            price: formField.price
        })], {
            type: 'application/json'
        }));
        return formData;
      }

      function isShowImage(imageFilename) {
        if (imageFilename) {
          return true;
        }
        return false;
      }

    }]);
})();
