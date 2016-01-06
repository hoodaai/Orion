'use strict';

var analyticsModule = angular.module('analyticsModule',[]);

analyticsModule.provider('fetchAnalyticsConfig', function() {
var fetchAnalyticsConfig = {};
    // code to initialize/configure the PROVIDER goes here (executed during `config` phase)

    this.$get = function($http, $window, $rootScope) {
        // code to initialize/configure the SERVICE goes here (executed during `run` stage)
        fetchAnalyticsConfig.getProfile = function() {
           $rootScope.analyticsConfiguration.Environment = "local";
           configureAnalytics();
        };

        function configureAnalytics() {
             if ($rootScope.analyticsConfiguration.Environment == 'production') {
                 $window.ga('set', 'dimension1', "0");
             } else {
                 $window.ga('set', 'dimension1', "1");
             }
        }

        return fetchAnalyticsConfig;
    };
});

analyticsModule.config(function (fetchAnalyticsConfigProvider) {
    // Here, code written outside $get will be available
  }).run(function (fetchAnalyticsConfig) {
    // Here, the service singleton return with the $get function will be injected
    fetchAnalyticsConfig.getProfile();
});


analyticsModule.factory('AnalyticsServices', ['$http', '$window', '$rootScope', function($http, $window, $rootScope) {

   var AnalyticsServices = {};

   AnalyticsServices.trackEvent = function(eventCategory, eventAction, eventLabel) {

       $window.ga('send', {
           'hitType': 'event',
           'eventCategory': eventCategory,
           'eventAction': eventAction,
           'eventLabel': eventLabel
       });
   };

   AnalyticsServices.trackPageView = function(url) {
       $window.ga('send', 'pageview', {
           'page': url
       });
   };

   return AnalyticsServices;
}]);
