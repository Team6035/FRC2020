package frc.robot.controller;

import frc.robot.*;

//import frc.robot.controller.operations.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Config;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.controller.operations.*;


/**
    * The integer value represents the axis enumeration
    * Axis 1 is the Left Joystick X axis value Left to Right = -1 to 1  
    * Axis 2 is the Left Joystick Y axis value Up to Down = -1 to 1 
    * Axis 3 is the Rear Triggers axis value Left = 0 up to 1 & Right = 0 down to -1
    * Axis 4 is the Right Joystick X axis value Left to Right = -1 to 1  
    * Axis 5 is the Right Joystick Y axis value Up to Down = -1 to 1 
    * Axis 6 is the Direction Pad X axis value Left to Right = -1 to 1
    */

    public class TeleopController implements ControllerClass{
        private Joystick stick = new Joystick(Config.JOYSTICK_PORT);
        private XboxController xbox = new XboxController(Config.XBOX_PORT);

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
        

    

    


    public double getDriveSpeed(){
        double SpeedYRaw = ((stick.getRawAxis(3)*-1) + 1)/2;
        double SpeedY = SpeedYRaw * (stick.getRawAxis(1)*-1);
        double Speed = SpeedY * Config.SPEED_SENSITIVITY;
        return Speed;
    }

    public double getDriveDirection(){
        double DirectionX = stick.getRawAxis(2);
        double Direction = DirectionX * Config.DIRECTION_SENSITIVITY;
        return Direction;
    }

    public boolean ballGrabInSlow(){
        boolean Button9Pressed = stick.getRawButton(9);
        return Button9Pressed;
    }

    public boolean ballGrabOutSlow(){
        boolean Button10Pressed = stick.getRawButton(10);
        return Button10Pressed;
    }
    public boolean auxMotorIn(){
        boolean Button2Pressed = stick.getRawButton(2);
        return Button2Pressed;
    }

    public boolean auxMotorOut(){
        boolean Button1Pressed = stick.getRawButton(1);
        return Button1Pressed;
    }
    public boolean auxMotorStop(){
        boolean Button3Pressed = stick.getRawButton(3);
        return Button3Pressed;
    }

    @Override
    public boolean ballGrabIn() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean ballGrabOut() {
        // TODO Auto-generated method stub
        return false;
    }
    

    
             
         
    






}
