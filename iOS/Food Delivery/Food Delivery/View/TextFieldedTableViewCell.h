//
//  TextFieldedTableViewCell.h
//  Food Delivery
//
//  Created by Kirill Budevich on 03.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol TextFieldedTableViewCellDelegate;

@interface TextFieldedTableViewCell : UITableViewCell

@property (nonatomic) __weak id <TextFieldedTableViewCellDelegate> delegate;

@property (weak, nonatomic) IBOutlet UITextField *textField;

- (void)becomeTextField;

@end

@protocol TextFieldedTableViewCellDelegate <NSObject>

@required

- (void)changedTextField:(UITextField *)textField inCell:(TextFieldedTableViewCell *)cell;
- (void)hideKeyboard;

@optional

- (void)textFieldDidBeginEditing:(UITextField *)textField inCell:(TextFieldedTableViewCell *)cell;
- (void)textFieldDidEndEditing:(UITextField *)textField inCell:(TextFieldedTableViewCell *)cell;

@end

