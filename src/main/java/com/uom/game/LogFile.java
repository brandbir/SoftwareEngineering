package main.java.com.uom.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to handles the logs of the MiniGame
 * Every Log is to be logged in a specific log file
 *
 */
public class LogFile
{
	private static final String LOG_PATH = "miniGame-logs.txt";
	private static File logFile = null;
	
	/**
	 * Logs an error to the Log File
	 * @param error The error to be logged
	 */
	public static void logError(String error)
	{
		if(logFile == null)
		{
			//Creating a new Directory for the logs
			File dir = new File("logs");
			dir.mkdir();
			logFile = new File(dir, LOG_PATH);
			
			try
			{
				logFile.createNewFile();
			} 
			catch (IOException e)
			{
				System.out.println("A problem occured while miniGame was trying to create a new File");
			}
		}
		
		try
		{
			FileWriter writer = new FileWriter(logFile);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("[" + System.currentTimeMillis() + "]" + error);
			bw.close();
		} 
		catch (IOException e)
		{
			System.out.println("miniGame was unable to write to the logFile");
		}
	}
}
