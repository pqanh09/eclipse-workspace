/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('mComponents.mListView.module')
    .directive('mListView', ['mListViewDirectiveConstant', function mListViewDirective(mListViewDirectiveConstant) {
      return {
        restrict: 'A',
        scope: {},
        templateUrl: mListViewDirectiveConstant.mListViewDirConst.templateUrl.directive,
        controllerAs: 'vm',
        bindToController: {
          config: '=?',
          data: '=?'
        },
        controller: 'mListViewDirectiveController'
      };
    }]);
})();
