package game;

import java.io.File;

public class Misc
{

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
				File file = new File(folder, subFiles[i]);
				file.delete();
			}
		}
	}
	
}
