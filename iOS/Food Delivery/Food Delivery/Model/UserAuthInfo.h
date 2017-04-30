//
//  UserAuthInfo.h
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import <Mantle/Mantle.h>

@interface UserAuthInfo : MTLModel <MTLJSONSerializing>

@property (strong, nonatomic)NSNumber *id_;
@property (strong, nonatomic)NSString *login;
@property (strong, nonatomic)NSString *password;
@property (strong, nonatomic)NSNumber *isAdmin;

@end
