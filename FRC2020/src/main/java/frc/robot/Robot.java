package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.controller.operations.*;

import edu.wpi.first.wpilibj.*;
import frc.robot.controller.*;
import frc.robot.gamecomponents.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.util.Color;

public class Robot extends TimedRobot{
  private ControllerClass controller = null;
  private DriveBase driveBase = null;
  private WoF WoF = null;
  //private ColourSpinner colourSpinner = null;
  
  //Need To Move Later
  private SpeedController ballGrab = new VictorSP(Config.BALL_GRAB_PORT);
  private SpeedController auxMotor = new VictorSP(Config.AUX_MOTOR_PORT);

  DigitalInput hallEffect = new DigitalInput(Config.HALL_EFFECT);
  DigitalInput hallEffect2 = new DigitalInput(Config.HALL_EFFECT2);
  DigitalInput limitSwitch = new DigitalInput(Config.LIMITSWITCH);
  DigitalInput limitSwitch2 = new DigitalInput(Config.LIMITSWITCH2);

  ColorSensorV3 colSens = new ColorSensorV3(I2C.Port.kOnboard);  
  ColorMatch matcher;
  Color wheelRed = new Color(0.390, 0.410, 0.183);
  Color wheelBlue = new Color(0.156, 0.439, 0.398);
  Color wheelYellow = new Color(0.302, 0.531, 0.164);
  Color wheelGreen = new Color(0.201, 0.541, 0.257);
  @Override
  public void robotInit(){
    matcher = new  ColorMatch();
    matcher.addColorMatch(wheelRed);
    matcher.addColorMatch(wheelBlue);
    matcher.addColorMatch(wheelGreen);
    matcher.addColorMatch(wheelYellow);
    matcher.setConfidenceThreshold(0.95);
  }

  public void driveBaseInit(){
    driveBase = new DriveBase();
  }

  @Override
  public void autonomousInit(){

  }

  @Override
  public void autonomousPeriodic(){

  }

  @Override
  public void teleopInit(){
    controller = new TeleopController();
    if(driveBase == null){
      driveBaseInit();
    }
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          //Blue case code
          break;
        case 'G' :
          //Green case code
          break;
        case 'R' :
          //Red case code
          break;
        case 'Y' :
          //Yellow case code
          break;
        default :
          //This is corrupt data
          break;
      }
    } else {
      //Code for no data received yet
    }
  
  
  } 
  @Override
  public void teleopPeriodic(){
    operateRobot();
    
  
    Color readColour = colSens.getColor();
    ColorMatchResult res = matcher.matchColor(readColour);
    if (res == null)
    {
      SmartDashboard.putString("Colour", "Unknown");
    }

    else if (res.color == wheelRed)
    {
      SmartDashboard.putString("Colour", "Red");
    }
    else if (res.color == wheelGreen)
    {
      SmartDashboard.putString("Colour", "Green");
    }
    else if (res.color == wheelBlue)
    {
      SmartDashboard.putString("Colour", "Blue");
    }
    else if (res.color == wheelYellow)
    {
      SmartDashboard.putString("Colour", "Yellow");
    }
    else
    {
      SmartDashboard.putString("Colour", "Unknown");
    }
    if (limitSwitch.get()) {
      SmartDashboard.putBoolean("LimitSW", false);
    }
    else {
      SmartDashboard.putBoolean("LimitSW", true);
    }
    if (limitSwitch2.get()) {
      SmartDashboard.putBoolean("LimitSW2", false);
    }
    else {
      SmartDashboard.putBoolean("LimitSW2", true);
    }
    if (hallEffect.get()) {
      SmartDashboard.putBoolean("HALL EFFECT", false);
    } else {
      SmartDashboard.putBoolean("HALL EFFECT", true);
    }
    if (hallEffect2.get()) {
      SmartDashboard.putBoolean("HALL EFFECT 2", false);
    } else {
      SmartDashboard.putBoolean("HALL EFFECT 2", true);
    }
    
  
  
  
  
  
  }

  //SmartDashboard.putNumber("Red", colourSpinner.detectedColor.red);
  //SmartDashboard.putNumber("Green", colourSpinner.detectedColor.green);
  //SmartDashboard.putNumber("Blue", colourSpinner.detectedColor.blue);

@Override 
public void disabledPeriodic()
{
  Color detectedColor = colSens.getColor();
  SmartDashboard.putNumber("Red", detectedColor.red);
  SmartDashboard.putNumber("Green", detectedColor.green);
  SmartDashboard.putNumber("Blue", detectedColor.blue);
 

}


  private void operateRobot(){
    driveDriveBase();
    runGrab();
    runAux();
  }
  

  private void driveDriveBase(){
    double Speed = controller.getDriveSpeed();
    double Direction = controller.getDriveDirection();
    driveBase.manualDrive(Speed, Direction);
  }

  private void runGrab(){
    boolean GrabIn = controller.ballGrabIn();
    boolean GrabOut = controller.ballGrabOut();
    boolean GrabInSlow = controller.ballGrabInSlow();
    boolean GrabOutSlow = controller.ballGrabOutSlow();

    if (GrabIn == true){
      ballGrab.set(Config.BALL_IN_SPEED);
    }
    else if (GrabOut == true){
      ballGrab.set(Config.BALL_OUT_SPEED);
    }
    else if (GrabInSlow == true){
      ballGrab.set(Config.BALL_IN_SPEED_S);
    }
    else if (GrabOutSlow == true){
      ballGrab.set(Config.BALL_OUT_SPEED_S);
    }
    else{
      ballGrab.set(0);
    }
  }
  private void runAux(){
    boolean auxIN = controller.auxMotorIn();
    boolean auxOUT = controller.auxMotorOut();
    boolean auxStop = controller.auxMotorStop();
  
    if (auxIN == true){
      auxMotor.set(Config.AUX_MOTOR_SPEED_IN);
    }
    else if (auxOUT == true){
      auxMotor.set(Config.AUX_MOTOR_SPEED_OUT);
    }
    else if (auxStop == true)
    auxMotor.set(0);
  } 
  private void operateWoF(){
    WoFOperation op = controller.getWoFOperation();
    switch (op) {
    case LEFT:
    WoF.left();
    case RIGHT:
    WoF.right();
    case STOP:
    WoF.stop();
    }
  }






}

