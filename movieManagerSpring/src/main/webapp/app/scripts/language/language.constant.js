/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('languageApp.module')
    .constant('languageConstant', (function() {
      return {
        languageConst: {
          appInfo: {
            id: 'languageApp',
            templateId: 'language-app'
          },
          api: {
            base: {
              url: 'api/languages'
            }
          },
          view: {
            type: {
              list: {
                name: 'list'
              },
              addEdit: {
                name: 'addEdit',
                editType: {
                  add: 'add',
                  edit: 'edit'
                }
              }
            }
          },
          templateUrl: {
            mainApp: 'scripts/language/language.html',
            appComponent: 'scripts/language/language.component.template.html',
            listViewComponent: 'scripts/language/listView/lg.listView.component.template.html',
            addEditViewComponent: 'scripts/language/addEditView/lg.addEditView.component.template.html'
          }
        }
      };
    })());
})();
