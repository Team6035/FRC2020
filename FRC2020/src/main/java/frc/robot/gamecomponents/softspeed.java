package frc.robot.gamecomponents;

import frc.robot.*;
import edu.wpi.first.wpilibj.*;

class SoftSpeedController implements SpeedController{

    private SpeedController target;
    private int RUT;

    public SoftSpeedController(SpeedController target, int RUT){

        this.RUT = RUT;
        this.target = target;

    }

    public SoftSpeedController(SpeedController target){

        this(target, Config.SSC_RUT);

    }

    @Override
    public void pidWrite(double out){

        target.pidWrite(out);

    }

    @Override
    public void set(double speed){

        double numIts = RUT / 20.0;
        double dSpeed = 1 / numIts;
        double cSpeed = target.get();
        double pNS = cSpeed - dSpeed;

        if (speed >= cSpeed){

            double aNS = Math.min(pNS, speed);
            target.set(aNS);

        }

        else {

            double aNS = Math.max(pNS, speed);
            target.set(aNS);

        }

    }

    @Override
    public double get(){

        return target.get();

    }

    @Override
    public void setInverted(boolean isInverted){

        target.setInverted(isInverted);

    }

    @Override
    public boolean getInverted(){

        return target.getInverted();

    }

    @Override
    public void disable(){

        target.disable();

    }

    @Override
    public void stopMotor(){

        target.stopMotor();

    }

}