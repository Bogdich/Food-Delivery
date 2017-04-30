//
//  CategoryList.h
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Mantle/Mantle.h>
#import "Category.h"

@interface CategoryList : MTLModel

@property (strong, nonatomic)NSArray<Category *> *categories;

@end
