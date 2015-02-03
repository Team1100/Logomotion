package org.team1100;

import org.team1100.commands.LogFileCommand;
import org.team1100.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	public static OI OI;
	public static LogFileCommand logFileCommand;

	private PowerDistributionPanel pdp;

	public void robotInit() {
		driveTrain = new DriveSubsystem();
		OI = new OI();
		pdp = new PowerDistributionPanel();
		logFileCommand = new LogFileCommand();
		
		SmartDashboard.putData(driveTrain);
	}
	
	public void autonomousInit() {
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void teleopInit() {
		logFileCommand.start();
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
		logFileCommand.cancel();
	}	
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		log();
	}
	
	private void log(){
		logFileCommand.putNumber("Total Current", pdp.getVoltage());
	}
}
