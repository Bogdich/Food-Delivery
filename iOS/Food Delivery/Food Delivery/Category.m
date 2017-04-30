//
//  Category.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "Category.h"

@implementation Category

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
    return @{
             @"id_": @"id",
             @"name": @"name"
             };
}

@end
