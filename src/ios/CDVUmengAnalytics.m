//
//  CDVUmengAnalytics.m
//
//  Created by xwang on 01/25/16.
//
//

#import "CDVUmengAnalytics.h"
#import "MobClick.h"

@implementation CDVUmengAnalytics

#pragma mark Initialization
- (void)pluginInitialize
{
    NSString* appId = [[self.commandDelegate settings] objectForKey:@"umenganalyticsappid"];
    if (appId)
    {
        self.umengAnalyticsAppId = appId;
    }
    [[NSNotificationCenter defaultCenter]addObserver:self
                                            selector:@selector(applicationDidFinishLaunching:)
                                                name:UIApplicationDidFinishLaunchingNotification
                                              object:nil];
}

- (void)applicationDidFinishLaunching:(NSNotification *)notification
{
    if (self.umengAnalyticsAppId) {
        [MobClick startWithAppkey:self.umengAnalyticsAppId reportPolicy:BATCH channelId:nil];
    }
}

#pragma mark APIs
- (void)profileSignInWithPUID:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 2) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick profileSignInWithPUID:[arguments objectAtIndex:0] provider:[arguments objectAtIndex:1]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)profileSignOff:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 0) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick profileSignOff];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)logPageViewWithDuration:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 2) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick logPageView:[arguments objectAtIndex:0] seconds:(int)[[arguments objectAtIndex:1] integerValue]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)beginLogPageView:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 1) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick beginLogPageView:[arguments objectAtIndex:0]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)endLogPageView:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 1) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick endLogPageView:[arguments objectAtIndex:0]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)event:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 1) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick event:[arguments objectAtIndex:0]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)eventWithLabel:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 2) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick event:[arguments objectAtIndex:0] label:[arguments objectAtIndex:1]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)eventWithAttributes:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 2) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    NSDictionary *attributes = [arguments objectAtIndex:1];
    [MobClick event:[arguments objectAtIndex:0] attributes:attributes];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)eventWithAttributesAndCounter:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 3) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    NSDictionary *attributes = [arguments objectAtIndex:1];
    int counter = (int) [[arguments objectAtIndex:2] integerValue];
    [MobClick event:[arguments objectAtIndex:0] attributes:attributes counter:counter];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)beginEvent:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 1) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick beginEvent:[arguments objectAtIndex:0]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)endEvent:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 1) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick endEvent:[arguments objectAtIndex:0]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)beginEventWithLabel:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 2) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick beginEvent:[arguments objectAtIndex:0] label:[arguments objectAtIndex:1]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)endEventWithLabel:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 2) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick endEvent:[arguments objectAtIndex:0] label:[arguments objectAtIndex:1]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)beginEventWithKeyAndAttributes:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 3) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    NSDictionary *attributes = [arguments objectAtIndex:2];
    [MobClick beginEvent:[arguments objectAtIndex:0] primarykey:[arguments objectAtIndex:1] attributes:attributes];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)endEventWithKey:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 2) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    [MobClick endEvent:[arguments objectAtIndex:0] primarykey:[arguments objectAtIndex:1]];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)eventWithDuration:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 2) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    int durations = (int) [[arguments objectAtIndex:1] integerValue];
    [MobClick event:[arguments objectAtIndex:0] durations:durations];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)eventWithLabelAndDuration:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 3) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    int durations = (int) [[arguments objectAtIndex:2] integerValue];
    [MobClick event:[arguments objectAtIndex:0] label:[arguments objectAtIndex:1] durations:durations];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

- (void)eventWithAttributesAndDuration:(CDVInvokedUrlCommand *)command
{
    NSArray *arguments = [command arguments];
    if ([arguments count] != 3) {
        [self failWithCallbackId:command.callbackId withMessage:@"参数错误"];
        return;
    }

    NSDictionary *attributes = [arguments objectAtIndex:1];
    int durations = (int) [[arguments objectAtIndex:2] integerValue];
    [MobClick event:[arguments objectAtIndex:0] attributes:attributes durations:durations];
    [self successWithCallbackId:command.callbackId withMessage:@"SUCCESS"];
}

#pragma mark Helper Function
- (void)successWithCallbackId:(NSString *)callbackId withMessage:(NSString *)message
{
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:message];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackId];
}

- (void)failWithCallbackId:(NSString *)callbackId withMessage:(NSString *)message
{
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:message];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackId];
}

- (void)failWithCallbackId:(NSString *)callbackId withError:(NSError *)error
{
    [self failWithCallbackId:callbackId withMessage:[error localizedDescription]];
}

@end