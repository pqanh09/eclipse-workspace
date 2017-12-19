/**
 * Created by pqanh on 9/20/16.
 */
(function() {
	'use strict';

	angular.module('music.manager').constant('musicConstant', {
		id : 'music-manager',
		restApi : {
			lastPrice: {
				getLatest: 'api/bittrex/getLatestLastPriceData'
			},
			total: {
				getOne: 'api/bittrex/total/',
				dfault: 'api/bittrex/total'
			}
		},
		wsApi : {
			register: 'http://35.188.117.45:8080/Spring4MVCAngularJSExample/register',
			subscribe: '/topic/usdtMarkets'
		},
		templateUrl : {
			home : {
				url : 'app/static/scripts/home.html'
			},
			musicManager: {
				url: 'app/static/scripts/music-manager/music-manager.html',
				viewall: 'app/static/scripts/music-manager/viewall/viewall.html',
				song: 'app/static/scripts/music-manager/song/song.html',
				playlist: 'app/static/scripts/music-manager/playlist/playlist.html',
				admin: 'app/static/scripts/music-manager/admin/admin.html',
				sampleone: {
					url: 'app/static/scripts/music-manager/sampleone/sampleone.html',
					detail: 'app/static/scripts/music-manager/sampleone/detail/detail.html',
					detailTmp: 'app/static/scripts/music-manager/sampleone/detail/temp.html',
					modify: 'app/static/scripts/music-manager/sampleone/modify/modify.html',
					modifyTmp: 'app/static/scripts/music-manager/sampleone/modify/temp.html',
					view: 'app/static/scripts/music-manager/sampleone/view/view.html',
					viewTmp: 'app/static/scripts/music-manager/sampleone/view/temp.html'
				}
			}

		}

	});
})();