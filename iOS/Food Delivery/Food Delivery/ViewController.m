//
//  ViewController.m
//  Food Delivery
//
//  Created by Kirill Budevich on 30.04.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "ViewController.h"
#import "APIManager.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [[APIManager sharedManager] getDishById:1
                                    success:^(Dish *dish){
                                        
                                        NSLog(@"%@", dish);
                                    }
                                    failure:^(NSError *error){
                                        NSLog(@"%@", error);
                                    }];
    // Do any additional setup after loading the view, typically from a nib.
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
