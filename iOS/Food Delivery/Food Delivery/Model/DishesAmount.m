//
//  DishesAmount.m
//  Food Delivery
//
//  Created by Kirill Budevich on 12.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "DishesAmount.h"

@implementation DishesAmount

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
    return @{
             @"dishId": @"dishId",
             @"amount": @"amount"
             };
}

- (instancetype)initWithDishId:(NSNumber *)dishId amount:(NSNumber *)amount {
    
    if (self = [super init]) {
        self.dishId = dishId;
        self.amount = amount;
    }
    return self;
}

@end
