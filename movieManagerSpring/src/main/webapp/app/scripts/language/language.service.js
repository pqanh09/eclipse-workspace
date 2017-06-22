/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('languageApp.module')
    .factory('languageService', ['$q', '$http', 'languageConstant', 'appSharedService', 'appConstant', function languageService($q, $http, languageConstant, appSharedService, appConstant) {
      var myConst      = languageConstant,
          sharedHelper = appSharedService;

      var serviceConst = {
        api: myConst.languageConst.api,
        response: appConstant.appConst.responseStandard
      };

      function sendGetDataAPIRequestPromise() {
        var url = sharedHelper.getValidApiUrl(serviceConst.api.base.url);
        var rawPromise = $http.get(url);
        return sharedHelper.getProcessedGetAPIPromise(rawPromise, getMappedDataList);
      }

      function getDefaultLanguageFormData() {
        return {
          name: '',
          alias: ''
        };
      }

      function getMappedItem(rawItem) {
        return {
          id: rawItem.id,
          name: rawItem.name,
          alias: rawItem.alias
        };
      }

      function getMappedDataList(rawDataList) {
        var resultDataList = _.map(rawDataList, function mapRawDataList(rawItem) {
          return getMappedItem(rawItem);
        });
        return resultDataList;
      }

      function sendAddAPIRequestPromise(requestBody) {
        var url = sharedHelper.getValidApiUrl(serviceConst.api.base.url);
        var rawPromise = $http.post(url, requestBody);
        return sharedHelper.getProcessedOperateAPIPromise(rawPromise);
      }

      function sendEditAPIRequestPromise(id, requestBody) {
        var url = sharedHelper.getValidApiUrl(serviceConst.api.base.url + '/' + id);
        var rawPromise = $http.put(url, requestBody);
        return sharedHelper.getProcessedOperateAPIPromise(rawPromise);
      }

      function sendDeleteAPIRequestPromise(requestBody) {
        var url = sharedHelper.getValidApiUrl(serviceConst.api.base.url);
        var rawPromise = $http.delete(url, {
          data: requestBody,
          headers: {
            'Content-type': 'application/json;charset=utf-8'
          }
        });
        return sharedHelper.getProcessedOperateAPIPromise(rawPromise);
      }

      return {
        sendGetDataAPIRequestPromise: sendGetDataAPIRequestPromise,
        sendAddAPIRequestPromise: sendAddAPIRequestPromise,
        sendEditAPIRequestPromise: sendEditAPIRequestPromise,
        sendDeleteAPIRequestPromise: sendDeleteAPIRequestPromise,
        getDefaultLanguageFormData: getDefaultLanguageFormData
      };
    }]);
})();
