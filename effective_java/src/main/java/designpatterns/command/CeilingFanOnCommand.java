package designpatterns.command;

public class CeilingFanOnCommand extends CeilingFanCommand {

    public CeilingFanOnCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.low();
    }
}
