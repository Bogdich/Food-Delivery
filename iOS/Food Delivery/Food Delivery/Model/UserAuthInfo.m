//
//  UserAuthInfo.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "UserAuthInfo.h"

@implementation UserAuthInfo

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
    return @{
             @"id": @"id",
             @"login": @"login",
             @"password": @"password",
             @"isAdmin": @"admin"
             };
}

@end
