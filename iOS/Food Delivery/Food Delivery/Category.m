//
//  Category.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "Category.h"

@implementation Category

+ (NSDictionary *)JSONKeyPathsByPropertyKey {
    return @{
             @"id_": @"id",
             @"name": @"name",
             @"imageURL": @"imageURL"
             };
}

- (void)loadCategoryImage {
    
    NSData *imageData = [NSData dataWithContentsOfURL:self.imageURL];
    
    self.image = [UIImage imageWithData:imageData];
    
    //    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
    //
    //
    //    });
}

@end
