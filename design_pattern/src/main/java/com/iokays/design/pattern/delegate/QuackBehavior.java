package com.iokays.design.pattern.delegate;

import io.swagger.annotations.ApiModel;

@ApiModel("呱呱叫行为接口")
public interface QuackBehavior {
    void quack();
}
