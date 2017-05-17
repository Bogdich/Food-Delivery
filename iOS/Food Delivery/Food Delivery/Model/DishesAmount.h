//
//  DishesAmount.h
//  Food Delivery
//
//  Created by Kirill Budevich on 12.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Mantle/Mantle.h>

@interface DishesAmount : MTLModel <MTLJSONSerializing>

@property (strong, nonatomic) NSNumber *dishId;
@property (strong, nonatomic) NSNumber *amount;

- (instancetype)initWithDishId:(NSNumber *)dishId amount:(NSNumber *)amount;

@end
