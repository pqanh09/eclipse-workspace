/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('languageApp.module')
    .controller('LanguageAddEditViewComponentController', ['languageConstant', 'languageService', function LanguageAddEditViewComponentController(languageConstant, languageService) {
      var vm    = this,
        myConst = languageConstant,
        helper  = languageService,
        applyFn = {},
        initFn  = {};

      vm.form = {
        field: {}
      };

      vm.const = {
        editType: myConst.languageConst.view.type.addEdit.editType
      };

      function applyEventHandler() {
        applyFn[vm.config.editType](vm.config.afterApplyCallback);
      }

      function cancelEventHandler() {
        vm.config.afterCancelCallback();
      }

      function getParsedAddRequestBody(formData) {
        return {
          name: formData.name,
          alias: formData.alias
        };
      }

      vm.$onInit = function onInit() {
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
          vm.form.field = helper.getDefaultLanguageFormData()
        };

        initFn[vm.const.editType.edit] = function initEditFn() {
          if (!vm.config.selectedItem) {
            vm.form.field = helper.getDefaultLanguageFormData()
          } else {
            var selectedItem = vm.config.selectedItem;
            vm.form.field = {
              name: selectedItem.name,
              alias: selectedItem.alias
            };
          }
        };

        if (!vm.config) {
          vm.config = {
            editType: vm.const.editType.add,
            afterCancelCallback: angular.noop,
            afterApplyCallback: angular.noop
          };
        }

        initFn[vm.config.editType]();
        vm.applyEventHandler = applyEventHandler;
        vm.cancelEventHandler = cancelEventHandler;
      };
    }]);
})();
