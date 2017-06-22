/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */

(function() {
  'use strict';

  angular.module('movieApp.module')
    .factory('movieService', ['$q', '$http', 'movieConstant', 'appSharedService', 'appConstant', function movieService($q, $http, movieConstant, appSharedService, appConstant) {
      var myConst      = movieConstant,
        sharedHelper = appSharedService;

      var serviceConst = {
        api: myConst.movieConst.api,
        response: appConstant.appConst.responseStandard,
        imageUrl: myConst.movieConst.appInfo.baseFilePath
      };

      function sendGetDataAPIRequestPromise() {
        var rawPromise = $http.get(sharedHelper.getValidApiUrl(serviceConst.api.base.url));
        return sharedHelper.getProcessedGetAPIPromise(rawPromise, getMappedDataList);
      }

      function getDefaultMovieFormData() {
        return {
          title: '',
          description: '',
          language: '',
          price: '',
          imageFilename: '',
          imageUrl: ''
        };
      }

      function getUrlImagePath(imageFilename) {
        return serviceConst.imageUrl + imageFilename;
      }

      function getMappedItem(rawItem) {
        return {
          id: rawItem.id,
          title: rawItem.title,
          description: rawItem.description,
          price: rawItem.price,
          imageFilename: rawItem.imageFilename,
          imageUrl: getUrlImagePath(rawItem.imageFilename)
        };
      }

      function getFileTransformConfig() {
        return {
          transformRequest: angular.identity,
          headers: { 'Content-Type': undefined }
        };
      }

      function getMappedDataList(rawDataList) {
        var resultDataList = _.map(rawDataList, function mapRawDataList(rawItem) {
          return getMappedItem(rawItem);
        });
        return resultDataList;
      }

      function sendAddAPIRequestPromise(requestBody) {
        var rawPromise = $http.post(sharedHelper.getValidApiUrl(serviceConst.api.base.url), requestBody, getFileTransformConfig())
        return sharedHelper.getProcessedOperateAPIPromise(rawPromise);
      }

      function sendEditAPIRequestPromise(id, requestBody) {
        var url = sharedHelper.getValidApiUrl(serviceConst.api.base.url + '/' + id);
        var rawPromise = $http.put(url, requestBody, getFileTransformConfig());
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
        getDefaultMovieFormData: getDefaultMovieFormData
      };
    }]);
})();
