package org.team1100.commands;

import org.team1100.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PDPCurrentPrintCommand extends Command {

    public PDPCurrentPrintCommand() {
    	requires(Robot.CAN);
    }

    protected void initialize() {
    	SmartDashboard.putNumber("CAN Channel", 0);
    }

    protected void execute() {
    	int channel= (int) SmartDashboard.getNumber("CAN Channel");
    	double current = Robot.CAN.getCurrent(channel);
    	SmartDashboard.putNumber("Current", current);
    	
    }

    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    	SmartDashboard.putString("", "CAN Reading Stopped");
    }

    protected void interrupted() {
    }
}
