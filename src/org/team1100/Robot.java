package org.team1100;

import org.team1100.commands.drive.DriveCommand;
import org.team1100.subsystems.CANSubsystem;
import org.team1100.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveSubsystem driveTrain;
	public static CANSubsystem CAN;
	public static OI OI;

	Command autonomousCommand;

	public void robotInit() {
		driveTrain = new DriveSubsystem();
		CAN = new CANSubsystem();
		OI = new OI();
		
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(CAN);
		autonomousCommand = new DriveCommand(.8, .8, 3);
	}
	
	public void autonomousInit() {
		autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void teleopInit() {
		autonomousCommand.cancel();
	}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}
	
	public void testPeriodic() {
		LiveWindow.run();
		log();
	}

	public void disabledInit() {
	}	
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	private void log(){
		
	}
}
