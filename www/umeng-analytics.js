var exec = require('cordova/exec');

module.exports = {
	profileSignInWithPUID: function (puid, provider, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "profileSignInWithPUID", [puid, provider]);
	},

	profileSignOff: function (successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "profileSignOff", []);
	},

	logPageViewWithDuration: function (page, seconds, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "logPageViewWithDuration", [page, seconds]);
	},

	beginLogPageView: function (page, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "beginLogPageView", [page]);
	},

	endLogPageView: function (page, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "endLogPageView", [page]);
	},

	event: function (id, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "event", [id]);
	},

	eventWithLabel: function (id, label, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "eventWithLabel", [id, label]);
	},

	eventWithAttributes: function (id, attributes, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "eventWithAttributes", [id, attributes]);
	},

	eventWithAttributesAndCounter: function (id, attributes, counter, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "eventWithAttributesAndCounter", [id, attributes, counter]);
	},

	beginEvent: function (id, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "beginEvent", [id]);
	},

	endEvent: function (id, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "endEvent", [id]);
	},

	beginEventWithLabel: function (id, label, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "beginEventWithLabel", [id, label]);
	},

	endEventWithLabel: function (id, label, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "endEventWithLabel", [id, label]);
	},

	beginEventWithKeyAndAttributes: function (id, key, attributes, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "beginEventWithKeyAndAttributes", [id, key, attributes]);
	},

	endEventWithKey: function (id, key, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "endEventWithKey", [id, key]);
	},

	eventWithDuration: function (id, milliseconds, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "eventWithDuration", [id, milliseconds]);
	},

	eventWithLabelAndDuration: function (id, label, milliseconds, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "eventWithLabelAndDuration", [id, label, milliseconds]);
	},

	eventWithAttributesAndDuration: function (id, attributes, milliseconds, successCallback, errorCallback) {
		exec(successCallback, errorCallback, "UmengAnalytics", "eventWithAttributesAndDuration", [id, attributes, milliseconds]);
	}
};