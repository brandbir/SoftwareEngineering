package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class that handles general methods used by miniGame
 *
 */
public class Misc
{
	public static final int SUCCESS = 1;
	public static final int FAILURE = -1;
	/**
	 * Deletes all files found inside a particular folder
	 * @param path folder path
	 */
	public static void deleteFiles(String path)
	{
		File folder = new File(path);
		String[] subFiles;
		
		if (folder.isDirectory())
		{
			subFiles = folder.list();
			for (int i = 0; i < subFiles.length; i++)
			{
				deleteFile(folder.toString(), subFiles[i]);
			}
		}
	}
	
	/**
	 * Deletes a particular file from the file System
	 * @param folder Folder where the file resides
	 * @param fileName The File name to be deleted
	 */
	public static void deleteFile(String folder, String fileName)
	{
		File file = new File(folder, fileName);
		file.delete();
	}
	
	/**
	 * This method is used to write the HTML Map to a specified path
	 * @param filePath The file to which you want to insert the HTML Maps
	 * @param init initialisation/updating 
	 * @param player Player Object
	 */
	public static int writeToFile(Map map, String filePath, boolean init, Player player)
	{
		String updatedHTML = "";
		int fileCreated = Misc.SUCCESS;
		
		//Getting the updatedHTML the player
		if(!init)
			updatedHTML = Game.updateHTML(map,player);
		
		try
		{
			BufferedWriter w = new BufferedWriter(new FileWriter(filePath));
			
			if(init)
				w.write(Game.generateHTMLCode(map,player));
			else
				w.write(updatedHTML);
			
			w.close();
		}
		
		catch (IOException e)
		{
			LogFile.logError("Game.generateHTMLFiles()::" + e.getMessage());
			fileCreated = Misc.FAILURE;
		}
		
		return fileCreated;
	}
}
