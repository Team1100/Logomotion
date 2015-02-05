package org.team1100.subsystems;

import org.team1100.Robot;
import org.team1100.RobotMap;
import org.team1100.commands.manipulator.ElevatorCommand;
import org.team1100.input.XboxController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
    
	private double SPEED_PERCENT = 1;
	
	private Jaguar jag1;
	private Jaguar jag2;
	private Encoder encoder;
	
	public Elevator(){
		jag1 = new Jaguar(RobotMap.M_ELEVATOR_CIM_1);
		jag2 = new Jaguar(RobotMap.M_ELEVATOR_CIM_2);
		encoder = new Encoder(RobotMap.M_ENCODER_A, RobotMap.M_ENCODER_B);
		encoder.reset();
	}
	
	public void userLift(){
		double speed = 0;
		double leftSpeed = Robot.OI.getXboxController().getAxis(XboxController.XboxAxis.kLeftTrigger);
		double rightSpeed = Robot.OI.getXboxController().getAxis(XboxController.XboxAxis.kRightTrigger);
		
		if (leftSpeed != 0)
			speed = leftSpeed * SPEED_PERCENT;
		else if (rightSpeed != 0)
			speed = -rightSpeed  * SPEED_PERCENT;
		
		lift(speed);
	}	
	
	public void lift(double speed){
		jag1.set(speed);
		jag2.set(speed);
	}
	
	public void stop(){
		jag1.set(0);
		jag2.set(0);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorCommand());
	}
	
	public double getPosition(){
		return encoder.get();
	}
	
	public void resetEncoder(){
		encoder.reset();
	}
	
	public double getSpeed(){
		return encoder.getRate();
	}
}

