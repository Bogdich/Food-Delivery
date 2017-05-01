//
//  CartTableViewCell.h
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Dish.h"

@protocol CartTableViewCellDelegate;

@interface CartTableViewCell : UITableViewCell

@property (nonatomic) __weak id<CartTableViewCellDelegate> delegate;

- (void)setDish:(Dish *)dish count:(NSInteger) count;

@end

@protocol CartTableViewCellDelegate <NSObject>

@required

- (void)longPressTapped:(UILongPressGestureRecognizer *)recognizer cell:(CartTableViewCell *)cell;
- (void)countStepperValueChanged:(NSInteger)value cell:(CartTableViewCell *)cell;

@end
