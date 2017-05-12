//
//  CreateOrderViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 03.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "CreateOrderViewController.h"
#import "SegmentedControlTableViewCell.h"
#import "TextFieldedTableViewCell.h"

#import "NSString+ContainsCyrillic.h"
#import "constants.h"
#import "User.h"
#import "Profile.h"
#import "CartManager.h"
#import "OrderSectionView.h"

typedef NS_ENUM(NSInteger, DeliveryType) {
    DeliveryTypePickup = 0,
    DeliveryTypeCourier = 1
};

typedef NS_ENUM(NSInteger, PayType) {
    PayTypeCard = 0,
    PayTypeCash = 1
};

@interface CreateOrderViewController () <UITableViewDelegate, UITableViewDataSource, SegmentedControlTableViewCellDelegate, TextFieldedTableViewCellDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UIButton *totalButton;
@property (weak, nonatomic) IBOutlet UILabel *totalPriceLabel;

@property (strong, nonatomic) User *user;
@property (strong, nonatomic) CartManager *cart;

@property (strong, nonatomic)NSString *cityAdr;
@property (strong, nonatomic)NSString *streetAdr;
@property (strong, nonatomic)NSString *houseAdr;

@property DeliveryType deliveryType;
@property PayType payType;

@end

@implementation CreateOrderViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.navigationController.navigationBar.topItem.title = @"";
    
    [self drawTotalButton];
    
    self.cart = [CartManager sharedManager];
    
//    self.user = [[User alloc] init];
//    self.user.name = @"Имя";
//    self.user.surname = @"Фамилия";
//    self.user.address = @"Минск Матусевича 56-101";
//    self.user.number = @"+3123123";
//    self.user.email = @"fisak";
    
    [self loadUserDataFromProfile];
    
    self.deliveryType = DeliveryTypePickup;
    self.payType = PayTypeCard;
    
    [self costForUpperView];
    
    // Do any additional setup after loading the view.
}

- (void)loadUserDataFromProfile {
    
    _user.name = [Profile sharedInstance].name;
    _user.surname = [Profile sharedInstance].surname;
    _user.address = [Profile sharedInstance].address;
    _user.number = [Profile sharedInstance].number;
    _user.email = [Profile sharedInstance].email;
    
    NSArray *adressArray = [_user.address componentsSeparatedByString:@" "];

    self.cityAdr = adressArray[0];
    self.streetAdr = adressArray[1];
    self.houseAdr = adressArray[2];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)finishOrderButtonClicked:(UIButton *)sender {
    
    NSMutableDictionary *dishesDict = [NSMutableDictionary new];
    
    NSArray *dishes = [self.cart getAllDishes];
    
    for (int i = 0; i < [self.cart getAllDishesCount]; i++) {
        
        Dish *dish = dishes[i];
        NSDictionary *dishesAndCount = [[NSDictionary alloc] initWithObjectsAndKeys: dish.id_, @"dishID",
                                        [NSNumber numberWithInteger:[self.cart getDishesCountForId:dish.id_]], @"count", nil];
        [dishesDict setObject:dishesAndCount forKey:[NSString stringWithFormat:@"%dDish", i + 1]];
    }
}

- (void)drawTotalButton {
    
    _totalButton.layer.cornerRadius = _totalButton.frame.size.height / 2;
    _totalButton.layer.borderWidth = 1.0f;
    _totalButton.layer.borderColor = [[UIColor GOLD_COLOR] CGColor];
}

- (void)costForUpperView {
    
    _totalPriceLabel.text = [NSString stringWithFormat:@"Итого: %ld BYN", (long)self.finalCost];
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    return 4;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    
    if (section == 3) {
        
        if (self.deliveryType == DeliveryTypePickup) {
            
            return 0;
        }
        return 3;
    }
    
    return 2;
}

- (NSString *)getPlaceholderStringForIndexPath:(NSIndexPath *)indexPath {
    
    if (indexPath.section == 0) {
        
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
    } else if (indexPath.section == 1) return indexPath.row == 0 ? @"Введите email" : @"Введите номер телефона";
    
    return @"";
}

