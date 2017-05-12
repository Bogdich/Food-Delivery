//
//  CartViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "CartViewController.h"
#import "constants.h"
#import "CartTableViewCell.h"
#import "CartManager.h"
#import "CreateOrderViewController.h"

@interface CartViewController () <UITableViewDelegate, UITableViewDataSource, CartTableViewCellDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UILabel *finalCostLabel;
@property (weak, nonatomic) IBOutlet UIButton *addToOrderButton;

@property (strong, nonatomic) CartManager *cart;

@property (assign, nonatomic) NSInteger finalCost;
@property (strong, nonatomic) UIImageView *dishPhotoImageView;

@end

@implementation CartViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.finalCost = 0;
    
    self.cart = [CartManager sharedManager];
    
    [self costForUpperView];
    [self drawCreateOrderButton];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - IBActions

- (IBAction)cancelButtonClicked:(UIBarButtonItem *)sender {
    
    [self.navigationController dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)createOrderButtonClicked:(UIButton *)sender {
}

#pragma mark - TableView Data Source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    return [[CartManager sharedManager] getAllDishesCount];
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    return 89.0f;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *reusableId = CartTableViewCellIdentifier;
    
    CartTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:reusableId];
    
    if (cell == nil) {
        
        cell = [[CartTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:reusableId];
    }
    
    NSArray *dishes = [[CartManager sharedManager] getAllDishes];
    Dish *dish = dishes[indexPath.row];
    NSInteger count = [[CartManager sharedManager] getDishesCountForId:dish.id_];
    
    [cell setDish:dishes[indexPath.row] count:count];
    cell.delegate = self;
    
    return cell;
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {

    return YES;
}

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        
        Dish *dish = [self.cart getDishInIndex:indexPath.row];
        
        [self.cart deleteDishFromCart:dish];
        
        [self costForUpperView];
        
        NSIndexSet *set = [NSIndexSet indexSetWithIndex:0];
        [self.tableView reloadSections:set withRowAnimation:UITableViewRowAnimationAutomatic];
    }
}

- (void)costForUpperView {
    
    self.finalCost = 0;
    
    if ([[CartManager sharedManager] getAllDishesCount] > 0) {
        
        for (Dish *dish in [[CartManager sharedManager] getAllDishes]) {
            
            NSInteger count = [[CartManager sharedManager]getDishesCountForId:dish.id_];
            
            self.finalCost += [dish.price integerValue] * count;
        }
    }
    
    _finalCostLabel.text = [NSString stringWithFormat:@"Итого: %ld BYN", (long)self.finalCost];
}


- (void)loadDishPhotoImageViewInCell:(CartTableViewCell *)cell {
    
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    
    NSArray *dishes = [[CartManager sharedManager] getAllDishes];
    Dish *dish = dishes[indexPath.row];
    
    if (!self.dishPhotoImageView) {
        
        self.dishPhotoImageView = [[UIImageView alloc] initWithImage:dish.image];
        
        self.dishPhotoImageView.layer.cornerRadius = 10.0f;
        self.dishPhotoImageView.clipsToBounds = YES;
        
        [self.dishPhotoImageView setFrame:CGRectMake(0, 0, self.view.frame.size.width - 40, self.view.frame.size.height/2)];
        self.dishPhotoImageView.center = CGPointMake(self.view.frame.size.width  / 2, self.view.frame.size.height / 2);
        self.dishPhotoImageView.alpha = 0.0f;

    } else {
        
        self.dishPhotoImageView.image = dish.image;
    }
}

#pragma mark - CartTableViewCell Delegate

- (void)longPressTapped:(UILongPressGestureRecognizer *)recognizer cell:(CartTableViewCell *)cell {
    
    [self loadDishPhotoImageViewInCell:cell];
    
    if (recognizer.state == UIGestureRecognizerStateBegan) {
        
        [self.view addSubview:self.dishPhotoImageView];
        
        [UIView animateWithDuration:0.2 animations:^(void){
            
            self.dishPhotoImageView.alpha = 1.0f;
        }];
    } else {
        
        if (recognizer.state == UIGestureRecognizerStateCancelled ||
            recognizer.state == UIGestureRecognizerStateFailed ||
            recognizer.state == UIGestureRecognizerStateEnded) {
            
            [UIView animateWithDuration:0.2 animations:^(void){
                
                self.dishPhotoImageView.alpha = 0.0f;
            
            } completion:^(BOOL finished){
                
                if (finished) {
                    [self.dishPhotoImageView removeFromSuperview];
                }
            }];
        }
    }
}

- (void)countStepperValueChanged:(NSInteger)value cell:(CartTableViewCell *)cell {
    
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    
    NSArray *dishes = [[CartManager sharedManager] getAllDishes];
    Dish *dish = dishes[indexPath.row];
    
    [[CartManager sharedManager] addDishToCart:dish count:value];
    
    [self costForUpperView];
}

- (void)drawCreateOrderButton {
    
    _addToOrderButton.layer.cornerRadius = _addToOrderButton.frame.size.height / 2;
    _addToOrderButton.layer.borderWidth = 1.0f;
    _addToOrderButton.layer.borderColor = [[UIColor GOLD_COLOR] CGColor];
}


#pragma mark - Navigation

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {

    if ([segue.identifier isEqualToString:CartToCreateOrderSegue]) {
        
        CreateOrderViewController *vc = segue.destinationViewController;
        vc.finalCost = self.finalCost;
    }
    
}

@end
