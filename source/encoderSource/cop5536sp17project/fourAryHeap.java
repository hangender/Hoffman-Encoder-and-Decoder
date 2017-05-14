package cop5536sp17project;

public class fourAryHeap {
	TreeNode heap[]; int heapSize;
	int maxSizeInt = 1000000; //use an array to store frequency of numbers. Numbers range from [0,maxSizeInt]
	
	fourAryHeap () //min dary heap
	{
		heap = new TreeNode[maxSizeInt+1+2]; //plus 1 since we don't store items in heap[0], +2 to shift over to align cache
		heapSize = 1+2;
	}
	
	void MaxHeapifyUp(int d)
	{
		while (d > 2)
		{
			int val = heap[d].val;
			int firstChild = ((d-3)*4+1+3)>=heapSize?-1:heap[((d-3)*4+1+3)].val;
			int secondChild = ((d-3)*4+2+3)>=heapSize?-1:heap[((d-3)*4+2+3)].val;
			int thirdChild = ((d-3)*4+3+3)>=heapSize?-1:heap[((d-3)*4+3+3)].val;
			int fourthChild = ((d-3)*4+4+3)>=heapSize?-1:heap[((d-3)*4+4+3)].val;
			
			int currentMin = val;
			int currentAddress = d;
			
			if (firstChild != -1 && firstChild < currentMin)
			{
				currentMin = firstChild;
				currentAddress = ((d-3)*4+1+3);
			}
			if (secondChild != -1 && secondChild < currentMin)
			{
				currentMin = secondChild;
				currentAddress = ((d-3)*4+2+3);
			}
			if (thirdChild != -1 && thirdChild < currentMin)
			{
				currentMin = thirdChild;
				currentAddress = ((d-3)*4+3+3);
			}
			if (fourthChild != -1 && fourthChild < currentMin)
			{
				currentMin = fourthChild;
				currentAddress = ((d-3)*4+4+3);
			}
			
			if (currentMin < val)
			{
				TreeNode temp = heap[currentAddress];
				heap[currentAddress] = heap[d];
				heap[d] = temp;
				d = (((d)/4)+2);
			}
			else {
				d = 2;
			}
		}
	}
	
	void MaxHeapifyDown(int d)
	{
		while (d < heapSize)
		{
			int val = heap[d].val;
			int firstChild = ((d-3)*4+1+3)>=heapSize?-1:heap[((d-3)*4+1+3)].val;
			int secondChild = ((d-3)*4+2+3)>=heapSize?-1:heap[((d-3)*4+2+3)].val;
			int thirdChild = ((d-3)*4+3+3)>=heapSize?-1:heap[((d-3)*4+3+3)].val;
			int fourthChild = ((d-3)*4+4+3)>=heapSize?-1:heap[((d-3)*4+4+3)].val;
			
			int currentMin = val;
			int currentAddress = d;
			
			if (firstChild != -1 && firstChild < currentMin)
			{
				currentMin = firstChild;
				currentAddress = ((d-3)*4+1+3);
			}
			if (secondChild != -1 && secondChild < currentMin)
			{
				currentMin = secondChild;
				currentAddress = ((d-3)*4+2+3);
			}
			if (thirdChild != -1 && thirdChild < currentMin)
			{
				currentMin = thirdChild;
				currentAddress = ((d-3)*4+3+3);
			}
			if (fourthChild != -1 && fourthChild < currentMin)
			{
				currentMin = fourthChild;
				currentAddress = ((d-3)*4+4+3);
			}
			
			if (currentMin < val)
			{
				TreeNode temp = heap[currentAddress];
				heap[currentAddress] = heap[d];
				heap[d] = temp;
				d = currentAddress;
			}
			else {
				d = heapSize;
			}
		}
	}
	
	TreeNode extractMin()
	{
		if (heapSize == 3)
		{
			return null;
		}
		else {
			TreeNode treeMin = heap[3];
			heapSize--;
			heap[3] = heap[heapSize];
			heap[heapSize] = null;
			MaxHeapifyDown(3);
			return treeMin;
		}
	}
	
	void insert(TreeNode element)
	{
		int i = heapSize;
		heapSize++;
		heap[i] = element;
		MaxHeapifyUp(((i)/4)+2);
	}
	
	boolean hasNext()
	{
		return (heapSize > 3);
	}
	
	//(TreeNode left, TreeNode right, int val, int leafVal)
	TreeNode buildTree(int[] v)
	{
		for (int i = 0; i < v.length; i++)
		{
			if (v[i]>0)
			{
				insert(new TreeNode(null, null, v[i], i));
				//System.out.println(i + " " + v[i]);
			}
		}
		
		while (hasNext())
		{
			TreeNode one = extractMin();
			TreeNode two = null;
			if (hasNext())
			{
				two = extractMin();
				TreeNode newNode = new TreeNode(one, two, one.val + two.val, -1); //-1 to indicate internal node
				insert(newNode);
			}
			else
			{
				return one;
			}
		}
		return null;
	}
}