- (NSString *)checkTextFieldTextForIndexPath:(NSIndexPath *)indexPath {
    
    if (indexPath.section == 0) {
        
        return indexPath.row == 0 ? _user.surname : _user.name;
        
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
    } else if (indexPath.section == 1) return indexPath.row == 0 ? _user.email : _user.number;

    return @"";
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (indexPath.section != 2) {
        
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
    } else {
        
        static NSString *reusableId = SegmentedControllTableViewCellIdentifier;
        
        SegmentedControlTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:reusableId];
        
        if (cell == nil) {
            
            cell = [[SegmentedControlTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:reusableId];
        }
        
        if (indexPath.row == 0) {
            
            [cell setFirstSegment:@"Картой" secondSegment:@"Наличными" withTitle:@"Оплата"];
        } else {
            
            [cell setFirstSegment:@"Самовызов" secondSegment:@"Курьером" withTitle:@"Доставка"];
        }
        
        cell.delegate = self;
        
        return cell;
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (self.deliveryType == DeliveryTypePickup && indexPath.section == 3) {
        
        return 0.0f;
    } else {
        
        return 44.0f;
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    
    return 0.00001f;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    
    if (self.deliveryType == DeliveryTypePickup && section == 3) {
        return 0.0001f;
    }
    
    return 40.0f;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    
    NSString *sectionName;
    
    switch (section) {
        case 0:
            sectionName = @"Личные данные";
            break;
            
        case 1:
            sectionName = @"Контактные данные";
            break;
            
        case 2:
            sectionName = @"Информация по заказу";
            break;
            
        case 3:
            sectionName = self.deliveryType == DeliveryTypePickup ? @"" : @"Адрес доставки";
            break;
            
        default:
            break;
    }
    
    OrderSectionView *orderView = [[OrderSectionView alloc] init];
    orderView.nameLabel.text = sectionName;
    
    return orderView;
}

#pragma mark - SegmentedControlTableViewCellDelegate

- (void)segmentedControlValueChanged:(UISegmentedControl *)sender inCell:(SegmentedControlTableViewCell *)cell {
    
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    
    if (indexPath.row == 1) {
        
        self.deliveryType = sender.selectedSegmentIndex == 0 ? DeliveryTypePickup : DeliveryTypeCourier;
        
        if (self.deliveryType == DeliveryTypeCourier) {
            
            self.finalCost += 3;
        } else {
            
            self.finalCost -= 3;
        }
        
        [self costForUpperView];
        
        NSIndexSet *indexSet = [NSIndexSet indexSetWithIndex:3];
        [self.tableView reloadSections:indexSet withRowAnimation:UITableViewRowAnimationAutomatic];
    } else {
        
        self.payType = sender.selectedSegmentIndex == 0 ? PayTypeCard : PayTypeCash;
    }
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
                
                if ([textField.text hasNumbers]) {
                    
                    [self showAlertViewWithMessage:@"В фамилии не должно быть цифр"];
                    return;
                }
                
                _user.surname = textField.text;
                
            }
            else if (indexPath.row == 1) {
                
                if ([textField.text hasNumbers]) {
                    
                    [self showAlertViewWithMessage:@"В имени не должно быть цифр"];
                    return;
                }
                
                _user.name = textField.text;
            }
            break;
            
        case 1:
            
            if (indexPath.row == 0) {
                
                if ([textField.text hasRussianCharacters]) {
                    
                    textField.text = [NSString stringWithOutRussian:textField.text];
                    [self showAlertViewWithMessage:@"E-mail нужно вводить латиницей"];
                    return;
                }
                
                _user.email = textField.text;
            }
            else if (indexPath.row == 1) {
                
                NSString *numberWithoutPlus = [NSString stringWithoutPlusesAndWhiteSpaces:textField.text];
                
                if ([numberWithoutPlus hasCharacters]) {
                    
                    [self showAlertViewWithMessage:@"Пожалуйста напишите корректный номер телефона"];
                    textField.text = [NSString stringWithOutCharacters:textField.text];
                    return;
                }
                
                _user.number = textField.text;
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
    
    if (indexPath.row == 1 && indexPath.section == 1) {
        
        if (_user.number.length == 0) {
            
            textField.text = @"+";
        }
        
        _user.number = textField.text;
    }
}

- (void)textFieldDidEndEditing:(UITextField *)textField inCell:(TextFieldedTableViewCell *)cell {
    
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    
    if (indexPath.row == 1 && indexPath.section == 1) {
        
        if (_user.number.length <= 5) {
            
            [self showAlertViewWithMessage:@"Пожалуйста напишите корректный номер телефона"];
            textField.text = [NSString stringWithOutCharacters:textField.text];
        }
        
        _user.number = textField.text;
    }
}

//- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
//    
//    [self.tableView deselectRowAtIndexPath:indexPath animated:YES];
//    
//    NSError *error;
//    Category *category = [MTLJSONAdapter modelOfClass:Category.class fromJSONDictionary:self.categories[indexPath.section] error:&error];
//    
//    [SVProgressHUD show];
//    
//    [self downloadDishesByCategoryId:[category.id_ integerValue]];
//}

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
