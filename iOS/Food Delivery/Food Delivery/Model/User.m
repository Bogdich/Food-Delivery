//
//  User.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "User.h"

@implementation User

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

@end
