package cop5536sp17project;

import java.io.IOException;
import java.util.Vector;

public class testMainClass {

	public static void main(String[] args) throws IOException {
		
		for(int i = 0; i < args.length; i++) { //get terminal args
            //System.out.println(args[i]);
        }
		//System.out.println(System.getProperty("user.dir")); //returns C:\Users\hangender\workspace\cop5536sp17project
		//System.out.println(System.getProperty("file.separator")); //"file.separator" string

		/*
		//encoder part
		String readFile = "C:\\Users\\hangender\\workspace\\cop5536sp17project\\sample_input_small.txt";
		frequencyTableBuilder freqTable = new frequencyTableBuilder(readFile);
		int[] v = freqTable.freqTable();
		
		String outFile = System.getProperty("user.dir") + System.getProperty("file.separator") + "code_table.txt";
		String outFile2 = System.getProperty("user.dir") + System.getProperty("file.separator") + "encoded.bin";
		*/
       
		/*
		binaryHeap binHeap = new binaryHeap();
		
		for (int i = 0; i < v.length; i++)
		{
			if (v[i]>0)
			{
				binHeap.insert(new TreeNode(null, null, v[i], i));
				//System.out.println(i + " " + v[i]);
			}
		}
		
		while (binHeap.hasNext())
		{
			TreeNode temp = binHeap.extractMin();
			System.out.println(temp.leafVal + " " + temp.val);
		}
		*/
		
		/*
		fourAryHeap fourHeap = new fourAryHeap();
		
		for (int i = 0; i < v.length; i++)
		{
			if (v[i]>0)
			{
				fourHeap.insert(new TreeNode(null, null, v[i], i));
				//System.out.println(i + " " + v[i]);
			}
		}
		
		while (fourHeap.hasNext())
		{
			TreeNode temp = fourHeap.extractMin();
			System.out.println(temp.leafVal + " " + temp.val);
		}
		*/
		
		/*
		pairingHeap pairHeap = new pairingHeap();
		
		for (int i = 0; i < v.length; i++)
		{
			if (v[i]>0)
			{
				pairHeap.insert(new TreeNode(null, null, v[i], i));
				//System.out.println(i + " " + v[i]);
			}
		}
		
		while (pairHeap.hasNext())
		{
			TreeNode temp = pairHeap.extractMin();
			System.out.println(temp.leafVal + " " + temp.val);
		}
		*/
		
		/*
		long timer;
		
		timer = System.currentTimeMillis();
		for (int i = 0; i <= 10; i++)
		{
			pairingHeap pairHeap = new pairingHeap();
			TreeNode hoffmanTree = pairHeap.buildTree(v);
	
			hoffmanPrintCode printer= new hoffmanPrintCode();
			printer.printCode(hoffmanTree, "");
		}
		System.out.println("pairing heap took: " + (System.currentTimeMillis() - timer) + "ms");
		
		timer = System.currentTimeMillis();
		for (int i = 0; i <= 10; i++)
		{
			binaryHeap binHeap = new binaryHeap();
			TreeNode hoffmanTree = binHeap.buildTree(v);
	
			hoffmanPrintCode printer= new hoffmanPrintCode();
			printer.printCode(hoffmanTree, "");
		}
		System.out.println("binary heap took: " + (System.currentTimeMillis() - timer) + "ms");
		
		timer = System.currentTimeMillis();
		for (int i = 0; i <= 10; i++)
		{
			fourAryHeap fourHeap = new fourAryHeap();
			TreeNode hoffmanTree = fourHeap.buildTree(v);
	
			hoffmanPrintCode printer= new hoffmanPrintCode();
			printer.printCode(hoffmanTree, "");
		}
		System.out.println("dary heap took: " + (System.currentTimeMillis() - timer) + "ms");
		*/
		
		/*
		fourAryHeap fourHeap = new fourAryHeap();
		TreeNode hoffmanTree = fourHeap.buildTree(v);

		hoffmanPrintCode printer= new hoffmanPrintCode(outFile, outFile2, readFile); //code_table, encoded, sample_input
		printer.printCode(hoffmanTree, "");
		printer.printCodeTable();
		printer.encode();
		
		System.out.println("code_table.txt and encoded.bin are at dir: " + System.getProperty("user.dir"));
		*/
		
		//decoder part
		//String codeTable = "C:\\Users\\hangender\\workspace\\cop5536sp17project\\code_table.txt"; //get from terminal args
		//String encoderBin = "C:\\Users\\hangender\\workspace\\cop5536sp17project\\encoded.bin"; //get from terminal args
       
		String codeTable = args[1];
		String encoderBin = args[0]; 
		
		String decodedText = System.getProperty("user.dir") + System.getProperty("file.separator") + "decoded.txt";

		hoffmanDecoder decoder = new hoffmanDecoder(codeTable, encoderBin, decodedText);
		TreeNode decoderTree = decoder.buildDecoderTree();
		//decoder.printCode(decoderTree, "");
		decoder.decode();
		
		System.out.println("decoded.txt is at dir: " + System.getProperty("user.dir"));
	}
		
}
