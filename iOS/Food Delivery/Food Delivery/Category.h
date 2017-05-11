//
//  Category.h
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Mantle/Mantle.h>
#import <UIKit/UIKit.h>

@interface Category : MTLModel <MTLJSONSerializing>

@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSNumber *id_;
@property (strong, nonatomic) NSURL *imageURL;
@property (strong, nonatomic) UIImage *image;

@end
