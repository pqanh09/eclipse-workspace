/**
 * Created by pqanh on 8/29/16.
 */
(function(){
  'use strict';

  angular.module('music.manager').service('musicManagerService', function() {
    var service = {};
    service.martketConst = [{id:'USDT-BTC',name:'Bitcoin'},{id:'USDT-BCC',name:'Bitcoin Cash'},{id:'USDT-BTG',name:'Bitcoin Gold'},{id:'USDT-DASH',name:'Dash'},{id:'USDT-ETH',name:'Ethereum'},{id:'USDT-ETC',name:'Ethereum Classic'},{id:'USDT-LTC',name:'Litecoin'},{id:'USDT-XMR',name:'Monero'},{id:'USDT-NEO',name:'Neo'},{id:'USDT-OMG',name:'OmiseGo'},{id:'USDT-XRP',name:'Ripple'},{id:'USDT-ZEC',name:'ZCash'}];
    service.bitfinexPairs = [{id:'BTCUSD',name:'Bitcoin'},{id:'BCHUSD',name:'Bitcoin Cash'},{id:'BTGUSD',name:'Bitcoin Gold'},{id:'DSHUSD',name:'Dash'},{id:'ETHUSD',name:'Ethereum'},{id:'ETCUSD',name:'Ethereum Classic'},{id:'LTCUSD',name:'Litecoin'},{id:'XMRUSD',name:'Monero'},{id:'NEOUSD',name:'Neo'},{id:'OMGUSD',name:'OmiseGo'},{id:'XRPUSD',name:'Ripple'},{id:'ZECUSD',name:'ZCash'}];
    service.defaultData = [];
    service.defaultBitfinexExchangeData = [];
    (function init(){
      var i;
      for(i = 0; i < service.martketConst.length; i ++){
        var coin = service.martketConst[i];
        service.defaultData.push({
          show: false,
          stt: i,
          id: coin.id,
          name: coin.name,
          input: 0,
          lastPrice: 0,
          percent: 0,
          unit: 0,
          cost: 0
        });
      }
      for(i = 0; i < service.bitfinexPairs.length; i ++){
    	  var pair = service.bitfinexPairs[i];
          	service.defaultBitfinexExchangeData.push({
          	chanId: 0,
            stt: i,
            id: pair,
            input: 0,
            lastPrice: 0,
            percent: 0,
            unit: 0,
            cost: 0
          });
      }
    })();
    
    
    service.defaultRequest = {
      'UsdtTotalRequestObject': {
          'model': {
	          'name': "Test1",
	          'coins': [],
	          'inputs': [],
	          'costs': [],
	          'units': []
	       },
	       'ids': []
      }
    };
    service.jobId;
    service.alertDefaultData = {
      key: 'alert-success',
      message: 'Every thing is Ok',
      show: false,
      autoHide: true,
      timeout: 5000,
      label: 'Auto hide alert after 5s.'
    };
    function autoHideAlert(alertData, $timeout){
      $timeout(function () {
        alertData.show = false;
      }, alertData.timeout);
    };
    service.configShowAlert = function(alertData){
      alertData.autoHide = !alertData.autoHide;
      if(alertData.autoHide){
        alertData.label = 'Auto hide alert after 5s.';
        alertData.show = false;
      } else {
        alertData.label = 'Keep showing alert.';
        alertData.show = true;
      }
    };
    service.showAlert = function(alertData, $timeout, type, message){
      if(type === 'success'){
        alertData.key = 'alert-success';
      } else if(type === 'error'){
        alertData.key = 'alert-danger';
      } else if(type === 'warning'){
        alertData.key = 'alert-warning';
      } else {
        alertData.key = 'alert-info';
      }
      alertData.message = message;
      alertData.show = true;
      if(alertData.autoHide) {
        autoHideAlert(alertData, $timeout);
      }
    };

    return service;
  });
})();
