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

+ (User *)getInstance {
    if (instance == nil) {
        instance = [[User alloc] init];
    }
    return instance;
}

- (void)setAuthorized:(BOOL)authorized {
    
    _authorized = authorized;
    
    if (authorized) {
        
        NSLog(@"send confirmation");
    }
}

- (NSString *)registrationWithLogin:(NSString *)login andPass:(NSString *)pass name:(NSString *)name surname:(NSString *)surname address:(NSString *)address number:(NSString *)number email:(NSString *)email {
    
    __block NSString *answer = @"ERROR";
    
    if (![self isLoginExist:login]) {
        
        __weak __typeof__(self) weakSelf = self;
        
        __block NSInteger newUserId = 0;
        
        dispatch_async(dispatch_get_main_queue(), ^{
            
            [[APIManager sharedManager] addUserWithLogin:login
                                                 andPass:pass
                                                    name:name
                                                 surname:surname
                                                 address:address
                                                  number:number
                                                   email:email
                                                 success:^(id object) {
                
                __strong __typeof__(weakSelf) strongSelf = weakSelf;
                
                                                     if (![object isEqualToString:@"SOMETHING WRONG"]) {
                    
                                                         newUserId = [object integerValue];
                                                         answer = @"OK";

                                                         strongSelf.info.id_ = [NSNumber numberWithInteger:newUserId];
                                                     }
                                                 }
                                                 failure:^(NSError *error) {
                                                     NSLog(@"%@", error);
                                                 }];
        });
    } else {
        
        answer = @"LOGIN EXIST";
    }
#warning zachem nam return id in registration?
    return answer;
}

- (NSString *)upAutorizationWithLogin:(NSString *)login andPass:(NSString *)pass {
    
    __block NSString *answer = @"ERROR";
    
    if ([self isLoginExist:login]) {
        
        __weak __typeof__(self) weakSelf = self;
        
        dispatch_async(dispatch_get_main_queue(), ^{
        
            [[APIManager sharedManager] upAutorizationWithLogin:login andPass:pass success:^(User *user) {
                
                __strong __typeof__(weakSelf) strongSelf = weakSelf;

                strongSelf.name = user.name;
                strongSelf.surname = user.surname;
                strongSelf.address = user.address;
                strongSelf.number = user.number;
                strongSelf.email = user.email;
                
                strongSelf.authorized = YES;
                
                answer = @"USER LOGIN";
                
            } failure:^(NSError *error) {
                
                NSLog(@"%@", error);
            }];
        });
        
    } else {
        
        answer = @"LOGIN NOT EXIST";
    }
    
    return answer;
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

#pragma mark - Logout

+ (void) logout{
//    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
//    [userDefaults removeObjectForKey:loginKey];
//    [userDefaults removeObjectForKey:passKey];
//    [userDefaults removeObjectForKey:UIDKey];
//    [userDefaults removeObjectForKey:@"intro"];
//    [userDefaults synchronize];
//    
//    NSUserDefaults *groupUserDefaults = [[NSUserDefaults alloc] initWithSuiteName:APP_GROUP_USER_DEFAULTS];
//    [groupUserDefaults removeObjectForKey:loginKey];
//    [groupUserDefaults removeObjectForKey:passKey];
//    [groupUserDefaults removeObjectForKey:UIDKey];
//    [groupUserDefaults removeObjectForKey:@"intro"];
//    [groupUserDefaults synchronize];
//    
//    [[NSNotificationCenter defaultCenter] postNotificationName:@"SGSAccountSavedNow" object:nil userInfo:nil];
}

//- (void)relogin {
//    
//    UIViewController *leftViewController = [self leftMenuViewController];
//    
//    if ([leftViewController respondsToSelector:@selector(checkAuth)]) {
//        
//        [leftViewController performSelector:@selector(checkAuth)];
//    }
//}

@end
