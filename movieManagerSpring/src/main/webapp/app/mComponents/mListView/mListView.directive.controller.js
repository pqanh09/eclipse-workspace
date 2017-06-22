/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('mComponents.mListView.module')
    .controller('mListViewDirectiveController', ['mListViewDirectiveService', function mListViewDirectiveController(mListViewDirectiveService) {
      var vm = this;
      var helper = mListViewDirectiveService;
      vm.name = 'Language';
      vm.config = {
        primaryKey: 'id',
        isAllowedSelect: true,
        columnList: [
          {
            key: 'name',
            name: 'Name',
            isHidden: false
          },
          {
            key: 'alias',
            name: 'Alias'
          }
        ]
      };

      vm.data = [
        {
          id: 1,
          name: 'English',
          alias: 'en'
        },
        {
          id: 2,
          name: 'Vietnamese',
          alias: 'vi'
        }
      ];

      vm.selectAllRows = function selectAllRows(dataList, targetCheckedStatus) {
        _.map(dataList, function (dataItem) {
          dataItem.isSelected = targetCheckedStatus;
        });

        if (targetCheckedStatus) {
          vm.selectedList = angular.copy(dataList);
        } else {
          vm.selectedList = [];
        }
      };

      vm.selectRow = function selectRow(selectedItem, targetCheckedStatus) {
        selectedItem.isSelected = targetCheckedStatus;
        if (targetCheckedStatus) {
          vm.selectedList.push(selectedItem);
        } else {
          vm.selectedList = _.filter(vm.selectedList, function filterSelectedList(currentItem) {
            return (selectedItem.id !== currentItem.id);
          });
        }

        if (vm.selectedList.length === vm.data.length) {
          vm.isSelectedAll = true;
        } else {
          vm.isSelectedAll = false;
        }
      };

      function init() {
        vm.isCheckedAll = false;
        vm.selectedList = [];
        vm.columnList = helper.getVisibleColumnList(vm.config.columnList);
      }

      init();

    }]);
})();
