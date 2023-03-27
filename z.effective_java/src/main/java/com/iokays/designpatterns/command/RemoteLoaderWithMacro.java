package com.iokays.designpatterns.command;

public class RemoteLoaderWithMacro {

    public static void main(String[] args) {
        Light light = new Light("Living room");
        TV tv = new TV("Living room");
        Stereo stereo = new Stereo("Living room");
        HotTub hotTub = new HotTub();

        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);

        StereoOnWithCDCommand stereoOn = new StereoOnWithCDCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);

        TVOnCommand tvOn = new TVOnCommand(tv);
        TVOffCommand tvOff = new TVOffCommand(tv);

        HotTubOnCommand hotTubOn = new HotTubOnCommand(hotTub);
        HotTubOffCommand hotTubOff = new HotTubOffCommand(hotTub);

        Command[] partyOn = { lightOn, stereoOn, tvOn, hotTubOn };
        Command[] partyOff = { lightOff, stereoOff, tvOff, hotTubOff };

        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(0, partyOnMacro, partyOffMacro);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
    }

}
