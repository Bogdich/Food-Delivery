//
//  DishesTableViewCell.m
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "DishesTableViewCell.h"

@interface DishesTableViewCell ()

@property (weak, nonatomic) IBOutlet UIImageView *dishImageView;
@property (weak, nonatomic) IBOutlet UILabel *dishNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *dishPriceLabel;

@end

@implementation DishesTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

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

- (void)setDishName:(NSString *)dishName {
    
    _dishName = dishName;
    self.dishNameLabel.text = dishName;
}

- (void)setDishPrice:(NSInteger)dishPrice {
    
    _dishPrice = dishPrice;
    self.dishPriceLabel.text = [NSString stringWithFormat:@"%ld BYN", (long)dishPrice];
}

- (void)setDishImage:(UIImage *)dishImage {
    
    _dishImage = dishImage;
    self.dishImageView.image = dishImage;
}

@end
