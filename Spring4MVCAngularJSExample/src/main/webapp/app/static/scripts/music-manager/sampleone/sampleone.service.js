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
    
    return service;
  });
})();
