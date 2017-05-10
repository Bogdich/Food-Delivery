//
//  NSString+ContainsCyrillic.h
//  biletix
//
//  Created by admin on 26.06.13.
//  Copyright (c) 2013 posplawworks. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSString (ContainsCyrillic)

- (BOOL)containsCyrillic;
- (BOOL)hasRussianCharacters;

- (BOOL)hasNumbers;
- (BOOL)hasCharacters;

+ (NSString *)stringWithOutRussian:(NSString *)oldString;
+ (NSString *)stringWithoutPlusesAndWhiteSpaces:(NSString *)string;
+ (NSString *)stringWithOutCharacters:(NSString *)oldString;

@end
