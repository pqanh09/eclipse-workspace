/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('movieApp.module')
    .constant('movieConstant', (function() {
      return {
        movieConst: {
          appInfo: {
            id: 'movieApp',
            templateId: 'movie-app',
            baseFilePath: '/movie/imageFile/'
          },
          api: {
            base: {
              url: 'api/movies'
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
            mainApp: 'scripts/movie/movie.html',
            appComponent: 'scripts/movie/movie.component.template.html',
            listViewComponent: 'scripts/movie/listView/mv.listView.component.template.html',
            addEditViewComponent: 'scripts/movie/addEditView/mv.addEditView.component.template.html'
          }
        }
      };
    })());
})();
