
package frc.robot.gamecomponents;
import edu.wpi.first.wpilibj.*;
import frc.robot.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
public class DriveBase{
private SpeedController LS = new Spark(Config.DB_LEFTSIDE_CHANNEL);
private SpeedController RS = new Spark(Config.DB_RIGHTSIDE_CHANNEL);
private DifferentialDrive DB = new DifferentialDrive(LS, RS);
public void manualDrive(double speed, double direction){
DB.arcadeDrive(speed, Config.DIRECTION_SENSITIVITY * direction);
}
}