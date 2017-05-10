//
//  SegmentedControlTableViewCell.m
//  Food Delivery
//
//  Created by Kirill Budevich on 03.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "SegmentedControlTableViewCell.h"

@interface SegmentedControlTableViewCell ()

@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
@property (weak, nonatomic) IBOutlet UISegmentedControl *segmentControl;

@end

@implementation SegmentedControlTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(nullable NSString *)reuseIdentifier {
    
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
        
    if (self) {
        
        self = [[[NSBundle mainBundle] loadNibNamed:NSStringFromClass([self class]) owner:self options:nil] objectAtIndex:0];
    }
    
    return self;
}

- (instancetype)init
{
    self = [super init];
    if (self) {
        
        self = [[[NSBundle mainBundle] loadNibNamed:NSStringFromClass([self class]) owner:self options:nil] objectAtIndex:0];
    }
    return self;
}

- (IBAction)segmentControlValueChanged:(UISegmentedControl *)sender {
    
    if ([self.delegate respondsToSelector:@selector(segmentedControlValueChanged:inCell:)]) {
        
        [self.delegate segmentedControlValueChanged:sender inCell:self];
    }
    else {
        
        NSAssert(NO, @"delegate issue");
    }
}

- (void)setFirstSegment:(NSString *)firstSegment secondSegment:(NSString *)secondSegment withTitle:(NSString *)title {
    
    [self.segmentControl removeAllSegments];
    
    [self.segmentControl insertSegmentWithTitle:firstSegment atIndex:0 animated:YES];
    [self.segmentControl insertSegmentWithTitle:secondSegment atIndex:1 animated:YES];
    
    [self.segmentControl setSelectedSegmentIndex:0];
    
    _titleLabel.text = title;
}

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
