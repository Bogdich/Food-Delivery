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

@interface CartViewController () <UITableViewDelegate, UITableViewDataSource, CartTableViewCellDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (strong, nonatomic) UIImageView *dishPhotoImageView;

@end

@implementation CartViewController

- (void)viewDidLoad {
    [super viewDidLoad];
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
