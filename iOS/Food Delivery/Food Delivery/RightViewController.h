//
//  RightViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <RESideMenu/RESideMenu.h>

//@class BILSearchViewModel;

@interface RightViewController : UIViewController

- (void)checkAuth;
- (void)updateSelectedIndexByStoryboardID:(NSString *)storyboardID;

//- (BOOL)openNewSearchScreen;
//- (BOOL)openNewSearchScreenWithRootView:(UIViewController *)rootVC;
//- (BOOL)openNewSearchScreenForced;
//- (void)openSearchScreenWithSearchModel:(BILSearchViewModel *)searchModel;
//- (void)saveCurrentSearch;
- (void)loginScreenOpen;

@end
