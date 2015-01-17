package org.team1100.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class LaunchpadController extends Joystick{
	private JoystickButton[] buttons;
	
	/**
	 * Initializes the inputs controlled by the Launchpad
	 * @param channel - Port the launchpad is plugged into
	 */
	public LaunchpadController(int channel) {
		super(channel);
		
		for (int i = 0; i <= 20; i++){
			buttons[i] = new JoystickButton(this, i);
		}
	}

	/**
	 * Gets specified button
	 *  
	 * @param number the button number
	 * @return the Button Object corresponding to the number
	 */
	public JoystickButton getButton(int number){
		return buttons[number];
	}

	
}
