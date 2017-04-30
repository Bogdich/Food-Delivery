//
//  CategoryList.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "CategoryList.h"

@implementation CategoryList

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
    return @{
             @"categories": @"",
             };
}

+ (NSValueTransformer *)categoriesTransformer {
    
    return [MTLJSONAdapter arrayTransformerWithModelClass:[Category class]];
}

@end
