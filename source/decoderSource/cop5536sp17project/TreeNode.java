package cop5536sp17project;

public class TreeNode {
	int val; //stores sum of external paths of children
	int leafVal; //stores the data
	TreeNode left;
	TreeNode right;
	
	TreeNode (TreeNode left, TreeNode right, int val, int leafVal)
	{
		this.left = left;
		this.right = right;
		this.val = val;
		this.leafVal = leafVal;
	}
}
