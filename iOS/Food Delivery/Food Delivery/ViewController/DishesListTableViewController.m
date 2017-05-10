//
//  DishesListTableViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "DishesListTableViewController.h"
#import <Mantle/Mantle.h>
#import "Dish.h"
#import "DishesTableViewCell.h"
#import "constants.h"
#import "PresentDishViewController.h"

@interface DishesListTableViewController () <UITableViewDelegate, UITableViewDataSource>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (strong, nonatomic) NSIndexPath *selectedIndexPath;

@end

@implementation DishesListTableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return self.dishesArray.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    return 1;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *reusableId = DishTableViewCellIdentifier;
    
    Dish *dish = self.dishesArray[indexPath.section];
    
    DishesTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:reusableId];
    
    if (cell == nil) {
        
        cell = [[DishesTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:reusableId];
    }
    
    [cell setDishName:dish.name];
    [cell setDishPrice:[dish.price integerValue]];
    [cell setDishImage:dish.image];
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    [self.tableView deselectRowAtIndexPath:indexPath animated:YES];
    self.selectedIndexPath = indexPath;
    
    [self performSegueWithIdentifier:DishListToPresentDishSegue sender:self];
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    return tableView.frame.size.height / 3;
}

#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    
    if ([segue.identifier isEqualToString:DishListToPresentDishSegue]) {
        
        PresentDishViewController *dishVC = segue.destinationViewController;
        
        [dishVC setSelectedDish: self.dishesArray[self.selectedIndexPath.section]];
    }
}

@end
