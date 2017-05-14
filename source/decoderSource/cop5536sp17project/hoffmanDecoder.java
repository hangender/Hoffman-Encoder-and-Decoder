package cop5536sp17project;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class hoffmanDecoder {
	String codeTable; String binaryData; String decodedText;
	
	int buffer, bufferSize;
	TreeNode v;
	hoffmanDecoder (String codeTable, String binaryData, String decodedText) throws IOException
	{
		//hoffmanDecoder decoder = new hoffmanDecoder(codeTable, encoderBin, decodedText);
		this.codeTable = codeTable;
		this.binaryData = binaryData;
		this.decodedText = decodedText;
		v = null;
		
	    File file = new File (decodedText); //make a new file at output path
	    BufferedWriter out = new BufferedWriter(new FileWriter(file)); //truncates the file if it exists
	    out.write("");
	    out.close();
	    
	    buffer = 0;
	    bufferSize = 0;
	}
	
	TreeNode buildDecoderTree() throws NumberFormatException, IOException
	{
		v = new TreeNode(null, null, -1, -1); //points to "root" of hoffman tree
		TreeNode temp;
		//TreeNode (TreeNode left, TreeNode right, int val, int leafVal)
		
	    BufferedReader reader = new BufferedReader(new FileReader(codeTable));
	    String line;
	    while ((line = reader.readLine()) != null)
	    {
	    	if (line.length() > 0)
	    	{
	    		int i = 0;
	    		while (Character.isDigit(line.charAt(i)))
	    		{
	    			i++;
	    		}
	    		//i should now sit at where the space is

	    		int leafVal = Integer.parseInt(line.substring(0, i));
	    		
	    		i++;
	    		temp = v;
	    		while (i<line.length())
	    		{
	    			if (line.charAt(i) == '0')
	    			{
	    				if (temp.left == null)
	    				{
	    					temp.left = new TreeNode(null, null, -1, -1); //add a node to left
	    					temp = temp.left; //traverse left
	    				}
	    				else
	    				{
	    					temp = temp.left; //traverse left
	    				}
	    			}
	    			else
	    			{
	    				if (temp.right == null)
	    				{
	    					temp.right = new TreeNode(null, null, -1, -1); //add a node to right
	    					temp = temp.right; //traverse right
	    				}
	    				else
	    				{
	    					temp = temp.right; //traverse right
	    				}
	    			}
	    			i++;
	    		}
	    		temp.leafVal = leafVal;
	    	}
	    }
	    reader.close();
	    
		return v;
	}
	
	int bitReading(BufferedInputStream inputStream) throws IOException
	{
		if (buffer == -1)
		{
			return buffer; //eof, so we must return
		}

		if (bufferSize == 0)
		{
			buffer = inputStream.read();
			if (buffer == -1)
			{
				return buffer; //eof, so we must return
			}
			bufferSize = 8; //restore buffer to size 8
		}
		bufferSize--;
		//System.out.println((buffer >>> bufferSize) & 1);
		return (buffer >>> (bufferSize)) & 1;
	}
	
	void decode() throws IOException
	{
	    File file = new File (decodedText); //make a new file at output path
	    BufferedWriter out = new BufferedWriter(new FileWriter(file, true)); //appends to file, writes to decoded.txt
	    
	    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(binaryData)); //encoded.bin
	    
	    int result = bitReading(inputStream);
	    TreeNode temp = v; //start at root of hoffman tree
	    
	    while (result != -1)
	    {
	    	if (result == 0)
	    	{
	    		temp = temp.left;
	    	}
	    	else
	    	{
	    		temp = temp.right;
	    	}
	    	if (temp.leafVal != -1) //check if it is leaf node
	    	{
			    out.write(Integer.toString(temp.leafVal));
			    //System.out.println(temp.leafVal);
			    out.newLine(); //newline
			    temp = v; //back at hoffman root
	    	}
	    	result = bitReading(inputStream);
	    }
	    out.close();
	    inputStream.close();
	}
	
	//should run buildDecoderTree first
	void printCode(TreeNode v, String code)
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
			System.out.println(v.leafVal + " ==> " + code);
		}
	}
}
