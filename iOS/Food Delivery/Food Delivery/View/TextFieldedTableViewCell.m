//
//  TextFieldedTableViewCell.m
//  Food Delivery
//
//  Created by Kirill Budevich on 03.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "TextFieldedTableViewCell.h"

@interface TextFieldedTableViewCell () <UITextFieldDelegate>

@end

@implementation TextFieldedTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(nullable NSString *)reuseIdentifier {
    
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    
    UIColor *color = [UIColor lightTextColor];
    
    self.textField.attributedPlaceholder = [[NSAttributedString alloc] initWithString:@"Введите имя" attributes:@{NSForegroundColorAttributeName: color}];
    self.textField.text = @"";
    
    if (self) {
        
        self = [[[NSBundle mainBundle] loadNibNamed:NSStringFromClass([self class]) owner:self options:nil] objectAtIndex:0];
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

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (IBAction)changedTextValue:(UITextField *)sender {
    
    if ([self.delegate respondsToSelector:@selector(changedTextField:inCell:)]) {
        
        [self.delegate changedTextField:sender inCell:self];
    }
    else {
        
        NSAssert(NO, @"delegate issue");
    }
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    [textField resignFirstResponder];
    
    return YES;
}

- (void)becomeTextField {
    
    [self.textField becomeFirstResponder];
}

- (void)textFieldDidBeginEditing:(UITextField *)textField {
    
    if (self.textField.keyboardType == UIKeyboardTypePhonePad) {
        
        UIToolbar* keyboardToolbar = [[UIToolbar alloc] init];
        [keyboardToolbar sizeToFit];
        UIBarButtonItem *flexBarButton = [[UIBarButtonItem alloc]
                                          initWithBarButtonSystemItem:UIBarButtonSystemItemFlexibleSpace
                                          target:nil action:nil];
        UIBarButtonItem *doneBarButton = [[UIBarButtonItem alloc]
                                          initWithBarButtonSystemItem:UIBarButtonSystemItemDone
                                          target:self action:@selector(hideKeyboard)];
        keyboardToolbar.items = @[flexBarButton, doneBarButton];
        self.textField.inputAccessoryView = keyboardToolbar;
    }
    else {
        
        self.textField.inputAccessoryView = nil;
    }
    
    if ([self.delegate respondsToSelector:@selector(textFieldDidBeginEditing:inCell:)]) {
        
        [self.delegate textFieldDidBeginEditing:self.textField inCell:self];
    }
}

- (void)textFieldDidEndEditing:(UITextField *)textField {
    
    if ([self.delegate respondsToSelector:@selector(textFieldDidEndEditing:inCell:)]) {
        
        [self.delegate textFieldDidEndEditing:self.textField inCell:self];
    }
}


- (void)hideKeyboard {
    
    if ([self.delegate respondsToSelector:@selector(hideKeyboard)]) {
        
        [self.delegate hideKeyboard];
    }
    else {
        
        NSAssert(NO, @"delegate issue");
    }
}


@end
