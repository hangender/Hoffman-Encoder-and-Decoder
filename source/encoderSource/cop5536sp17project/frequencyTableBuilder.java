package cop5536sp17project;

import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;

public class frequencyTableBuilder {
	
	int[] table; int maxSizeInt = 1000000; //use an array to store frequency of numbers. Numbers range from [0,maxSizeInt]
	
	frequencyTableBuilder (String fileName)
	{
		table = new int[maxSizeInt];
		for (int i = 0; i < table.length; i++)
		{
			table[i] = 0;
		}
		//init table to all 0's
		
	  try
	  {
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    String line;
	    while ((line = reader.readLine()) != null)
	    {
	    	if (line.length() > 0)
	    	{
	    		table[(Integer.parseInt(line))] = table[(Integer.parseInt(line))] + 1;
	    	}
	    }
	    reader.close();
	  }
	  catch (Exception e)
	  {
	    System.err.format("Exception occurred trying to read '%s'.", fileName);
	    e.printStackTrace();
	  }
	}
	
	int[] freqTable()
	{
		return table;
	}

}
