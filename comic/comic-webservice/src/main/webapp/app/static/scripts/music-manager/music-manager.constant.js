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
			register: 'http://35.224.130.5:8080/Spring4MVCAngularJSExample/register',
			//35.224.130.5
			//localhost
			subscribe: '/topic/usdtMarkets'
		},
		templateUrl : {
			home : {
				url : 'app/static/scripts/home.html'
			},
			musicManager: {
				url: 'app/static/scripts/music-manager/music-manager.html',
				song: 'app/static/scripts/music-manager/song/song.html',
				admin: 'app/static/scripts/music-manager/admin/admin.html',
				sampleone: {
					url: 'app/static/scripts/music-manager/sampleone/sampleone.html',
					detail: 'app/static/scripts/music-manager/sampleone/detail/detail.html',
					detailTmp: 'app/static/scripts/music-manager/sampleone/detail/temp.html',
					detail2: 'app/static/scripts/music-manager/sampleone/detail2/detail2.html',
					detailTmp2: 'app/static/scripts/music-manager/sampleone/detail2/temp.html',
					modify: 'app/static/scripts/music-manager/sampleone/modify/modify.html',
					modifyTmp: 'app/static/scripts/music-manager/sampleone/modify/temp.html',
					view: 'app/static/scripts/music-manager/sampleone/view/view.html',
					viewTmp: 'app/static/scripts/music-manager/sampleone/view/temp.html'
				}
			}

		}

	});
})();