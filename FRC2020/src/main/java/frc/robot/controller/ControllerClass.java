package frc.robot.controller;

import frc.robot.controller.operations.*;

public interface ControllerClass {

    double getDriveSpeed();
    double getDriveDirection();

    WoFOperation getWoFOperation();
    ColourSensorOperation getColourSensorOperation();
    BallGrabOperation getBallGrabOperation();

}