/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */
(function() {
'use strict';

  angular.module('movieManagerApp.apps.module', [
    'movieApp.module',
    'languageApp.module'
  ]);

  angular.module('mComponents.module', [
    'mComponents.mListView.module'
  ]);

  angular.module('movieManagerApp.module', [
    'movieManagerApp.shared.module',
    'ngRoute',
    'movieManagerApp.apps.module',
    'mComponents.module',
    'ngMaterial'
  ]);

})();