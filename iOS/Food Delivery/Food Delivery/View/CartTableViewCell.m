//
//  CartTableViewCell.m
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "CartTableViewCell.h"
#import "constants.h"

@interface CartTableViewCell ()

@property (strong, nonatomic) Dish *dish;

@property (weak, nonatomic) IBOutlet UILabel *dishNameLabel;
@property (weak, nonatomic) IBOutlet UIImageView *backgroundImageView;
@property (weak, nonatomic) IBOutlet UILabel *countAndPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *categoryNameLabel;
@property (weak, nonatomic) IBOutlet UIStepper *countStepper;

@end

@implementation CartTableViewCell

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

- (IBAction)longGestureRecognizerTapped:(UILongPressGestureRecognizer *)sender {

    if ([self.delegate respondsToSelector:@selector(longPressTapped:cell:)]) {
        
        [self.delegate longPressTapped:sender cell:self];
    }
    else {
        
        NSAssert(NO, @"delegate issue");
    }
}

- (IBAction)countStepperValueClicked:(UIStepper *)sender {
    
    [self changeCountValue:sender.value];
    
    if ([self.delegate respondsToSelector:@selector(countStepperValueChanged:cell:)]) {
        
        [self.delegate countStepperValueChanged:sender.value cell:self];
    }
    else {
        
        NSAssert(NO, @"delegate issue");
    }
}

- (void)changeCountValue:(NSInteger)value {
    
    NSInteger count = value;
    
    NSString *titleString = [NSString stringWithFormat:@"%ldx%@ BYN", (long)count, _dish.price];
    
    NSRange selectedRange = [titleString rangeOfString:[NSString stringWithFormat: @"%ldx", (long)count]];
    
    NSDictionary *attrs = @{
                            NSFontAttributeName:[UIFont systemFontOfSize:21],
                            NSForegroundColorAttributeName:[UIColor whiteColor]
                            };
    NSDictionary *subAttrs = @{
                               NSFontAttributeName:[UIFont systemFontOfSize:25],
                               NSForegroundColorAttributeName:[UIColor GOLD_COLOR]
                               };
    
    NSMutableAttributedString *attributedText = [[NSMutableAttributedString alloc] initWithString:titleString attributes:subAttrs];
    
    [attributedText setAttributes:attrs range:selectedRange];
    
    [self.countAndPriceLabel setAttributedText:attributedText];
}

- (void)setDish:(Dish *)dish count:(NSInteger) count {
    
    _dish = dish;

    _dishNameLabel.text = dish.name;
    _categoryNameLabel.text = dish.category.name;
    _backgroundImageView.image = dish.category.image;
    _countStepper.value = count;
    
    NSString *titleString = [NSString stringWithFormat:@"%ldx%@ BYN", (long)count, dish.price];
    
    NSRange selectedRange = [titleString rangeOfString:[NSString stringWithFormat: @"%ldx", (long)count]];
    
    NSDictionary *attrs = @{
                            NSFontAttributeName:[UIFont systemFontOfSize:21],
                            NSForegroundColorAttributeName:[UIColor whiteColor]
                            };
    NSDictionary *subAttrs = @{
                               NSFontAttributeName:[UIFont systemFontOfSize:25],
                               NSForegroundColorAttributeName:[UIColor GOLD_COLOR]
                               };
    
    NSMutableAttributedString *attributedText = [[NSMutableAttributedString alloc] initWithString:titleString attributes:subAttrs];
    
    [attributedText setAttributes:attrs range:selectedRange];
    
    [self.countAndPriceLabel setAttributedText:attributedText];
}

@end
