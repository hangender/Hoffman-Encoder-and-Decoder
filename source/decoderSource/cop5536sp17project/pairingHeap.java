package cop5536sp17project;

import java.util.Vector;

public class pairingHeap {
	
		class pairingHeapNode
		{
			pairingHeapNode child;
			pairingHeapNode left;
			pairingHeapNode right;
			TreeNode v;
			
			pairingHeapNode (TreeNode v)
			{
				this.v = v;
				child = left = right = null;
			}
		}
		class queue
		{
			int queueSize;
			queueItem head;
			
			class queueItem
			{
				pairingHeapNode v;
				queueItem next;
				
				queueItem (pairingHeapNode v)
				{
					this.v = v;
					next = null;
				}
				
				queueItem (pairingHeapNode v, queueItem next)
				{
					this.v = v;
					this.next = next;
				}
			}
			
			queue()
			{
				queueSize = 0;
				head = null;
			}
			
			void push(pairingHeapNode v)
			{
				if (head == null)
				{
					head = new queueItem(v);
				}
				else
				{
					head = new queueItem(v, head);
				}
				queueSize++;
			}
			
			pairingHeapNode pop() //FIFO
			{
				if (head == null)
				{
					return null;
				}
				else
				{
					queueItem temp = head;
					head = head.next;
					queueSize--;
					return temp.v;
				}
			}
			
			boolean isEmpty()
			{
				return (queueSize == 0);
			}
		}
		
	int heapSize;
	pairingHeapNode minRoot;
	
	pairingHeap ()
	{
		heapSize = 0;
		minRoot = null;
	}
	
	void twoPass()
	{
		queue firstPass = new queue();
		
		if (minRoot.child != null)
		{
			pairingHeapNode temp = minRoot.child;
			firstPass.push(temp);
			while (temp.right != null)
			{
				firstPass.push(temp.right);
				temp = temp.right;
			}
		}
		Vector<pairingHeapNode> secondPass = new Vector<pairingHeapNode>(firstPass.queueSize+1,0);
		
		while (!firstPass.isEmpty())
		{
			pairingHeapNode one = firstPass.pop();
			pairingHeapNode two = firstPass.pop();
			if (one != null && two != null)
			{
				if (one.v.val <= two.v.val)
				{
					//one becomes parent of two
					if (one.child != null)
					{
						one.child.left = two;
						two.left = one;
						two.right = one.child;
						one.child = two;
					}
					else
					{
						one.child = two;
						two.left = one;
						
						one.left = null;
						one.right = null;
						two.right = null;
					}
					secondPass.add(one);
				}
				else
				{
					//two becomes parent of one
					pairingHeapNode temp = one;
					one = two;
					two = temp;
					if (one.child != null)
					{
						one.child.left = two;
						two.left = one;
						two.right = one.child;
						one.child = two;
					}
					else
					{
						one.child = two;
						two.left = one;
						
						one.left = null;
						one.right = null;
						two.right = null;
					}
					secondPass.add(one);
				}
			}
			else
			{
				if (one != null)
				{
					secondPass.add(one);
				}
				if (two != null)
				{
					secondPass.add(two);
				}
			}
		}
		
		while (secondPass.size() > 1)
		{
			pairingHeapNode one = secondPass.remove(secondPass.size()-1);
			pairingHeapNode two = secondPass.remove(secondPass.size()-1);
			if (one.v.val <= two.v.val)
			{
				//one becomes parent of two
				if (one.child != null)
				{
					one.child.left = two;
					two.left = one;
					two.right = one.child;
					one.child = two;
				}
				else
				{
					one.child = two;
					two.left = one;
					
					one.left = null;
					one.right = null;
					two.right = null;
				}
				secondPass.add(one);
			}
			else
			{
				//two becomes parent of one
				pairingHeapNode temp = one;
				one = two;
				two = temp;
				if (one.child != null)
				{
					one.child.left = two;
					two.left = one;
					two.right = one.child;
					one.child = two;
				}
				else
				{
					one.child = two;
					two.left = one;
					
					one.left = null;
					one.right = null;
					two.right = null;
				}
				secondPass.add(one);
			}
		}
		if (secondPass.size() > 0)
		{
			minRoot = secondPass.remove(0);
		}
		else
		{
			minRoot = null;
		}
	}
	
	TreeNode extractMin()
	{
		TreeNode treeMin = minRoot.v;
		twoPass();
		heapSize--;
		return treeMin;
	}
	
	void insert(TreeNode element)
	{
		heapSize++;
		pairingHeapNode newNode = new pairingHeapNode(element);
		
		if (minRoot == null)
		{
			minRoot = newNode;
		}
		else
		{
			if ((newNode.v).val >= (minRoot.v).val)
			{
				if (minRoot.child != null)
				{
					minRoot.child.left = newNode; //root's child's left pointer now points to newNode
					newNode.right = minRoot.child; //newNode's right pointer now points to child
					newNode.left = minRoot;
					minRoot.child = newNode;
				}
				else
				{
					minRoot.child = newNode;
					newNode.left = minRoot;
				}	
			}
			else
			{
				newNode.child = minRoot;
				minRoot.left = newNode;
				minRoot = newNode;
			}
		}
	}
	
	boolean hasNext()
	{
		return (heapSize > 0);
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
