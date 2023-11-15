package com.iokays.designpattern.templatemethod.coffee;

import io.swagger.annotations.ApiModel;

@ApiModel("带钩子的咖啡因饮料")
public class CoffeeWithHook extends CaffeineBeverageWithHook {

    @Override
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    @Override
    public boolean customerWantsCondiments() {
        String answer = getUserInput();
        if (answer.toLowerCase().startsWith("y")) {
            return true;
        }
        else {
            return false;
        }
    }

    private String getUserInput() {
        // get the user's response
        return "y";
    }
}
