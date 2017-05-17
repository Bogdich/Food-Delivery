//
//  RootMenuViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "RootMenuViewController.h"
#import "constants.h"

@interface RootMenuViewController ()

@end

@implementation RootMenuViewController

- (void)awakeFromNib {
    
    self.menuPreferredStatusBarStyle = UIStatusBarStyleLightContent;
    self.contentViewShadowColor = [UIColor GOLD_COLOR];
    self.contentViewShadowOffset = CGSizeMake(0, 0);
    self.contentViewShadowOpacity = 1.0;
    self.contentViewShadowRadius = 1.0;
    self.contentViewShadowEnabled = YES;
    
    self.interactivePopGestureRecognizerEnabled = YES;
    self.scaleBackgroundImageView = NO;
    self.scaleMenuView = NO;
    self.parallaxEnabled = NO;
    
    self.scaleContentView = NO;
    self.bouncesHorizontally = NO;
    
    self.contentViewInPortraitOffsetCenterX = - ([UIScreen mainScreen].bounds.size.width / 2 - 110.0f);
    
    self.contentViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"contentViewController"];
    self.rightMenuViewController = nil;
    self.leftMenuViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"rightMenuViewController"];
    
    self.delegate = self;
    
    self.panGestureEnabled = YES;
    self.interactivePopGestureRecognizerEnabled = NO;
    self.panFromEdge = NO;
    
    [super awakeFromNib];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


#pragma mark - RESideMenu Delegate

- (void)sideMenu:(RESideMenu *)sideMenu willShowMenuViewController:(UIViewController *)menuViewController {
    
}

- (void)sideMenu:(RESideMenu *)sideMenu didShowMenuViewController:(UIViewController *)menuViewController {
    
//    NSLog(@"didShowMenuViewController: %@", NSStringFromClass([menuViewController class]));
}

- (void)sideMenu:(RESideMenu *)sideMenu willHideMenuViewController:(UIViewController *)menuViewController {
    
    NSLog(@"willHideMenuViewController: %@", NSStringFromClass([menuViewController class]));

}

- (void)sideMenu:(RESideMenu *)sideMenu didHideMenuViewController:(UIViewController *)menuViewController {
    
//    NSLog(@"didHideMenuViewController: %@", NSStringFromClass([menuViewController class]));
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
