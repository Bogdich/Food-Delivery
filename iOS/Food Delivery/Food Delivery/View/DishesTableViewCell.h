//
//  DishesTableViewCell.h
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DishesTableViewCell : UITableViewCell

@property (nonatomic, strong) UIImage *dishImage;
@property (nonatomic, strong) NSString *dishName;
@property (nonatomic, assign) NSInteger dishPrice;

@end
