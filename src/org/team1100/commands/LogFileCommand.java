package org.team1100.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.team1100.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LogFileCommand extends Command {

    private File logFile;
    private PrintWriter writer;
    private Calendar calobj;
    private DateFormat time;
    private boolean isWorking = true;
    
    private long lastTime;
    private final long UPDATE_TIME = 1000; //ms

	public LogFileCommand() {
		requires(Robot.CAN);
    }

    protected void initialize() {
    	DateFormat df = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
    	time = new SimpleDateFormat("HH:mm:ss");
		calobj = Calendar.getInstance();
		String fileName = df.format(calobj.getTime());
		
		File directory = new File("/media/sda1/logs");
		
		if (directory.exists() && directory.isDirectory()){
			logFile = new File(directory.getAbsolutePath() + "/" + fileName);
		} else {
			logFile = new File("/home/lvuser/robot/" + fileName);
		}
		
		try {
			writer = new PrintWriter(logFile);
		} catch (FileNotFoundException e) {
			
		}
		
		lastTime = 0;
    }

    protected void execute() {
    	if (!isWorking)
    		return;
    	long currentTime = System.currentTimeMillis();
    	if (currentTime - lastTime >= UPDATE_TIME){
    		writer.printf("[%s] Current @ 0 = %.2f%n",time.format(calobj.getTime()), Robot.CAN.getCurrent(0));
    		lastTime = currentTime;
    	}
    }

    protected boolean isFinished() {
        return !isWorking;
    }

    protected void end() {
    	writer.close();
    }

    protected void interrupted() {
    	end();
    }
}
