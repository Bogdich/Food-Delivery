//
//  OrderSectionView.m
//  Food Delivery
//
//  Created by Kirill Budevich on 04.05.17.
//  Copyright © 2017 Kirill Budevich. All rights reserved.
//

#import "OrderSectionView.h"

@implementation OrderSectionView

- (instancetype)init
{
    self = [super init];
    if (self) {
        
        self = [[[NSBundle mainBundle] loadNibNamed:NSStringFromClass([self class]) owner:self options:nil] objectAtIndex:0];
        
    }
    return self;
}

@end
