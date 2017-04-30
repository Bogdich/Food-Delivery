//
//  Dish.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "Dish.h"
#import "Category.h"

@implementation Dish

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
    return @{
             @"id_": @"id",
             @"name": @"name",
             @"description_": @"description",
             @"weight": @"weight",
             @"price": @"price",
             @"category": @"category"
             };
}

+ (NSValueTransformer *)categoryJSONTransformer {
    return [MTLJSONAdapter dictionaryTransformerWithModelClass:[Category class]];
}

@end
