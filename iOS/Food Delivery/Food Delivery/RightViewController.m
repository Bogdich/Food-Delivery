//
//  RightViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "RightViewController.h"
#import "RightMenuTableViewCell.h"
#import "ProfileSectionView.h"
#import "constants.h"
#import "CategoryListTableViewController.h"
//#import "BTXProfileSectionView.h"
#import "LoginViewController.h"
#import "AppDelegate.h"
#import "User.h"


@interface RightViewController () <UITableViewDelegate, UITableViewDataSource, RESideMenuDelegate, ProfileSectionViewDelegate,/*BILProfileRegisterLoginViewDelegate,*/ UIAlertViewDelegate>

@property (weak, nonatomic) IBOutlet UIView *profileSectionView;
@property (strong, nonatomic) ProfileSectionView *profileSection;

@property (nonatomic, strong) NSArray *titles;
@property (nonatomic, strong) NSArray *images;
@property (nonatomic, strong) NSArray *links;
@property (weak, nonatomic) IBOutlet UITableView *_tableView;

//@property (strong, nonatomic) BILProfileRegisterLoginView *registerLoginView;

@property (strong, nonatomic) UINavigationController *searchNavigationController;

@property NSIndexPath *selectedIndexPath;

@end

@implementation RightViewController

- (void)viewDidLoad {
    
    self.sideMenuViewController.delegate = self;
    self._tableView.separatorColor = [UIColor clearColor];
    
    self.titles = @[@"Меню", @"История заказов", @"Избранное", @"Настройки"];
    self.images = @[@"food_menu", @"order_history", @"favorite", @"settings"];
    self.links =  @[ContentViewController, OrderHisoryViewController, FavoriteViewController, SettingsViewController];
    
    [super viewDidLoad];
    
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reloadDataInProfileSection) name:@"userInfoSaved" object:nil];
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reloadDataInProfileSection) name:@"photoSaved" object:nil];
    
    self.profileSection = [[ProfileSectionView alloc] initOnView:self.profileSectionView inView:self.view andMainView:self.view];
    self.profileSection.delegate = self;
    
    UINavigationController *vc = (UINavigationController *)self.sideMenuViewController.contentViewController;
    
    if ([[vc topViewController] isKindOfClass:[CategoryListTableViewController class]]) {
        
        self.searchNavigationController = vc;
    }
        
    self.selectedIndexPath = [NSIndexPath indexPathForRow:0 inSection:0];
    [self._tableView reloadData];
    
    [self checkAuth];
    
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reloadDataInProfileSection) name:@"photoSaved" object:nil];
}

- (void)createRecognizerForHideKeyboardByTap {
    
    //override for working didSelectRowAtIndexPath:
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)checkAuth {
    
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    
}

#pragma mark - ProfileSection

//- (BILProfileRegisterLoginView *)registerLoginView {
//    
//    if (!_registerLoginView) {
//        
//        _registerLoginView = [[BILProfileRegisterLoginView alloc] init];
//        _registerLoginView.delegate = self;
//    }
//    
//    return _registerLoginView;
//}

//- (void)checkAndSetNameStringFromDict:(NSDictionary *) dict {
//    
//    NSString *name = [dict objectForKey:@"NAME"];
//    NSString *lastName = [dict objectForKey:@"LAST_NAME"];
//    
//    if (name.length == 0 || lastName.length == 0) {
//        
//        self.profileSection.nameString = [BTXProfile getInstance].email;
//    } else {
//        
//        self.profileSection.nameString = [NSString stringWithFormat:@"%@ %@", lastName, name];
//    }
//}

#pragma mark - UITableViewDataSource, UITableViewDelegate

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
        
    static NSString *CellIdentifier = @"RightMenuTableViewCellIdentifier";
    
    RightMenuTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    
    if (cell == nil) {
        
        cell = [[RightMenuTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier];
    }
    
    cell.title = [self.titles objectAtIndex:indexPath.row];
    
    cell.imageName = [self.images objectAtIndex:indexPath.row];
    cell.choosed = [self.selectedIndexPath isEqual:indexPath];
    
    return cell;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    return 4;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    
//    if (section == 0 && self.registerLoginView.opened) {
//        
//        return 100.0f;
//    }
    
    return 0.0001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    return UITableViewAutomaticDimension;
}

- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    return 100.0f;
}

- (void)updateSelectedIndexByStoryboardID:(NSString *)storyboardID {
    
    NSInteger index = [self.links indexOfObject:storyboardID];
    
    if (index == NSNotFound) {
        
        index = 0;
    }
    
    self.selectedIndexPath = [NSIndexPath indexPathForRow:index inSection:0];
    
    [self._tableView reloadData];
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
        
    UIViewController *controller = [self.storyboard instantiateViewControllerWithIdentifier:[self.links objectAtIndex:indexPath.row]];
    
    if (indexPath.row == 0) {
        
        controller = [self.storyboard instantiateViewControllerWithIdentifier:[self.links objectAtIndex:indexPath.row]];
        self.searchNavigationController = (UINavigationController *)controller;
    }
    
    [self.sideMenuViewController setContentViewController:controller animated:YES];
    [self.sideMenuViewController hideMenuViewController];
    
    self.selectedIndexPath = indexPath;
    
    [self._tableView reloadSections:[NSIndexSet indexSetWithIndex:0] withRowAnimation:UITableViewRowAnimationAutomatic];
}

#pragma mark - BTXProfileSectionViewDelegate

- (void)areaButtonClicked:(UIButton *)sender {
    
    [self profileClicked];
}

- (void)profileClicked {
    
    if ([User getInstance].authorized) {
        
        [self profileScreenOpen];
        
        _selectedIndexPath = nil;
        [self._tableView reloadData];
    }
    else {
        
        [self loginScreenOpen];
    }
}

- (void)loginScreenOpen {
    
    UINavigationController *nc = [self.storyboard instantiateViewControllerWithIdentifier:@"LoginViewController"];
    
    [self.sideMenuViewController setContentViewController:nc animated:YES];
    [self.sideMenuViewController hideMenuViewController];
}

- (void)profileScreenOpen {
    
    return;
    UIViewController *controller = [self.storyboard instantiateViewControllerWithIdentifier:@""];
    [self.sideMenuViewController setContentViewController:controller animated:YES];
    [self.sideMenuViewController hideMenuViewController];
}

#pragma mark - UIAlertViewDelegate

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
    
    if (buttonIndex == 1) {
        
        [self loginScreenOpen];
    }
}

#pragma mark - dealloc

- (void)dealloc {
    
    [[NSNotificationCenter defaultCenter] removeObserver:self];
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
