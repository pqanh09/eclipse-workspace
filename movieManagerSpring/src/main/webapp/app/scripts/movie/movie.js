/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */


(function() {
  'use strict';

  angular.module('movieApp.module')
    .config(['$routeProvider', 'movieConstant', function ($routeProvider, movieConstant) {
      var route = '/movie';
      var config = {
        templateUrl: movieConstant.movieConst.templateUrl.mainApp,
        appId: movieConstant.movieConst.appInfo.id
      };

      $routeProvider
        .when(route, config);
    }]);
})();
