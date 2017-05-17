//
//  LoginViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 09.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "LoginViewController.h"
#import "APIManager.h"
#import "User.h"
#import <SVProgressHUD/SVProgressHUD.h>

@interface LoginViewController ()

@property (weak, nonatomic) IBOutlet UITextField *usernameTextField;
@property (weak, nonatomic) IBOutlet UITextField *passwordTextField;
@property (weak, nonatomic) IBOutlet UIImageView *backgroundImageView;

@property (strong, nonatomic) NSString *login;
@property (strong, nonatomic) NSString *password;

@end

@implementation LoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self customizeTextFields];
    
    self.navigationItem.title = @"";
    
    if (!UIAccessibilityIsReduceTransparencyEnabled()) {
        self.view.backgroundColor = [UIColor clearColor];
        
        UIBlurEffect *blurEffect = [UIBlurEffect effectWithStyle:UIBlurEffectStyleRegular];
        UIVisualEffectView *blurEffectView = [[UIVisualEffectView alloc] initWithEffect:blurEffect];
        blurEffectView.frame = self.view.bounds;
        blurEffectView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
        
        
        [self.view insertSubview:blurEffectView atIndex:1];
    } else {
        
        self.view.backgroundColor = [UIColor blackColor];
    }
    
    // Do any additional setup after loading the view.
}

- (void)customizeTextFields {
    
    self.usernameTextField.borderStyle = UITextBorderStyleRoundedRect;
    
    [self.usernameTextField setBackgroundColor:[UIColor whiteColor]];
    [self.usernameTextField.layer setBorderColor:[UIColor whiteColor].CGColor];
    [self.usernameTextField.layer setBorderWidth:2.0];
    
    self.passwordTextField.borderStyle = UITextBorderStyleRoundedRect;
    
    [self.passwordTextField setBackgroundColor:[UIColor whiteColor]];
    [self.passwordTextField.layer setBorderColor:[UIColor whiteColor].CGColor];
    [self.passwordTextField.layer setBorderWidth:2.0];
}

- (void)viewWillAppear:(BOOL)animated {
    
    [self.navigationController setNavigationBarHidden:YES];
}

- (void)viewWillDisappear:(BOOL)animated {
    
    [self.navigationController setNavigationBarHidden:NO];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)loginButtonClicked:(UIButton *)sender {
    
    [SVProgressHUD showWithStatus:@"Идет авторизация"];
    
    self.login = _usernameTextField.text;
    self.password = _passwordTextField.text;
    
    NSString *answer = [[User getInstance] upAutorizationWithLogin:self.login andPass:self.password];
    NSString *message;
    
    if ([answer isEqualToString:@"USER LOGIN"]) {
        
        message = @"Вы успешно авторизовались";
        [SVProgressHUD showSuccessWithStatus:message];
        
    } else if([answer isEqualToString:@"LOGIN NOT EXIST"]) {
        
        message = @"Проверьте введеные данные";
        [SVProgressHUD showErrorWithStatus:message];
    } else {
        
        message = @"Что-то пошло не так";
        [SVProgressHUD showErrorWithStatus:message];
    }
    
    [self.navigationController dismissViewControllerAnimated:YES completion:nil];
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
