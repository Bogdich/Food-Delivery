//
//  DishesList.h
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Mantle/Mantle.h>
#import "Dish.h"

@interface DishesList : MTLModel

@property (strong, nonatomic)NSArray<Dish *> *dishes;

@end
