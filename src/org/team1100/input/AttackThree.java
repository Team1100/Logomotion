package org.team1100.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *
 */
public class AttackThree extends Joystick {

	private JoystickButton button[] = new JoystickButton[11];
	private double deadband;

	/**
	 * Initializes a Joystick on a specific channel, mapping the buttons. The
	 * Joystick will never return a value in between +/- the beadband value.
	 * 
	 * @param channel the channel the Joystick is plugged into
	 * @param deadband the value of the deadband, from 0 to 1
	 */
	public AttackThree(int channel, double deadband) {
		super(channel);

		for (int i = 0; i < 11; i++) {
			button[i] = new JoystickButton(this, i + 1);
		}

		this.deadband = deadband;
	}

	/**
	 * Gets the specified button on this controller
	 *
	 * @param number the number of the button on the Joystick
	 * @return the Button corresponding the the number, starting at 1
	 */
	public JoystickButton getButton(int number) {
		return button[number - 1];
	}

	/**
	 * Gets position of a specific axis, accounting for the deadband
	 *
	 * @param axis the AxisType to retrieve
	 * @return the value of the axis, with the deadband factored in
	 */
	public double getAxis(AxisType axis) {
		double val = super.getAxis(axis);
		if (Math.abs(val) <= deadband) {
			val = 0.0;
		}
		return val;
	}

	/**
	 * Gets the angle at which the Joystick is moved based on the x and y axes.
	 * This is used for mecanum drive
	 * 
	 * @return the angle in degrees formed by the Joystick handle
	 */
	public double getAngle() {
		//TODO Check Validity of this function
		double x = -getAxis(Joystick.AxisType.kX);
		double y = -getAxis(Joystick.AxisType.kY);
		double angle = Math.toDegrees(Math.atan2(x, y)); // change x and y
		while (angle < 0) {
			angle += 360;
		}
		return angle;
	}

	/**
	 * Gets the magnitude of the Joystick, ignoring which axis it's on. This is
	 * used for mecanum
	 *
	 * @return the magnitude of the Joystick
	 */
	public double getMagnitude() {
		double x = getAxis(Joystick.AxisType.kX);
		double y = getAxis(Joystick.AxisType.kY);
		return Math.sqrt((x * x) + (y * y));
	}
}
