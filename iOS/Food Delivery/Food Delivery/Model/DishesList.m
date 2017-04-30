//
//  DishesList.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "DishesList.h"

@implementation DishesList

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
    return @{
             @"dishes": @"",
             };
}

+ (NSValueTransformer *)dishesTransformer {
    
    return [MTLJSONAdapter arrayTransformerWithModelClass:[Dish class]];
}

@end
