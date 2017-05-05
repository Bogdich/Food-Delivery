//
//  APIManager.h
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "SessionManager.h"
#import "Category.h"
#import "User.h"
#import "Dish.h"

@interface APIManager : SessionManager

- (NSURLSessionDataTask *)getCategoriesWithSuccess:(void (^)(NSArray *categories))success failure:(void (^)(NSError *error))failure;

- (NSURLSessionDataTask *)getDishesByCategoryId:(NSInteger) _id success:(void (^)(NSArray *dishes))success failure:(void (^)(NSError *error))failure;

- (NSURLSessionDataTask *)addUserWithLogin:(NSString *)login andPass:(NSString *)pass name:(NSString *)name surname:(NSString *)surname address:(NSString *)address number:(NSString *)number email:(NSString *)email success:(void (^)(id object))success failure:(void (^)(NSError *error))failure;

- (NSURLSessionDataTask *)getUserById:(NSInteger) _id success:(void (^)(User *user))success failure:(void (^)(NSError *error))failure;

- (NSURLSessionDataTask *)getDishById:(NSInteger) _id success:(void (^)(Dish *dish))success failure:(void (^)(NSError *error))failure;
@end
