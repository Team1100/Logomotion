package org.team1100;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//[D]rive
	public static int D_FRONT_LEFT_CIM = 0;
	public static int D_FRONT_RIGHT_CIM = 1;
	public static int D_REAR_LEFT_CIM = 2;
	public static int D_REAR_RIGHT_CIM = 3;

	//[C]ontrol
    public static final int C_LEFT_JOYSTICK = 2;
    public static final int C_RIGHT_JOYSTICK = 1;
    public static final int C_XBOX_CONTROLLER = 0;
    public static final int C_LAUNCHPAD_CONTROLLER = 3;
    
    //Camera
    public static final String CAMERA_NAME = "cam0";
}
