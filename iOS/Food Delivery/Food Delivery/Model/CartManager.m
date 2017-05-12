//
//  CartManager.m
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "CartManager.h"

@interface CartManager ()

@property (nonatomic, strong) NSMutableArray *dishes;
@property (nonatomic, strong) NSMutableDictionary *dishesCount;

@end

@implementation CartManager

- (id)init {
    if(!self) return nil;
    
    self.dishes = [NSMutableArray new];
    self.dishesCount = [NSMutableDictionary new];
    
    return self;
}

+ (id)sharedManager {
    static CartManager *_cartManager = nil;
    
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _cartManager = [[self alloc] init];
    });
    
    return _cartManager;
}

- (void)addDishToCart:(Dish *)dish count:(NSInteger)count {
    
    if (![self containsDishName:dish.name dishId:dish.id_]) {
        
        [self.dishes addObject:dish];
    }
    
    if (count == 0) {
        
        [self deleteDishFromCart:dish];
    } else {
        
        [self.dishesCount setObject:[NSNumber numberWithInteger:count] forKey:dish.id_];
    }
}

- (NSArray *)getAllDishes {
    
    return self.dishes;
}

- (NSInteger)getAllDishesCount {
    
    return _dishes.count;
}

- (Dish *)getDishInIndex:(NSInteger)index {
    
    return self.dishes[index];
}

- (void)deleteDishFromCart:(Dish *)dish {
    
    [self.dishes removeObject:dish];
    [self.dishesCount removeObjectForKey:dish.id_];
}

- (NSInteger)getDishesCountForId:(NSNumber *)dishId {
    
    NSInteger count = [self.dishesCount objectForKey:dishId] ? [[self.dishesCount objectForKey:dishId] integerValue] : 0;

    return count;
}

- (BOOL)containsDishName:(NSString *)dishName dishId:(NSNumber *)dishId{
    
    for (Dish *dish in self.dishes) {
        
        if ([dish.name isEqualToString:dishName] && dish.id_ == dishId) {
            return YES;
        }
    }
    
    return NO;
}


@end
