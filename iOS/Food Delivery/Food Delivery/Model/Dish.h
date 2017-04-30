//
//  Dish.h
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Mantle/Mantle.h>
#import "Category.h"

@interface Dish : MTLModel <MTLJSONSerializing>

@property NSInteger id_;
@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSString *description_;
@property (strong, nonatomic) NSNumber *weight;
@property (strong, nonatomic) NSNumber *price;
@property (strong, nonatomic) Category *category;

@end
