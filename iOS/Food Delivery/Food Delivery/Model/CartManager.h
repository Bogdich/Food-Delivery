//
//  CartManager.h
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Dish.h"

@interface CartManager : NSObject

+ (id)sharedManager;

- (void)deleteDishFromCart:(Dish *)dish;
- (void)addDishToCart:(Dish *)dish count:(NSInteger)count;

- (NSArray *)getAllDishes;
- (Dish *)getDishInIndex:(NSInteger)index;

- (NSInteger)getAllDishesCount;
- (NSInteger)getDishesCountForId:(NSNumber *)dishId;

@end
