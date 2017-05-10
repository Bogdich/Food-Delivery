//
//  Profile.h
//  Food Delivery
//
//  Created by Kirill Budevich on 10.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Profile : NSObject

@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSString *surname;
@property (strong, nonatomic) NSString *address;
@property (strong, nonatomic) NSString *number;
@property (strong, nonatomic) NSString *email;
@property (strong, nonatomic)NSNumber *id_;
@property (strong, nonatomic)NSString *login;
@property (strong, nonatomic)NSString *password;
@property (strong, nonatomic) NSMutableArray *orders;

@property (nonatomic, getter=isAuthorized) BOOL authorized;

+ (Profile *)sharedInstance;

+ (void)logout;
//- (void)relogin;

- (void)registrationWithLogin:(NSString *)login andPass:(NSString *)pass name:(NSString *)name surname:(NSString *)surname address:(NSString *)address number:(NSString *)number email:(NSString *)email;
- (void)upAutorizationWithLogin:(NSString *)login andPass:(NSString *)pass;

@end
