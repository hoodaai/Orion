'use strict';

var gaModule = angular.module('gaDirectiveModule',['analyticsModule']);

gaModule.directive('swTrackPageView', function(AnalyticsServices){
	return {
		link: function(scope, element, attrs) {
			element.bind('click', function(e){
			    e.preventDefault();
			    e.stopPropagation();
				AnalyticsServices.trackPageView(attrs.pageurl);
			});
		}
	}
});


gaModule.directive('swTrackEvent', function(AnalyticsServices){
	return {
		link: function(scope, element, attrs) {
			element.bind('click', function(){
				AnalyticsServices.trackEvent(attrs.eventcategory, attrs.eventaction, attrs.eventlabel);
			});
		}
	}
});

gaModule.directive('swTrackPageViewOnLoad', function(AnalyticsServices){
	return {
		link: function(scope, element, attrs) {
			element.bind('load', function(){
				AnalyticsServices.trackPageView(attrs.pageurl);
			});
		}
	}
});
