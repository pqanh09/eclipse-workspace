/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('languageApp.module')
    .component('languageAddEditView', {
      bindings: {
        config: '<?'
      },
      controller: 'LanguageAddEditViewComponentController',
      controllerAs: 'vm',
      templateUrl: ['languageConstant', function(languageConstant) {
        return languageConstant.languageConst.templateUrl.addEditViewComponent
      }]
    });

})();
