/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('movieManagerApp.module')
    .controller('AppController', function AppController() {
      var vm = this;

      vm.menu = [
        {
          'id': 1,
          'title': 'Movies',
          'link': '/#!/movie',
          'active': true
        },
        {
          'id': 2,
          'title': 'Languages',
          'link': '/#!/language',
          'active': false
        }
      ];

      vm.selectMenu = function (id) {
        for (var i = 0; i < vm.menu.length; i++) {
          if (vm.menu[i].id === id) {
            vm.menu[i].active = true;
          } else {
            vm.menu[i].active = false;
          }
        }
      };
    });
})();
