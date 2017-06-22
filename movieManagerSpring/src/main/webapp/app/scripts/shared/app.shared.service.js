/**
 © Copyright ALE USA Inc., 2015
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */


(function() {
  'use strict';

  angular.module('movieManagerApp.shared.module')
    .factory('appSharedService', ['$q', 'appConstant', '$log', function appSharedService($q, appConstant, $log) {

      var serviceConst = {
        response: appConstant.appConst.responseStandard
      };

      function getValidApiUrl(url) {
        var result = url;
        if (url.charAt(0) === '/') {
          result = url.substring(1);
        }
        return result;
      }

      function parsedSuccessGetResponseBody(deferred, response, mapRawDataListCallback) {
        var resultData = null;
        if (response.data.status === serviceConst.response.status.success) {
          resultData = response.data.data;
          if (typeof mapRawDataListCallback === 'function') {
            resultData = mapRawDataListCallback(resultData);
          }
          deferred.resolve(resultData);
        } else {
          resultData = response.data.message;
          deferred.reject(resultData);
        }
      }

      function parsedSuccessOperateResponseBody(deferred, response) {
        var resultData = null;
        if (response.data.status === serviceConst.response.status.success) {
          resultData = response.data.message;
          deferred.resolve(resultData);
        } else {
          resultData = response.data.message;
          deferred.reject(resultData);
        }
      }

      function parsedErrorResponseBody(deferred, response) {
        var errorMessage = 'Failed to load data from server!';
        $log.error('response error: ', response);
        deferred.reject(errorMessage);
      }

      function getProcessedGetAPIPromise(apiPromise, mapRawDataListCallback) {
        var deferred = $q.defer();
        apiPromise.then(function successCallback(successResponse) {
          parsedSuccessGetResponseBody(deferred, successResponse, mapRawDataListCallback);
        }, function errorCallback(errorResponse) {
          parsedErrorResponseBody(deferred, errorResponse);
        });

        return deferred.promise;
      }

      function getProcessedOperateAPIPromise(apiPromise, mapRawDataListCallback) {
        var deferred = $q.defer();
        apiPromise.then(function successCallback(successResponse) {
          parsedSuccessOperateResponseBody(deferred, successResponse, mapRawDataListCallback);
        }, function errorCallback(errorResponse) {
          parsedErrorResponseBody(deferred, errorResponse);
        });

        return deferred.promise;
      }

      return {
        parsedSuccessResponseBody: parsedSuccessGetResponseBody,
        parsedErrorResponseBody: parsedErrorResponseBody,
        getProcessedGetAPIPromise: getProcessedGetAPIPromise,
        getProcessedOperateAPIPromise: getProcessedOperateAPIPromise,
        getValidApiUrl: getValidApiUrl
      };
    }]);
})();
