//
//  APIManager.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "APIManager.h"
#import <Mantle/Mantle.h>

#define getCategories @"/food-delivery/category/getCategories"
#define getUserByID @"/food-delivery/user/getInfo"
#define getIsUserExistByLogin @"/food-delivery/user/checkExist"
#define upAutorizationUser @"/food-delivery/user/login"
#define getDishByID @"/food-delivery/dish/getInfo"
#define getDishesByCategoryID @"/food-delivery/dish/getDishes"
#define registerUser @"/food-delivery/user/insertUser"

@implementation APIManager

- (NSURLSessionDataTask *)addUserWithLogin:(NSString *)login andPass:(NSString *)pass name:(NSString *)name surname:(NSString *)surname address:(NSString *)address number:(NSString *)number email:(NSString *)email success:(void (^)(id object))success failure:(void (^)(NSError *error))failure {
    
    NSString* encodedName = [name stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSString* encodedSurname = [surname stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSString* encodedAddress = [address stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSString* encodedLogin = [login stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSString* encodedPass = [pass stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    
    NSMutableDictionary *parameters = [[NSMutableDictionary alloc] initWithObjectsAndKeys:
                                       encodedLogin, @"login",
                                       encodedPass, @"password",
                                       encodedName, @"name",
                                       encodedSurname, @"surname",
                                       number, @"number",
                                       email, @"email",
                                       encodedAddress, @"address", nil];
    
//    NSMutableDictionary *parameters = [[NSMutableDictionary alloc] initWithObjectsAndKeys:
//                                       login, @"login",
//                                       pass, @"password",
//                                       name, @"name",
//                                       surname, @"surname",
//                                       number, @"number",
//                                       email, @"email",
//                                       address, @"address", nil];
    
    self.requestSerializer = [AFHTTPRequestSerializer serializer];
    [self.requestSerializer setStringEncoding:NSUTF8StringEncoding];
    
    return [self POST:registerUser parameters:parameters progress:nil
              success:^(NSURLSessionDataTask *task, id responseObject) {
                  
                  NSDictionary *responseDictionary = (NSDictionary *)responseObject;
                  
                  id object;
                  
                  if ([[responseDictionary objectForKey:@"message"] isEqualToString:@"OK"]) {
                      
                      object = [responseDictionary objectForKey:@"responseID"];
                  } else {
                      
                      object = [responseDictionary objectForKey:@"message"];
                  }
                  
                  success(object);
                  
              } failure:^(NSURLSessionDataTask *task, NSError *error) {
                  
                  failure(error);
                  
              }];
}

- (NSURLSessionDataTask *)getCategoriesWithSuccess:(void (^)(NSArray *categories))success failure:(void (^)(NSError *error))failure {
    
    return [self GET:getCategories parameters:nil progress:nil
             success:^(NSURLSessionDataTask *task, id responseObject) {
        
                 NSDictionary *responseDictionary = (NSDictionary *)responseObject;
        
                 NSArray *categories = (NSArray *)responseDictionary;
                 success(categories);
        
             } failure:^(NSURLSessionDataTask *task, NSError *error) {
        
                         failure(error);
                         
                     }];
}

- (NSURLSessionDataTask *)getUserById:(NSInteger) _id success:(void (^)(User *user))success failure:(void (^)(NSError *error))failure {
        
    return [self GET:[NSString stringWithFormat:@"%@/%lu", getUserByID, (long)_id] parameters:nil progress:nil
             success:^(NSURLSessionDataTask *task, id responseObject) {
                 
                 NSDictionary *responseDictionary = (NSDictionary *)responseObject;
                 
                 NSError *error;
                 User *user = [MTLJSONAdapter modelOfClass:[User class]
                                                fromJSONDictionary:responseDictionary error:&error];
                 success(user);
                 
             } failure:^(NSURLSessionDataTask *task, NSError *error) {
                 
                 failure(error);
                 
             }];
}

- (NSURLSessionDataTask *)getUserByLogin:(NSString *)login success:(void (^)(BOOL isExist))success failure:(void (^)(NSError *error))failure {
    
    return [self GET:[NSString stringWithFormat:@"%@/%@", getIsUserExistByLogin, login] parameters:nil progress:nil
             success:^(NSURLSessionDataTask *task, id responseObject) {
                 
                 NSDictionary *responseDictionary = (NSDictionary *)responseObject;
                 
                 BOOL isExist = [[responseDictionary objectForKey:@"message"] isEqualToString:@"OK"] ? YES : NO;

                 success(isExist);
                 
             } failure:^(NSURLSessionDataTask *task, NSError *error) {
                 
                 failure(error);
             }];
}

- (NSURLSessionDataTask *)upAutorizationWithLogin:(NSString *)login andPass:(NSString *)pass success:(void (^)(id object))success failure:(void (^)(NSError *error))failure {
    
    NSMutableDictionary *parameters = [[NSMutableDictionary alloc] initWithObjectsAndKeys:
                                       login, @"login",
                                       pass, @"password", nil];
    
    return [self GET:upAutorizationUser parameters:parameters progress:nil
             success:^(NSURLSessionDataTask *task, id responseObject) {
                 
                 NSDictionary *responseDictionary = (NSDictionary *)responseObject;
                 
                 id object;
                 
                 if ([[responseDictionary objectForKey:@"message"] isEqualToString:@"OK"]) {
                     
                     object = [responseDictionary objectForKey:@"responseID"];
                 } else {
                     
                     object = [responseDictionary objectForKey:@"message"];
                 }
                 
                 success(object);
                 
             } failure:^(NSURLSessionDataTask *task, NSError *error) {
                 
                 failure(error);
             }];
}

- (NSURLSessionDataTask *)getDishById:(NSInteger) _id success:(void (^)(Dish *dish))success failure:(void (^)(NSError *error))failure {
    
    return [self GET:[NSString stringWithFormat:@"%@/%lu", getDishByID, (long)_id] parameters:nil progress:nil
             success:^(NSURLSessionDataTask *task, id responseObject) {
                 
                 NSDictionary *responseDictionary = (NSDictionary *)responseObject;
                 
                 NSError *error;
                 Dish *dish = [MTLJSONAdapter modelOfClass:[Dish class]
                                        fromJSONDictionary:responseDictionary error:&error];
                 
                 [dish loadDishImage];
                 success(dish);
                 
             } failure:^(NSURLSessionDataTask *task, NSError *error) {
                 
                 failure(error);
                 
             }];
}

- (NSURLSessionDataTask *)getDishesByCategoryId:(NSInteger) _id success:(void (^)(NSArray *dishes))success failure:(void (^)(NSError *error))failure {
    
    return [self GET:[NSString stringWithFormat:@"%@/%lu", getDishesByCategoryID, (long)_id] parameters:nil progress:nil
             success:^(NSURLSessionDataTask *task, id responseObject) {
                 
                 NSDictionary *responseDictionary = (NSDictionary *)responseObject;
                 
                 NSArray *dishes = (NSArray *)responseDictionary;
                 success(dishes);
                 
             } failure:^(NSURLSessionDataTask *task, NSError *error) {
                 
                 failure(error);
                 
             }];
}

@end
