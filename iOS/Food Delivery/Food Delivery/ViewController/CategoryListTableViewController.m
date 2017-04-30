//
//  CategoryListTableViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "CategoryListTableViewController.h"
#import "APIManager.h"
#import <SVProgressHUD/SVProgressHUD.h>
#import "constants.h"
#import <Mantle/Mantle.h>
#import "DishesListTableViewController.h"
#import "CategoryTableViewCell.h"

@interface CategoryListTableViewController ()

@property (strong, nonatomic) NSArray *categories;
@property (strong, nonatomic) NSArray *dishesForCategory;
@property (strong, nonatomic) NSArray *categoryImages;
@property (strong, nonatomic) UIImage *image;

//@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation CategoryListTableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.categoryImages = @[@"category_pizza", @"category_sushi", @"category_roll", @"category_drinks"];
    
    [SVProgressHUD show];
    [self downloadCategories];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return self.categories.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
   
    return 1;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *reusableId = CategoryTableViewCellIdentifier;
    
    CategoryTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:reusableId];
    
    if (cell == nil) {
        
        cell = [[CategoryTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:reusableId];
    }
    
    NSError *error;
    Category *category = [MTLJSONAdapter modelOfClass:Category.class fromJSONDictionary:self.categories[indexPath.section] error:&error];

    [cell setCategoryName:category.name];
    [cell setCategoryImage:[UIImage imageNamed:self.categoryImages[indexPath.section]]];
    
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
 
    return tableView.frame.size.height / 4;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    [self.tableView deselectRowAtIndexPath:indexPath animated:YES];
    
    NSError *error;
    Category *category = [MTLJSONAdapter modelOfClass:Category.class fromJSONDictionary:self.categories[indexPath.section] error:&error];
    
    [SVProgressHUD show];
    
    [self downloadDishesByCategoryId:[category.id_ integerValue]];
}

- (void)downloadCategories {
    
    [[APIManager sharedManager] getCategoriesWithSuccess:^(NSArray *categoryList) {
        
            self.categories = categoryList;
//            NSURL *imageURL = [NSURL URLWithString:@"https://home-pizza.com/media/_versions_/%D0%B4%D0%BE%D0%BD_%D0%B1%D0%B5%D0%BA%D0%BE%D0%BD_catalog_product_detail.jpg"];
        
//        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
//            NSData *imageData = [NSData dataWithContentsOfURL:imageURL];
//            
//            dispatch_async(dispatch_get_main_queue(), ^{
//                // Update the UI
//                self.image = [UIImage imageWithData:imageData];
//                [self.tableView reloadData];
//            });
//        });

            [self.tableView reloadData];
            [SVProgressHUD dismiss];
        
    }
        failure:^(NSError *error){
            
            NSLog(@"%@", error);
        }];
}

- (void)downloadDishesByCategoryId:(NSInteger)id_ {
    
    [[APIManager sharedManager] getDishesByCategoryId:id_
     
        success:^(NSArray *dishes){
            
            self.dishesForCategory = dishes;
            
            [SVProgressHUD dismiss];
            [self performSegueWithIdentifier:CategoryListToDishListSegue sender:self];
                                                  
    }
        failure:^(NSError *error){
                                                  
    }];
}

#pragma mark - Navigation

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    
    if ([segue.identifier isEqualToString:CategoryListToDishListSegue]) {
        
        DishesListTableViewController *dishesVC = segue.destinationViewController;
        [dishesVC setDishesArray: self.dishesForCategory];
    }
}

@end
