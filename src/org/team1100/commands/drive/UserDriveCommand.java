package org.team1100.commands.drive;

import org.team1100.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UserDriveCommand extends Command {

	public UserDriveCommand(){
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.driveTrain.userDriveTank();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveTrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
