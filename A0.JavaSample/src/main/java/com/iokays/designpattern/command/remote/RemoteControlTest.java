package com.iokays.designpattern.command.remote;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "遥控器测试", description = "遥控器测试")
public class RemoteControlTest {

    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        LightOnCommand lightOn = new LightOnCommand(light);

        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }

}
