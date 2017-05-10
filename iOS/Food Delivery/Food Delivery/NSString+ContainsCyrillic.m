//
//  NSString+ContainsCyrillic.m
//  biletix
//
//  Created by admin on 26.06.13.
//  Copyright (c) 2013 posplawworks. All rights reserved.
//

#import "NSString+ContainsCyrillic.h"

@implementation NSString (ContainsCyrillic)

- (BOOL)containsCyrillic {
    NSRegularExpression *regex = [[NSRegularExpression alloc] initWithPattern:@"[А-я]" options:0 error:NULL];
    return [regex numberOfMatchesInString:self options:0 range:NSMakeRange(0, [self length])] > 0;
}

- (BOOL)hasRussianCharacters {
    NSCharacterSet * set = [NSCharacterSet characterSetWithCharactersInString:@"абвгдеёжзийклмнопрстуфхцчшщъыьэюя"];
    return [[self lowercaseString] rangeOfCharacterFromSet:set].location != NSNotFound;
}

- (BOOL)hasNumbers {
    NSCharacterSet * set = [NSCharacterSet characterSetWithCharactersInString:@"1234567890"];
    return [[self lowercaseString] rangeOfCharacterFromSet:set].location != NSNotFound;
}

- (BOOL)hasCharacters {
    NSCharacterSet * set = [NSCharacterSet characterSetWithCharactersInString:@"абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯqwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM"];
    return [[self lowercaseString] rangeOfCharacterFromSet:set].location != NSNotFound;
}

+ (NSString *)stringWithOutRussian:(NSString *)oldString {
    
    return [[oldString componentsSeparatedByCharactersInSet:[NSCharacterSet characterSetWithCharactersInString:@"абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"]] componentsJoinedByString:@""];
}

+ (NSString *)stringWithOutCharacters:(NSString *)oldString {
    
    return [[oldString componentsSeparatedByCharactersInSet:[NSCharacterSet characterSetWithCharactersInString:@"абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯqwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM"]] componentsJoinedByString:@""];
}

+ (NSString *)stringWithoutPlusesAndWhiteSpaces:(NSString *)string {
    
    return [[string stringByReplacingOccurrencesOfString:@"+" withString:@""] stringByReplacingOccurrencesOfString:@" " withString:@""];
}

@end

