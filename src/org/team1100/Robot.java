package org.team1100;

import org.team1100.subsystems.DriveSubsystem;
import org.team1100.subsystems.Elevator;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	public static OI OI;
	public static Elevator elevator;

	public void robotInit() {
		driveTrain = new DriveSubsystem();
		OI = new OI();
		elevator = new Elevator();
		
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(elevator);
	}
	
	public void autonomousInit() {
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void teleopInit() {
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
		SmartDashboard.putBoolean("Digital 0", elevator.getDigital0());
		SmartDashboard.putBoolean("Digital 1", elevator.getDigital1());
	}
}
