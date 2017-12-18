/**
 * Created by pqanh on 8/29/16.
 */
(function(){
  'use strict';

  angular.module('music.manager.sampleone').service('sampleoneService', function() {
    var service = {};
    service.currentProfileId = '';
    service.currentView = {
    };
    service.currentSelected = [];
    service.listView = {
      viewAll: 'static/scripts/music-manager/sampleone/view/temp.html',
      detail: 'static/scripts/music-manager/sampleone/detail/temp.html',
      modify: 'static/scripts/music-manager/sampleone/modify/temp.html'
    };
    return service;
  });
})();
