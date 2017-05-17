//
//  RegistrationTableViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 09.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "RegistrationTableViewController.h"
#import "TextFieldedTableViewCell.h"

#import "NSString+ContainsCyrillic.h"
#import "constants.h"
#import "User.h"
#import <SVProgressHUD/SVProgressHUD.h>
#import "APIManager.h"
#import "OrderSectionView.h"

@interface RegistrationTableViewController () <TextFieldedTableViewCellDelegate>

@property (weak, nonatomic) IBOutlet UIButton *registrationButton;
@property (weak, nonatomic) IBOutlet UITableView *tableView;

@property (strong, nonatomic)NSString *login;
@property (strong, nonatomic)NSString *password;
@property (strong, nonatomic)NSString *name;
@property (strong, nonatomic)NSString *surname;
@property (strong, nonatomic)NSString *cityAdr;
@property (strong, nonatomic)NSString *streetAdr;
@property (strong, nonatomic)NSString *houseAdr;
@property (strong, nonatomic)NSString *number;
@property (strong, nonatomic)NSString *email;

@end

@implementation RegistrationTableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self drawRegistrButton];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)registrationButtonClicked:(UIButton *)sender {
    
    [SVProgressHUD showWithStatus:@"Идет авторизация"];
    
    if ([self isCorrectData]) {
        
        NSString *address = [NSString stringWithFormat:@"%@ %@ %@", self.cityAdr, self.streetAdr, self.houseAdr];
        
        NSString *message = [[User getInstance] registrationWithLogin:self.login andPass:self.password name:self.name surname:self.surname address:address number:self.number email:self.email];
        
        if ([message isEqualToString:@"OK"]) {
            
            [SVProgressHUD showSuccessWithStatus:@"Вы успешно авторизовались"];
            
        } else if([message isEqualToString:@"LOGIN EXIST"]) {
            
            [SVProgressHUD showErrorWithStatus:@"Такой логин уже существует"];
        } else {
            
            [SVProgressHUD showErrorWithStatus:@"Что-то пошло не так"];
        }

    } else {
        
        [SVProgressHUD showErrorWithStatus:@"Пожалуйста заполните все поля"];
    }
}

- (BOOL) isCorrectData {
    
    if (self.login.length < 1) {
        return NO;
    }
    else if (self.password.length < 1) {
        return NO;
    }
    else if (self.name.length < 1) {
        return NO;
    }
    else if (self.surname.length < 1) {
        return NO;
    }
    else if (self.number.length < 1) {
        return NO;
    }
    else if (self.email.length < 1) {
        return NO;
    }
    else if (self.houseAdr.length < 1) {
        return NO;
    }
    else if (self.cityAdr.length < 1) {
        return NO;
    }
    else if (self.streetAdr.length < 1) {
        return NO;
    }
    
    return YES;
}

#pragma mark - Table view data source

- (void)drawRegistrButton {
    
    _registrationButton.layer.cornerRadius = _registrationButton.frame.size.height / 2;
    _registrationButton.layer.borderWidth = 1.0f;
    _registrationButton.layer.borderColor = [[UIColor GOLD_COLOR] CGColor];
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return 4;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    if (section == 3) {
        
        return 3;
    }
    
    return 2;
}

- (NSString *)getPlaceholderStringForIndexPath:(NSIndexPath *)indexPath {
    
    if (indexPath.section == 0) {
        
        return indexPath.row == 0 ? @"Введите логин" : @"Введитемя пароль";
        
    } else if (indexPath.section == 1) {
        
        return indexPath.row == 0 ? @"Введите фамилию" : @"Введитемя имя";
        
    } else if(indexPath.section == 3) {
        
        switch (indexPath.row) {
            case 0:
                return @"Введите город";
                break;
                
            case 1:
                return @"Введите улицу";
                break;
                
            case 2:
                return @"Введите дом-квартиру";
                break;
                
            default:
                break;
        }
    } else if (indexPath.section == 2) return indexPath.row == 0 ? @"Введите email" : @"Введите номер телефона";
    
    return @"";
}

