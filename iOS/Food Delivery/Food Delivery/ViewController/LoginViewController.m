//
//  LoginViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 09.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "LoginViewController.h"
#import "APIManager.h"
#import "Profile.h"
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
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(endAutorization) name:@"autorizationOK" object:nil];
    
    // Do any additional setup after loading the view.
}

- (void)endAutorization {
    
    //open new veiw
}

- (void)dealloc {
    
    [[NSNotificationCenter defaultCenter] removeObserver:self];
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

    [[Profile sharedInstance] upAutorizationWithLogin:self.login andPass:self.password];
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
