/**
 (c) Copyright ALE USA Inc., 2016
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */
(function () {
    'use strict';
    angular.module('MusicSong',[
        'ngRoute',
        'MusicAppConst'
    ])
        .config(['$routeProvider', 'songConst', 'musicConst', function ($routeProvider, songConst, musicConst) {
            $routeProvider
                .when(musicConst.urlApp.song,{
                    templateUrl: songConst.template.listSong
                })
                .when(musicConst.urlApp.song + songConst.urlApp.add,{
                    templateUrl: songConst.template.formAdd
                });
        }])
        .constant('songConst',{
            api: 'songs',
            urlApp: {
                add: '/add'
            },
            template: {
                listSong: 'source/public/song/listSong.html',
                listSongDirective: 'source/public/song/listSong.directive.html',
                formAdd: 'source/public/song/addSong/formAdd.html',
                formAddDirective: 'source/public/song/addSong/formAdd.directive.html'
            }
        })
        .controller('SongController',SongControllerFn)
        .factory('songService',songServiceFn)
        .directive('fileModel',fileModelFn)
        .directive('musicSongList',musicSongList)
    ;
    //-----------------------------------------------------------------------------------
    //directive
    musicSongList.$inject = [
        'songConst'
    ];
    function musicSongList(songConst) {
        return {
            restrict: 'A',
            controller: 'SongController',
            controllerAs: 'vmListSong',
            templateUrl: songConst.template.listSongDirective
        };
    }

    fileModelFn.$inject = [
        '$parse'
    ];
    function fileModelFn($parse) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function(){
                    scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }
    //-----------------------------------------------------------------------------------
    //Controller
    SongControllerFn.$inject = [
        'songService',
        '$scope',
        '$location',
        'musicConst',
        'songConst'
    ];
    function SongControllerFn(songService, $scope, $location, musicConst, songConst) {
        var vm = this;
        vm.goAdd = function () {
            $location.path(musicConst.urlApp.song + songConst.urlApp.add);
        };
        songService.getSong().then(function success(data) {
            vm.songs = data.data.response;
        },function error() {
            console.log('fail');
        });
    }
    //-----------------------------------------------------------------------------------
    //service
    songServiceFn.$inject = [
        'songConst',
        '$http'
    ];
    function songServiceFn(songConst, $http) {
        var service = {};
        service.getSong = function () {
          return $http({
              url: songConst.api,
              method: 'GET',
              headers: {'Content-Type': 'application/json'}
          });
        };
        service.addSong = function (dataSong,file) {
            var fd = new FormData();
            fd.append('file', file);
            fd.append('name', dataSong.name);
            fd.append('type', dataSong.type);
            fd.append('author', dataSong.author);
            return $http({
                url: songConst.api,
                method: 'POST',
                data: fd,
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            });
        };
        return service;
    }

})()