package frc.robot.gamecomponents;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.*;

public class WoF {

    private VictorSP motor = new VictorSP(Config.WoF_CHANNEL);
    private SoftSpeedController controller = new SoftSpeedController(motor);

    public void left(){

        controller.set(Config.WoF_SPEED);

    }

    public void right(){

        controller.set(Config.WoF_SPEED * -1);

    }

    public void stop(){

        controller.stopMotor();

    }

}