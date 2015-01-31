package org.team1100.input;

import org.team1100.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class LogitechCamera extends USBCamera {
	private static LogitechCamera camera;
	private CameraServer server;
	private Image currentFrame;

	public static LogitechCamera getInstance() {
		if (camera == null) {
			camera = new LogitechCamera(RobotMap.CAMERA_NAME);
		}
		return camera;
	}

	private LogitechCamera(String name) {
		super(name);
		openCamera();
		server = CameraServer.getInstance();
	}

	public void start() {
		server.startAutomaticCapture(this);
	}
	
	public Image getCurrentFrame(){
		getImage(currentFrame);
		return currentFrame;
	}
}
