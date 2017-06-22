/**
 (c) Copyright ALE USA Inc., 2016
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */
(function () {
    'use strict';
    angular.module('MusicApp',[
        'MusicAppConst',
        'MusicSong',
        'ngRoute'
    ])
        .config(['$routeProvider', 'musicConst', function ($routeProvider, musicConst) {
            $routeProvider
                .when(musicConst.urlApp.root,{
                    redirectTo: musicConst.urlApp.song
                })
        }])
        .controller('MainController',MainControllerFn)
        .factory('musicService',musicServiceFn);
    //-----------------------------------------------------------------------------------
    //Controller
    MainControllerFn.$inject = [
        'musicService',
        '$scope'
    ];
    function MainControllerFn(musicService, $scope) {
        var vm = this;
    }
    //-----------------------------------------------------------------------------------
    //service
    musicServiceFn.$inject = [
        '$http'
    ];
    function musicServiceFn( $http) {
        var service = {};
        return service;
    }
})()