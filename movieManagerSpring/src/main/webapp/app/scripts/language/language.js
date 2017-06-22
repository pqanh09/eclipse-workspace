/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
'use strict';

angular.module('languageApp.module')
  .config(['$routeProvider', 'languageConstant', function ($routeProvider, languageConstant) {
    var route = '/language';
    var config = {
      templateUrl: languageConstant.languageConst.templateUrl.mainApp,
      appId: languageConstant.languageConst.appInfo.id
    };

    $routeProvider
      .when(route, config);
  }]);
})();
