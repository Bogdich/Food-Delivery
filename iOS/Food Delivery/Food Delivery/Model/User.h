//
//  User.h
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Mantle/Mantle.h>
#import "UserAuthInfo.h"

@interface User : MTLModel <MTLJSONSerializing>

@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSString *surname;
@property (strong, nonatomic) NSString *address;
@property (strong, nonatomic) NSString *number;
@property (strong, nonatomic) NSString *email;

@property (nonatomic, getter=isAuthorized) BOOL authorized;

@property (strong, nonatomic) UserAuthInfo *info;

+ (User *)getInstance;
+ (void)logout;
//- (void)relogin;

- (NSString *)registrationWithLogin:(NSString *)login andPass:(NSString *)pass name:(NSString *)name surname:(NSString *)surname address:(NSString *)address number:(NSString *)number email:(NSString *)email;
- (NSString *)upAutorizationWithLogin:(NSString *)login andPass:(NSString *)pass;
- (BOOL)isLoginExist:(NSString *)login;

@end
