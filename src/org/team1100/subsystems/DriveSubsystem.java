package org.team1100.subsystems;

import org.team1100.Robot;
import org.team1100.RobotMap;
import org.team1100.commands.drive.UserDriveCommand;
import org.team1100.input.XboxController;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	private RobotDrive drive;

	/**
	 * Initializes the DriveSubsystem, using the talon values from RobotMap
	 */
	public DriveSubsystem() {
		Jaguar frontLeftCIM = new Jaguar(RobotMap.D_FRONT_LEFT_CIM);
		Jaguar rearLeftCIM = new Jaguar(RobotMap.D_REAR_LEFT_CIM);
		Jaguar frontRightCIM = new Jaguar(RobotMap.D_FRONT_RIGHT_CIM);
		Jaguar rearRightCIM = new Jaguar(RobotMap.D_REAR_RIGHT_CIM);
		
		drive = new RobotDrive(frontLeftCIM, rearLeftCIM, frontRightCIM, rearRightCIM);
	}

	/**
	 * This is repeatedly called in the UserDriveCommand, allowing the operator
	 * to drive the robot
	 */
	public void userDriveTank() {
		/*
		double leftValue = Robot.OI.getLeftJoystick().getAxis(Joystick.AxisType.kY);
		double rightValue = Robot.OI.getRightJoystick().getAxis(Joystick.AxisType.kY);
		*/
		double leftValue = Robot.OI.getXboxController().getAxis(XboxController.XboxAxis.kYLeft);
		double rightValue = Robot.OI.getXboxController().getAxis(XboxController.XboxAxis.kYRight);
		SmartDashboard.putNumber("Left Value", leftValue);
		SmartDashboard.putNumber("Right Value", rightValue);
		
		
		drive.tankDrive(leftValue, rightValue);
	}

	/**
	 * Drives the robot in tank given the speed
	 * 
	 * @param leftValue the speed of the left side, from -1 to 1
	 * @param rightValue the speed of the right side, from -1 to 1
	 */
	public void driveTank(double leftValue, double rightValue) {
		drive.tankDrive(-leftValue, -rightValue);
	}

	/**
	 * Stops the drive train
	 */
	public void stop() {
		drive.tankDrive(0, 0);
	}

	/**
	 * Sets the default command to drive the robot via an operator. This command
	 * is used when no other commands are using the subsystem
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new UserDriveCommand());
	}

}
