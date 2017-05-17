//
//  RightMenuTableViewCell.m
//  Food Delivery
//
//  Created by Kirill Budevich on 05.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "RightMenuTableViewCell.h"
#import "constants.h"

@interface RightMenuTableViewCell ()

@property (weak, nonatomic) IBOutlet UIImageView *menuImageView;
@property (weak, nonatomic) IBOutlet UILabel *menuNameLabel;

@end

@implementation RightMenuTableViewCell

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

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setTitle:(NSString *)title {
    
    _title = title;
    self.menuNameLabel.text = title;
}

- (void)setChoosed:(BOOL)choosed {
    
    self.menuNameLabel.textColor = choosed ?  [UIColor GOLD_COLOR] :  [UIColor whiteColor];
    
    self.menuImageView.image = [UIImage imageNamed:choosed ? [NSString stringWithFormat:@"%@_selected", self.imageName] : self.imageName];
}

- (void)setImageName:(NSString *)imageName {
    
    _imageName = imageName;
    self.menuImageView.image = [UIImage imageNamed:imageName];
    [self.menuImageView layoutIfNeeded];
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
