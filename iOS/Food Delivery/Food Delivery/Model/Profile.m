//
//  Profile.m
//  Food Delivery
//
//  Created by Kirill Budevich on 10.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "Profile.h"
#import "APIManager.h"
#import <SVProgressHUD.h>

@implementation Profile

- (id)init {
    self = [super init];
    if(!self) return nil;
    
    return self;
}

+ (Profile *)sharedInstance {
    
    static Profile *_profile = nil;
    
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _profile = [[self alloc] init];
    });
    
    return _profile;
}
- (void)setAuthorized:(BOOL)authorized {
    
    _authorized = authorized;
    
    if (authorized) {
        
        NSLog(@"send confirmation");
    }
}

- (void)registrationWithLogin:(NSString *)login andPass:(NSString *)pass name:(NSString *)name surname:(NSString *)surname address:(NSString *)address number:(NSString *)number email:(NSString *)email {
    
    __block NSString *answer = @"ERROR";
    
    __weak __typeof__(self) weakSelf = self;
    
    __block NSInteger newUserId = 0;
    
    [[APIManager sharedManager] addUserWithLogin:login
                                         andPass:pass
                                            name:name
                                         surname:surname
                                         address:address
                                          number:number
                                           email:email
                                         success:^(id object) {
                                             
                                             __strong __typeof__(weakSelf) strongSelf = weakSelf;
                                             
                                             if ([object integerValue]) {
                                                 
                                                 newUserId = [object integerValue];
                                                 answer = @"OK";
                                                 
                                                 strongSelf.id_ = [NSNumber numberWithInteger:newUserId];
                                                 
                                                 [SVProgressHUD showSuccessWithStatus:@"Вы успешно зарегестрировались"];
                                                 
                                                 [[NSNotificationCenter defaultCenter] postNotificationName:@"registrationOK" object:nil];
                                             } else {
                                                 
                                                 [SVProgressHUD showErrorWithStatus:@"Такой логин уже существует"];
                                             }
                                         }
                                         failure:^(NSError *error) {
                                             NSLog(@"%@", error);
                                             [SVProgressHUD showErrorWithStatus:@"Что-то пошло не так"];
                                         }];
#warning zachem nam return id in registration?
}

- (void)upAutorizationWithLogin:(NSString *)login andPass:(NSString *)pass {
    
    __block NSString *answer = @"ERROR";
    
    __weak __typeof__(self) weakSelf = self;

    [[APIManager sharedManager] upAutorizationWithLogin:login andPass:pass success:^(id object) {
        
        if ([object integerValue]) {
            
            [[APIManager sharedManager] getUserById:[object integerValue] success:^(User *user) {
                
                __strong __typeof__(weakSelf) strongSelf = weakSelf;
                
                strongSelf.name = user.name;
                strongSelf.surname = user.surname;
                strongSelf.number = user.number;
                strongSelf.email = user.email;
                strongSelf.address = user.address;
                strongSelf.login = login;
                strongSelf.password = pass;
                strongSelf.id_ = user.info.id_;
                
                strongSelf.authorized = YES;
                
                [SVProgressHUD showSuccessWithStatus:@"Вы успешно авторизовались"];
                
                [[NSNotificationCenter defaultCenter] postNotificationName:@"autorizationOK" object:nil];
                
            } failure:^(NSError *error) {
                
                answer = [NSString stringWithFormat:@"%@", error];
                [SVProgressHUD showErrorWithStatus:@"Что-то пошло не так"];
            }];
            
            answer = @"USER LOGIN";
            
        } else {
            
            answer = @"LOGIN NOT EXIST";

            [SVProgressHUD showErrorWithStatus:@"Проверьте введеные данные"];
        }
        
    } failure:^(NSError *error) {
        
        NSLog(@"%@", error);
        [SVProgressHUD showErrorWithStatus:@"Что-то пошло не так"];
    }];
}

- (void)upAutorizationFromUD {
    
    
}

- (void)saveProfileInfoToUD {
    
    
}

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
