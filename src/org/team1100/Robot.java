package org.team1100;

import org.team1100.commands.LogFileCommand;
import org.team1100.commands.drive.DriveCommand;
import org.team1100.subsystems.CANSubsystem;
import org.team1100.subsystems.DriveSubsystem;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
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
	
	private static CANTalon talon;
	private static Solenoid solenoid;
	private static CameraServer server;

	private Command autonomousCommand;
	private LogFileCommand logFileCommand;

	public void robotInit() {
		driveTrain = new DriveSubsystem();
		CAN = new CANSubsystem();
		OI = new OI();
		//Tests talon communication
		talon = new CANTalon(4);
		//Starts the compressor
		solenoid = new Solenoid(0);
		
		//Camera Testing
        server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("cam0");
        
		
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(CAN);
		autonomousCommand = new DriveCommand(.8, .8, 3);
		logFileCommand = new LogFileCommand();
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
		
	}
}
