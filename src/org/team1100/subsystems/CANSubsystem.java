package org.team1100.subsystems;

import org.team1100.commands.PDPCurrentPrintCommand;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CANSubsystem extends Subsystem {
    
	PowerDistributionPanel pdp;
	
	public CANSubsystem(){
		pdp = new PowerDistributionPanel();
	}
	
	public double getCurrent(int channel){
		return pdp.getCurrent(channel);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new PDPCurrentPrintCommand());
    }
}

