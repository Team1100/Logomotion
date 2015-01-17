package org.team1100.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class LaunchpadController extends Joystick{

	private JoystickButton exampleA;
	private JoystickButton exampleB;
	
	/**
	 * Initializes the inputs controlled by the Launchpad
	 * @param channel - Port the launchpad is plugged into
	 */
	public LaunchpadController(int channel) {
		super(channel);
		
		exampleA = new JoystickButton(this, 1);
		exampleB = new JoystickButton(this, 2);
	}
	
	/**
	 * Gets the example button
	 * 
	 * @return the example button
	 */
	public JoystickButton getExampleA(){
		return exampleA;
	}
	public JoystickButton getExampleB(){
		return exampleB;
	}

	
}
