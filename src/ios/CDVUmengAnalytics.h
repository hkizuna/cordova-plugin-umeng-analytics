//
//  CDVUmengAnalytics.h
//
//  Created by xwang on 01/25/16.
//
//

#import <Cordova/CDV.h>

@interface CDVUmengAnalytics:CDVPlugin

@property (nonatomic, strong) NSString *umengAnalyticsAppId;

- (void)profileSignInWithPUID:(CDVInvokedUrlCommand *)command;
- (void)profileSignOff:(CDVInvokedUrlCommand *)command;
- (void)logPageViewWithDuration:(CDVInvokedUrlCommand *)command;
- (void)beginLogPageView:(CDVInvokedUrlCommand *)command;
- (void)endLogPageView:(CDVInvokedUrlCommand *)command;
- (void)event:(CDVInvokedUrlCommand *)command;
- (void)eventWithLabel:(CDVInvokedUrlCommand *)command;
- (void)eventWithAttributes:(CDVInvokedUrlCommand *)command;
- (void)eventWithAttributesAndCounter:(CDVInvokedUrlCommand *)command;
- (void)beginEvent:(CDVInvokedUrlCommand *)command;
- (void)endEvent:(CDVInvokedUrlCommand *)command;
- (void)beginEventWithLabel:(CDVInvokedUrlCommand *)command;
- (void)endEventWithLabel:(CDVInvokedUrlCommand *)command;
- (void)beginEventWithKeyAndAttributes:(CDVInvokedUrlCommand *)command;
- (void)endEventWithKey:(CDVInvokedUrlCommand *)command;
- (void)eventWithDuration:(CDVInvokedUrlCommand *)command;
- (void)eventWithLabelAndDuration:(CDVInvokedUrlCommand *)command;
- (void)eventWithAttributesAndDuration:(CDVInvokedUrlCommand *)command;

@end