/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('languageApp.module')
    .controller('LanguageAppComponentController', ['languageConstant', function LanguageAppComponentController(languageConstant) {
      var vm = this,
          myConst = languageConstant;

      vm.const = {
        viewType: myConst.languageConst.view.type,
        editType: myConst.languageConst.view.type.addEdit.editType
      };

      vm.setCurrentView = function setCurrentView(view) {
        vm.currentView = view;
      };

      function afterAddCallback() {
        vm.setCurrentView(vm.const.viewType.addEdit.name);
      }

      function afterEditCallback() {
        vm.setCurrentView(vm.const.viewType.addEdit.name);
      }

      function afterDeleteCallback() {
      }

      function afterApplyCallback() {
        vm.setCurrentView(vm.const.viewType.list.name);
      }

      function afterCancelCallback() {
        vm.setCurrentView(vm.const.viewType.list.name);
      }

      function addCallback() {
        vm.addEditView.config.editType = vm.const.editType.add;
        vm.setCurrentView(vm.const.viewType.addEdit.name);
      }

      function editCallback(selectedItem) {
        vm.addEditView.config.selectedItem = selectedItem;
        vm.addEditView.config.editType = vm.const.editType.edit;
        vm.setCurrentView(vm.const.viewType.addEdit.name);
      }

      function refreshCallback(selectedItem) {
        vm.setCurrentView(vm.const.viewType.list.name);
      }

      function init() {
        vm.setCurrentView(vm.const.viewType.list.name);

        vm.listView = {
          config: {
            addCallback: addCallback,
            editCallback: editCallback,
            refreshCallback: refreshCallback
          }
        };

        vm.addEditView = {
          config: {
            afterApplyCallback: afterApplyCallback,
            afterCancelCallback: afterCancelCallback
          }
        };
      }

      init();

    }]);
})();
