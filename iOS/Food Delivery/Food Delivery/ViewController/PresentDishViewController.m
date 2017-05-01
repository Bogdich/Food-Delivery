//
//  PresentDishViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "PresentDishViewController.h"
#import "constants.h"
#import <SVProgressHUD/SVProgressHUD.h>
#import "CartManager.h"

@interface PresentDishViewController ()

@property (weak, nonatomic) IBOutlet UIImageView *dishImageView;
@property (weak, nonatomic) IBOutlet UILabel *dishNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *dishPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *dishDescriptionLabel;
@property (weak, nonatomic) IBOutlet UILabel *dishWeightLabel;
@property (weak, nonatomic) IBOutlet UILabel *countLabel;
@property (weak, nonatomic) IBOutlet UIStepper *countStepper;

@property (weak, nonatomic) IBOutlet UIButton *addToCartButton;

@property NSInteger dishCount;

@end

@implementation PresentDishViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.navigationController.navigationBar.topItem.title = @"";
    
    [self drawAddToCartButton];
    
    self.dishCount = [[CartManager sharedManager] getDishesCountForId:self.selectedDish.id_];
    _countLabel.text = [NSString stringWithFormat:@"%ld шт.", (long)self.dishCount];
    _dishImageView.image = _selectedDish.image;
    _dishNameLabel.text = _selectedDish.name;
    _dishPriceLabel.text = [NSString stringWithFormat:@"%@ BYN", _selectedDish.price];
    _dishDescriptionLabel.text = _selectedDish.description_;
    _dishWeightLabel.text = [NSString stringWithFormat:@"%@ г.", _selectedDish.weight];
    // Do any additional setup after loading the view.
}

- (IBAction)stepperClicked:(UIStepper *)sender {
    
    self.dishCount = [sender value];
    
    _countLabel.text = [NSString stringWithFormat:@"%ld шт.", (long)self.dishCount];
}

- (IBAction)addToCartButtonClicked:(UIButton *)sender {
    
    [SVProgressHUD showSuccessWithStatus:@"Успешно добавлено в корзину!"];
    
    [[CartManager sharedManager] addDishToCart:self.selectedDish count:self.dishCount];
    
    [SVProgressHUD dismissWithDelay:2];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)drawAddToCartButton {
    
    _addToCartButton.layer.cornerRadius = _addToCartButton.frame.size.height / 2;
    _addToCartButton.layer.borderWidth = 1.0f;
    _addToCartButton.layer.borderColor = [[UIColor GOLD_COLOR] CGColor];
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
