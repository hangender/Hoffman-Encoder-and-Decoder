package cop5536sp17project;

public class binaryHeap { //this is a min heap
	TreeNode heap[]; int heapSize;
	int maxSizeInt = 1000000; //use an array to store frequency of numbers. Numbers range from [0,maxSizeInt]
	
	binaryHeap ()
	{
		heap = new TreeNode[maxSizeInt+1]; //plus 1 since we don't store items in heap[0]
		heapSize = 1;
	}
	
	void MaxHeapifyUp(int d)
	{
		while (d>0)
		{
			int val = heap[d].val;
			int rightVal = (2*d+1)>=heapSize?-1:heap[2*d+1].val;
			int leftVal = (2*d)>=heapSize?-1:heap[2*d].val;
			
			if (rightVal == -1)
			{
				if (leftVal < val)
				{
					TreeNode temp = heap[(2*d)];
					heap[(2*d)] = heap[d];
					heap[d] = temp;
					d = d/2;
				}
				else
				{
					d = 0;
				}
			}
			else 
			{
				if (leftVal < val && leftVal <= rightVal) //left is smallest, and smaller than parent
				{
					TreeNode temp = heap[(2*d)];
					heap[(2*d)] = heap[d];
					heap[d] = temp;
					d = d/2;
				}
				else if (rightVal < val && rightVal < leftVal) //right is smallest, and smaller than parent
				{
					TreeNode temp = heap[(2*d+1)];
					heap[(2*d+1)] = heap[d];
					heap[d] = temp;
					d = d/2;
				}
				else
				{
					d = 0;
				}
			}
		}
		
	}
	
	void MaxHeapifyDown(int d)
	{
		while (d < heapSize)
		{
			int val = heap[d].val;
			int rightVal = (2*d+1)>=heapSize?-1:heap[2*d+1].val;
			int leftVal = (2*d)>=heapSize?-1:heap[2*d].val;
			
			if (rightVal == -1) //no right child
			{
				if (leftVal == -1)
				{
					d = heapSize;
				}
				else if (leftVal < val)
				{
					TreeNode temp = heap[(2*d)];
					heap[(2*d)] = heap[d];
					heap[d] = temp;
					d = 2*d;
				}
				else
				{
					d = heapSize;
				}
			}
			else 
			{
				if (leftVal < val && leftVal <= rightVal) //left is smallest, and smaller than parent
				{
					TreeNode temp = heap[(2*d)];
					heap[(2*d)] = heap[d];
					heap[d] = temp;
					d = 2*d;
				}
				else if (rightVal < val && rightVal < leftVal) //right is smallest, and smaller than parent
				{
					TreeNode temp = heap[(2*d+1)];
					heap[(2*d+1)] = heap[d];
					heap[d] = temp;
					d = 2*d+1;
				}
				else
				{
					d = heapSize;
				}
			}
		}
	}
	
	TreeNode extractMin()
	{
		if (heapSize == 1)
		{
			return null;
		}
		else {
			TreeNode treeMin = heap[1];
			heapSize--;
			heap[1] = heap[heapSize];
			heap[heapSize] = null;
			MaxHeapifyDown(1);
			return treeMin;
		}
	}
	
	void insert(TreeNode element)
	{
		int i = heapSize;
		heapSize++;
		heap[i] = element;
		MaxHeapifyUp((i/2));
	}
	
	boolean hasNext()
	{
		return (heapSize > 1);
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