- (NSString *)checkTextFieldTextForIndexPath:(NSIndexPath *)indexPath {
    
    if (indexPath.section == 0) {
        
        return indexPath.row == 0 ? self.login : self.password;
        
    } else if (indexPath.section == 1) {
        
        return indexPath.row == 0 ? self.surname : self.name;
        
    } else if(indexPath.section == 3) {
        
        switch (indexPath.row) {
            case 0:
                return self.cityAdr;
                break;
                
            case 1:
                return self.streetAdr;
                break;
                
            case 2:
                return self.houseAdr;
                break;
                
            default:
                break;
        }
    } else if (indexPath.section == 2) return indexPath.row == 0 ? self.email : self.number;
    
    return @"";
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *reusableId = TextFieldedTableViewCellIdentifier;
    
    TextFieldedTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:reusableId];
    
    if (cell == nil) {
        
        cell = [[TextFieldedTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:reusableId];
    }
    
    NSString *placeholderString = [self getPlaceholderStringForIndexPath:indexPath];
    UIColor *color = [UIColor lightTextColor];
    
    cell.textField.text = [self checkTextFieldTextForIndexPath:indexPath];
    
    cell.textField.attributedPlaceholder = [[NSAttributedString alloc] initWithString:placeholderString attributes:@{NSForegroundColorAttributeName: color, NSFontAttributeName: [UIFont systemFontOfSize:14.0f]}];
    cell.delegate = self;
    
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    return 44.0f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    
    return 0.00001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    
    return 40.0f;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    
    NSString *sectionName;
    
    switch (section) {
        case 0:
            sectionName = @"Авторизационные данные";
            break;
        
        case 1:
            sectionName = @"Личные данные";
            break;
            
        case 2:
            sectionName = @"Контактные данные";
            break;
            
        case 3:
            sectionName = @"Адрес доставки";
            break;
            
        default:
            break;
    }
    
    OrderSectionView *orderView = [[OrderSectionView alloc] init];
    orderView.nameLabel.text = sectionName;
    
    return orderView;
}

#pragma mark - TextFieldedTableViewCellDelegate

- (void)hideKeyboard {
    
    [self.view endEditing:YES];
}

- (void)changedTextField:(UITextField *)textField inCell:(TextFieldedTableViewCell *)cell {
    
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    
    switch (indexPath.section) {
        
        case 0:
            
            if (indexPath.row == 0) {
                
                self.login = textField.text;
                
            }
            else if (indexPath.row == 1) {
                
                self.password = textField.text;
            }
            break;
            
        case 1:
            
            if (indexPath.row == 0) {
                
                if ([textField.text hasNumbers]) {
                    
                    [self showAlertViewWithMessage:@"В фамилии не должно быть цифр"];
                    return;
                }
                
                self.surname = textField.text;
                
            }
            else if (indexPath.row == 1) {
                
                if ([textField.text hasNumbers]) {
                    
                    [self showAlertViewWithMessage:@"В имени не должно быть цифр"];
                    return;
                }
                
                self.name = textField.text;
            }
            break;
            
        case 2:
            
            if (indexPath.row == 0) {
                
                if ([textField.text hasRussianCharacters]) {
                    
                    textField.text = [NSString stringWithOutRussian:textField.text];
                    [self showAlertViewWithMessage:@"E-mail нужно вводить латиницей"];
                    return;
                }
                
                self.email = textField.text;
            }
            else if (indexPath.row == 1) {
                
                NSString *numberWithoutPlus = [NSString stringWithoutPlusesAndWhiteSpaces:textField.text];
                
                if ([numberWithoutPlus hasCharacters]) {
                    
                    [self showAlertViewWithMessage:@"Пожалуйста напишите корректный номер телефона"];
                    textField.text = [NSString stringWithOutCharacters:textField.text];
                    return;
                }
                
                self.number = textField.text;
            }
            break;
            
        default:
            break;
    }
    
    if (indexPath.section == 3) {
        
        switch (indexPath.row) {
            case 0:
                self.cityAdr = textField.text;
                break;
                
            case 1:
                self.streetAdr = textField.text;
                break;
                
            case 2:
                self.houseAdr = textField.text;
                break;
                
            default:
                break;
        }
    }
}

- (void)textFieldDidBeginEditing:(UITextField *)textField inCell:(TextFieldedTableViewCell *)cell {
    
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    
    if (indexPath.row == 1 && indexPath.section == 2) {
        
        if (self.number.length == 0) {
            
            textField.text = @"+";
        }
        
        self.number = textField.text;
    }
}

- (void)textFieldDidEndEditing:(UITextField *)textField inCell:(TextFieldedTableViewCell *)cell {
    
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    
    if (indexPath.row == 1 && indexPath.section == 2) {
        
        if (self.number.length <= 5) {
            
            [self showAlertViewWithMessage:@"Пожалуйста напишите корректный номер телефона"];
            textField.text = [NSString stringWithOutCharacters:textField.text];
        }
        
        self.number = textField.text;
        
    } else if (indexPath.row == 1 && indexPath.section == 0)
        
        if (textField.text.length < 5) {
        
        [self showAlertViewWithMessage:@"Пароль должен быть больше 5 символов"];
        return;
    }
    
    self.password = textField.text;
}

#pragma mark - UIAlertView

- (void)showAlertViewWithMessage:(NSString *)message {
    
    [[[UIAlertView alloc] initWithTitle:@"" message:message delegate:nil cancelButtonTitle:@"Ок" otherButtonTitles:nil, nil] show];
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
