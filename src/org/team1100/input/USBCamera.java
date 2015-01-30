package org.team1100.input;

import org.team1100.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;

public class USBCamera {
	private static USBCamera usbServer;
	private CameraServer niServer;
	private Image currentFrame;

	public static USBCamera getInstance() {
		if (usbServer == null) {
			usbServer = new USBCamera();
		}
		return usbServer;
	}

	private USBCamera() {
		niServer = CameraServer.getInstance();
	}

	private class CaptureRunnable implements Runnable {
		String name;

		public CaptureRunnable(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			Image frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
			int id = NIVision.IMAQdxOpenCamera(name,
					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			NIVision.IMAQdxConfigureGrab(id);
			NIVision.IMAQdxStartAcquisition(id);

			while (true) {
				NIVision.IMAQdxGrab(id, frame, 1);
				niServer.setImage(frame);
				currentFrame = frame;
			}

		}

	}

	public void start() {
		CaptureRunnable runnable = new CaptureRunnable(RobotMap.CAMERA_NAME);
		Thread captureThread = new Thread(runnable);
		captureThread.start();
	}
	
	public Image getCurrentFrame(){
		return currentFrame;
	}
}
