package frc.robot.dashboard;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Dashboard{

    private DriverStation dS = DriverStation.getInstance();
    
    public void getGameMessage(){

        String gD = dS.getGameSpecificMessage();

        switch (gD.charAt(0)){

            case 'R':
            SmartDashboard.putString("Match Colour", "RED");

            case 'B':
            SmartDashboard.putString("Match Colour", "BLUE");

            case 'Y':
            SmartDashboard.putString("Match Colour", "YELLOW");

            case 'G':
            SmartDashboard.putString("Match Colour", "GREEN");

        }

    }

    public void displayMessage(String message){

        SmartDashboard.putString("Colour", message);

    }

}