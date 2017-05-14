package cop5536sp17project;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class hoffmanPrintCode {
	
	String output; String output2; String input;
	int maxSizeInt = 1000000; //use an array to store frequency of numbers. Numbers range from [0,maxSizeInt]
	String[] table;
	
	hoffmanPrintCode(String path, String path2, String input) throws IOException
	{
		this.input = input;
		output = path; //code_table.txt
		output2 = path2; //encoded.bin
		
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output2)); //truncates the file if it exists
		outputStream.close();
				
	    File file = new File (output); //make a new file at output path
	    BufferedWriter out = new BufferedWriter(new FileWriter(file)); //truncates the file if it exists
	    out.write("");
	    out.close();
	    
		table = new String[maxSizeInt];
		for (int i = 0; i < table.length; i++)
		{
			table[i] = null;
		}
		//init table to null
	}
	
	void printCode(TreeNode v, String code) throws IOException
	{
		if (v==null) {return;}
		if (v.left != null)
		{
			printCode(v.left, code + "0");
		}
		if (v.right != null)
		{
			printCode(v.right, code + "1");
		}
		if (v.leafVal != -1)
		{
			//System.out.println(v.leafVal + " ==> " + code);
			/*
		    File file = new File (output); //make a new file at output path
		    BufferedWriter out = new BufferedWriter(new FileWriter(file, true)); //appends to file
		    out.write(v.leafVal + " " + code);
		    out.newLine(); //newline
		    out.close();
		    */
			table[v.leafVal] = code;
		}
	}
	
	//printCodeTable must be ran after printCode
	void printCodeTable() throws IOException
	{
	    File file = new File (output); //make a new file at output path
	    BufferedWriter out = new BufferedWriter(new FileWriter(file, true)); //appends to file
	    
		for (int i = 0; i < table.length; i++)
		{
			if (table[i] != null)
			{
			    out.write(i + " " + table[i]);
			    out.newLine(); //newline
			}
		}

	    out.close();
	}
	
	//encode must be ran after printCode
	void encode() throws NumberFormatException, IOException
	{
	    BufferedReader reader = new BufferedReader(new FileReader(input)); //reads in input file
	    
	    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output2,true)); //appends
	    //DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(output2,true));
		
	    String line;
	    String output = ""; int buffer = 0; int bufferSize = 0;
	    
	    while ((line = reader.readLine()) != null)
	    {
	    	if (line.length() > 0)
	    	{
	    		output = table[(Integer.parseInt(line))];
	    		//System.out.println((Integer.parseInt(line)) + " ==> " + output);
	    		
	    		
	    		for (int i = 0; i < output.length(); i++)
	    		{
	    			if (output.charAt(i) == '0')
	    			{
	    				buffer = (buffer << 1) | 0;
	    			}
	    			else
	    			{
	    				buffer = (buffer << 1) | 1;
	    			}
	    			bufferSize++;
	    			if (bufferSize == 8)
	    			{
	    				outputStream.write(buffer);
	    				buffer = 0;
	    				bufferSize = 0;
	    			}
	    		}
	    		
	    	}

	    }
	    if (bufferSize > 0)
	    {
		    while (8 > bufferSize) //padding the lower ordered bits
		    {
		    	buffer = (buffer << 1) | 0;
		    	bufferSize++;
		    }
			outputStream.write(buffer);
	    }
		buffer = 0;
		bufferSize = 0;
		
		outputStream.close();
	    reader.close();
	}
}
