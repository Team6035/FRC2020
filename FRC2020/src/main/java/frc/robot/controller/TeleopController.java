package frc.robot.controller;

import frc.robot.controller.operations.*;
import frc.robot.Config;
import frc.robot.gamecomponents.ColourSensor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class TeleopController implements ControllerClass{

    private Joystick stick = new Joystick(Config.JOYSTICK_PORT);
    private XboxController xbox = new XboxController(Config.XBOX_PORT);
    private ColourSensor cS = new ColourSensor();
    private DigitalInput hE = new DigitalInput(Config.HE_CHANNEL);
    private DigitalInput hE2 = new DigitalInput(Config.HE2_CHANNEL);
    private DigitalInput lS = new DigitalInput(Config.LS_CHANNEL);
    private DigitalInput lS2 = new DigitalInput(Config.LS2_CHANNEL);

    @Override
    public double getDriveSpeed(){

        double speedY = stick.getY();
        double throttle = stick.getThrottle() * -1;
        double nThrottle = (throttle + 1) / 2;
        double sOut = speedY * nThrottle;
        return sOut;

    }

    @Override
    public double getDriveDirection(){

        double dOut = stick.getZ();
        return dOut;

    }

    @Override
    public WoFOperation getWoFOperation(){

        WoFOperation opOut = WoFOperation.STOP;

        if (xbox.getXButton()){

            opOut = WoFOperation.LEFT;

        }

        else if (xbox.getBButton()){

            opOut = WoFOperation.RIGHT;

        }

        return opOut;

    }

    @Override
    public ColourSensorOperation getColourSensorOperation(){

        ColourSensorOperation opOut = null;

        if (cS.cMR.color == null){

            opOut = ColourSensorOperation.NULL;

        }

        else if (cS.cMR.color == Config.COLOUR_RED){

            opOut = ColourSensorOperation.RED;

        }

        else if (cS.cMR.color == Config.COLOUR_BLUE){

            opOut = ColourSensorOperation.BLUE;

        }

        else if (cS.cMR.color == Config.COLOUR_YELLOW){

            opOut = ColourSensorOperation.YELLOW;

        }

        else if (cS.cMR.color == Config.COLOUR_GREEN){

            opOut = ColourSensorOperation.GREEN;

        }
        
        return opOut;

    }

    @Override
    public BallGrabOperation getBallGrabOperation(){

        BallGrabOperation opOut = BallGrabOperation.STOP;

        if (stick.getRawButton(Config.BG_SIN_BUTTON)){

            opOut = BallGrabOperation.S_IN;

        }

        else if (stick.getRawButton(Config.BG_SOUT_BUTTON)){

            opOut = BallGrabOperation.S_OUT;

        }

        else if (stick.getRawButton(Config.BG_FIN_BUTTON)){

            opOut = BallGrabOperation.F_IN;

        }

        else if (stick.getRawButton(Config.BG_FOUT_BUTTON)){

            opOut = BallGrabOperation.F_OUT;

        }

        return opOut;

    }

}