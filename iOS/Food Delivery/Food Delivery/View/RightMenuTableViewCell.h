//
//  RightMenuTableViewCell.h
//  Food Delivery
//
//  Created by Kirill Budevich on 05.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface RightMenuTableViewCell : UITableViewCell

@property (nonatomic, strong) NSString *title;
@property (nonatomic, strong) NSString *imageName;
@property (nonatomic, getter = isChoosed) BOOL choosed;

@end
