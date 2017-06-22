/**
 (c) Copyright ALE USA Inc., 2016
 All Rights Reserved. No part of this file may be reproduced, stored in a retrieval system,
 or transmitted in any form or by any means, electronic, mechanical,
 photocopying, or otherwise without the prior permission of ALE USA Inc..
 */
(function () {
    'use strict';
    angular.module('MusicSong')
        .controller('SongAddCtrl',SongAddCtrl)
        .directive('musicSongAdd',musicSongAdd);
    SongAddCtrl.$inject = [
        'songService',
        '$scope',
        '$location',
        'musicConst'
    ];
    function SongAddCtrl(songService, $scope, $location, musicConst) {
        var vm = this;
        vm.errorMessage = '';
        vm.cancel = function () {
            $location.path(musicConst.urlApp.song);
        };
        vm.addSong = function () {
            var data = {
                name: '',
                type: '',
                author: ''
            };
            data.name = vm.name;
            data.type = vm.type || '';
            data.author = vm.author || '';
            songService.addSong(data,$scope.myFile).then(function success(data) {
                console.log(data);
                $location.path(musicConst.urlApp.song);
            }, function error() {
                vm.errorMessage = 'Upload fail';
                console.log('fail');
            });
        };
    }
    musicSongAdd.$inject = [
        'songConst'
    ];
    function musicSongAdd(songConst) {
        return {
            restrict: 'A',
            controller: 'SongAddCtrl',
            controllerAs: 'vmAddSong',
            templateUrl: songConst.template.formAddDirective
        };
    }
})()