//
//  User.h
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Mantle/Mantle.h>
#import "UserAuthInfo.h"

@interface User : MTLModel <MTLJSONSerializing>

@property (strong, nonatomic)NSString *name;
@property (strong, nonatomic)NSString *surname;
@property (strong, nonatomic)NSString *address;
@property (strong, nonatomic)NSString *number;
@property (strong, nonatomic)NSString *email;

@property (strong, nonatomic)UserAuthInfo *info;

@end
