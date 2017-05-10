//
//  ProfileSectionView.h
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol ProfileSectionViewDelegate;

@interface ProfileSectionView : UIView

@property __weak id <ProfileSectionViewDelegate> delegate;

@property (copy, nonatomic) NSString *nameString;
@property (strong, nonatomic) UIImage *avatar;

@property (nonatomic) BOOL opened;

- (instancetype)initOnView:(UIView *)view inView:(UIView *)inView andMainView:(UIView *)mainView;

@end

@protocol ProfileSectionViewDelegate <NSObject>

@required

- (void)areaButtonClicked:(UIButton *)sender;

@end
