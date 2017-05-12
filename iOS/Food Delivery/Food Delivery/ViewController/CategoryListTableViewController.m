//
//  CategoryListTableViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "CategoryListTableViewController.h"
#import <RESideMenu/RESideMenu.h>
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
        
    self.navigationController.navigationBar.topItem.title = @"";
    self.navigationController.navigationBar.tintColor = [UIColor GOLD_COLOR];
    
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
    
    Category *category = self.categories[indexPath.section];

    [cell setCategoryName:category.name];
    [cell setCategoryImage:category.image];
    
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
 
    return tableView.frame.size.height / 4;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    [self.tableView deselectRowAtIndexPath:indexPath animated:YES];
    
    Category *category = self.categories[indexPath.section];
    
    [SVProgressHUD show];
    
    [self downloadDishesByCategoryId:[category.id_ integerValue]];
}

- (void)downloadCategories {
    
    [[APIManager sharedManager] getCategoriesWithSuccess:^(NSArray *categoryList) {
        
        self.categories = [self categoryArrayWithDictionaryCategoryArray:categoryList];

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
            
            self.dishesForCategory = [self dishArrayWithDictionaryDishArray:dishes];
            
            [SVProgressHUD dismiss];
            [self performSegueWithIdentifier:CategoryListToDishListSegue sender:self];
                                                  
    }
        failure:^(NSError *error){
                                                  
    }];
}

- (NSArray *)dishArrayWithDictionaryDishArray:(NSArray *) dictionaryArray {
    
    NSMutableArray *dishArray = [NSMutableArray new];
    NSError *error;
    
    for (NSDictionary *dict in dictionaryArray) {
        
        Dish *dish = [MTLJSONAdapter modelOfClass:Dish.class fromJSONDictionary:dict error:&error];
        [dish loadDishImage];
        dish.category = [self.categories objectAtIndex:[dish.category.id_ integerValue] - 1];
        
        [dishArray addObject:dish];
    }
    
    return dishArray;
}

- (NSArray *)categoryArrayWithDictionaryCategoryArray:(NSArray *) dictionaryArray {
    
    NSMutableArray *categoryArray = [NSMutableArray new];
    NSError *error;
    
    for (NSDictionary *dict in dictionaryArray) {
        
        Category *category = [MTLJSONAdapter modelOfClass:Category.class fromJSONDictionary:dict error:&error];
        [category loadCategoryImage];
        
        if (category) {
            
            [categoryArray addObject:category];
        }
    }
    
    return categoryArray;
}


#pragma mark - Navigation

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    
    if ([segue.identifier isEqualToString:CategoryListToDishListSegue]) {
        
        DishesListTableViewController *dishesVC = segue.destinationViewController;
        [dishesVC setDishesArray: self.dishesForCategory];
    }
}

@end
