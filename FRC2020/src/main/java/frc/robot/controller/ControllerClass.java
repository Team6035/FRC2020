package frc.robot.controller;

import frc.robot.controller.operations.*;

//import frc.robot.controller.operations.*;


public interface ControllerClass{
    double getDriveSpeed();
    double getDriveDirection();
    boolean ballGrabIn();
    boolean ballGrabOut();
    boolean ballGrabInSlow();
    boolean ballGrabOutSlow();
    boolean auxMotorIn();
    boolean auxMotorOut();
    boolean auxMotorStop();
    WoFOperation getWoFOperation();
}