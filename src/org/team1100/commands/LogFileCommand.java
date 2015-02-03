package org.team1100.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import org.team1100.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LogFileCommand extends Command {

	private File logFile;
	private PrintWriter writer;
	private DateFormat time;
	private boolean isWorking = true;

	private long lastTime;
	private final long UPDATE_TIME = 1000; // ms

	private Hashtable<String, Double> values;

	public LogFileCommand() {
		values = new Hashtable<String, Double>();
	}

	protected void initialize() {
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
		time = new SimpleDateFormat("HH:mm:ss");
		String fileName = df.format(Calendar.getInstance().getTime());

		File directory = new File("/media/sda1/logs");
		if (directory.exists() && directory.isDirectory()) {
			logFile = new File(directory.getAbsolutePath() + "/" + fileName);
		} else {
			logFile = new File("/home/lvuser/robot/" + fileName);
		}

		try {
			writer = new PrintWriter(logFile);
		} catch (FileNotFoundException e) {
			isWorking = false;
		}


		lastTime = 0;
	}

	public void putNumber(String name, double value) {
		values.put(name, value);
	}
	
	public void removeKey(String name){
		values.remove(name);
	}

	protected void execute() {
		if (!isWorking)
			return;
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastTime >= UPDATE_TIME) {
			writer.printf("[%s] ", time.format(Calendar.getInstance().getTime()));
			for (String s : values.keySet())
				writer.printf("%s: %.2f ", s, values.get(s));
			writer.println();
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
