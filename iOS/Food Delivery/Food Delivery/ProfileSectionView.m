//
//  ProfileSectionView.m
//  Food Delivery
//
//  Created by Kirill Budevich on 01.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "ProfileSectionView.h"

@interface ProfileSectionView ()

@property (weak, nonatomic) IBOutlet UILabel *label;
@property (weak, nonatomic) IBOutlet UIImageView *iconImageView;

@end

@implementation ProfileSectionView

/*
 // Only override drawRect: if you perform custom drawing.
 // An empty implementation adversely affects performance during animation.
 - (void)drawRect:(CGRect)rect {
 // Drawing code
 }
 */

- (instancetype)initOnView:(UIView *)view inView:(UIView *)inView andMainView:(UIView *)mainView
{
    self = [super init];
    if (self) {
        
        self = [[[NSBundle mainBundle] loadNibNamed:NSStringFromClass([self class]) owner:self options:nil] objectAtIndex:0];
        
        self.label.text = nil;
        self.iconImageView.image = [UIImage imageNamed:@"user_not_registred"];
        
        self.translatesAutoresizingMaskIntoConstraints = NO;
        
        [inView addSubview:self];
        
        [mainView addConstraint:[NSLayoutConstraint constraintWithItem:self
                                                             attribute:NSLayoutAttributeTrailing
                                                             relatedBy:NSLayoutRelationEqual
                                                                toItem:view
                                                             attribute:NSLayoutAttributeTrailing
                                                            multiplier:1
                                                              constant:0]];
        
        [mainView addConstraint:[NSLayoutConstraint constraintWithItem:self
                                                             attribute:NSLayoutAttributeLeading
                                                             relatedBy:NSLayoutRelationEqual
                                                                toItem:view
                                                             attribute:NSLayoutAttributeLeading
                                                            multiplier:1
                                                              constant:0]];
        
        [mainView addConstraint:[NSLayoutConstraint constraintWithItem:self
                                                             attribute:NSLayoutAttributeTop
                                                             relatedBy:NSLayoutRelationEqual
                                                                toItem:view
                                                             attribute:NSLayoutAttributeTop
                                                            multiplier:1
                                                              constant:0]];
        
        [mainView addConstraint:[NSLayoutConstraint constraintWithItem:self
                                                             attribute:NSLayoutAttributeBottom
                                                             relatedBy:NSLayoutRelationEqual
                                                                toItem:view
                                                             attribute:NSLayoutAttributeBottom
                                                            multiplier:1
                                                              constant:0]];
        
        [mainView layoutIfNeeded];
    }
    return self;
}

- (instancetype)init
{
    self = [super init];
    if (self) {
        
        self = [[[NSBundle mainBundle] loadNibNamed:NSStringFromClass([self class]) owner:self options:nil] objectAtIndex:0];
    }
    return self;
}

- (void)setOpened:(BOOL)opened {
    
    _opened = opened;
}

- (void)setNameString:(NSString *)nameString {
    
    _nameString = nameString;
    
    self.label.text = nameString;
    
    self.iconImageView.image = [UIImage imageNamed:@"user_registred"];
}

- (void)setAvatar:(UIImage *)avatar {
    
    avatar = [avatar isKindOfClass:[NSNull class]] ? nil : avatar;
    
    if (avatar) {
        
        self.iconImageView.image = avatar;
    } else {
        
        self.iconImageView.image = [UIImage imageNamed:@"user_not_registred"];
    }
    self.iconImageView.layer.cornerRadius = self.iconImageView.frame.size.width / 2;
}

- (IBAction)areaButtonClicked:(id)sender {
    
    if ([self.delegate respondsToSelector:@selector(areaButtonClicked:)]) {
        
        [self.delegate areaButtonClicked:sender];
    }
    else {
        
        NSAssert(NO, @"delegate issue");
    }
}
@end
