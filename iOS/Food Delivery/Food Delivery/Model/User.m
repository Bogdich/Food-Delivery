//
//  User.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "User.h"
#import <RESideMenu.h>
#import "RightViewController.h"
#import "APIManager.h"

#define OK_ERROR @"OK"

@implementation User

static User *instance = nil;

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
    return @{
             @"name": @"name",
             @"surname": @"surname",
             @"address": @"address",
             @"number": @"number",
             @"email": @"email",
             @"info": @"user"
             };
}

- (BOOL)isLoginExist:(NSString *)login {
    
    __block BOOL isUserExist = NO;
    
    dispatch_async(dispatch_get_main_queue(), ^{
        
        [[APIManager sharedManager] getUserByLogin:login success:^(BOOL isExist) {
            
            isUserExist = isExist;
        }
         failure:^(NSError *error) {
             NSLog(@"error");
         }];
    });
    
    return isUserExist;
}

//- (BOOL)isCorrectPassword:(NSString *)password {
//    
//    __block BOOL isCorrectPassword = NO;
//    
//    dispatch_async(dispatch_get_main_queue(), ^{
//        
//        [[APIManager sharedManager] getUserByLogin:login success:^(BOOL isExist) {
//            
//            isUserExist = isExist;
//        }
//                                           failure:^(NSError *error) {
//                                               NSLog(@"error");
//                                           }];
//    });
//    
//    return isCorrectPassword;
//}

@end
