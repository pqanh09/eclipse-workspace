/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('movieManagerApp.module')
    .component('mainApp', {
      bindings: {},
      controller: 'AppController',
      controllerAs: 'vm',
      templateUrl: ['appConstant', function (appConstant) {
        return appConstant.appConst.templateUrl.directive;
      }]
    });
})();
