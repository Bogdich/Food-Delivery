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
#define getDishByID @"/food-delivery/dish/getInfo"
#define getDishesByCategoryID @"/food-delivery/dish/getDishes"
#define registrUser @"/food-delivery/user/insertUser"

@implementation APIManager

- (NSURLSessionDataTask *)addUserWith:(User *)user success:(void (^)(id object))success failure:(void (^)(NSError *error))failure {

    NSMutableDictionary *parameters = [[NSMutableDictionary alloc] initWithObjectsAndKeys: user, @"id",  nil];
    
    return [self POST:registrUser parameters:parameters progress:nil
              success:^(NSURLSessionDataTask *task, id responseObject) {
        
                  NSDictionary *responseDictionary = (NSDictionary *)responseObject;
                  
                  id object;
                  
                  if ([responseDictionary objectForKey:@"responseID"]) {
                     
                      object = [responseDictionary objectForKey:@"responseID"];
                  } else {
                      
                      object = [responseDictionary objectForKey:@"error"];
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
                 User *user = [MTLJSONAdapter modelOfClass:[Category class]
                                                fromJSONDictionary:responseDictionary error:&error];
                 success(user);
                 
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
