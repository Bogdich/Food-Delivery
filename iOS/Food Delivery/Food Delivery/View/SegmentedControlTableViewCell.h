//
//  SegmentedControlTableViewCell.h
//  Food Delivery
//
//  Created by Kirill Budevich on 03.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol SegmentedControlTableViewCellDelegate;

@interface SegmentedControlTableViewCell : UITableViewCell

@property (nonatomic) __weak id <SegmentedControlTableViewCellDelegate> delegate;

- (void)setFirstSegment:(NSString *)firstSegment secondSegment:(NSString *)secondSegment withTitle:(NSString *)title;

@end

@protocol SegmentedControlTableViewCellDelegate <NSObject>

@required

- (void)segmentedControlValueChanged:(UISegmentedControl *)sender inCell:(SegmentedControlTableViewCell *) cell;

@end
