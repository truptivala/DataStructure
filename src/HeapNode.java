//Heap - Binary tree representation

@SuppressWarnings("hiding")
public class HeapNode<StudentDetails>
{
	//count - makes track of number of nodes in heap
	
	public int count = 0;
	
	//root, left, right, parent elements of HeapNode
	
	private HeapNode<StudentDetails> root, left, right, parent;
	
	//StudentDetails object
	
	private StudentDetails element;
	
	//lastNode - keeps track of last node of heap
	
	public HeapNode<StudentDetails> lastNode;
	
	//default constructor
	
	public HeapNode ()
	{
		root = null;
		parent = null;
		left = right = null;
	}
	
	//parameterized constructor
	
	public HeapNode(StudentDetails obj)
	{
		element = obj;
		parent = null;
		left = right = null;
	}
	
	//insert function - to insert student object
	
	public void insert(StudentDetails obj)
	{
		HeapNode<StudentDetails> node = new HeapNode<StudentDetails> (obj);
		//for the first element in heap
		if(root == null)
			root = node;
		else
		{
			//method to find parent element of element to be added
			HeapNode<StudentDetails> nextParent = getNextParentAdd();
			//add as a left child if it is found null
			if(nextParent.left == null)
				nextParent.left = node;
			else
				//add as a right child if it is null
				nextParent.right = node;

			node.parent = nextParent;
		}
		//updating lastNode
		lastNode = node;
		//updating count - number of nodes in heap
		count++;
		if(count > 1)
			heapifyAdd();
	}

	private HeapNode<StudentDetails> getNextParentAdd() 
	{
		HeapNode<StudentDetails> result = lastNode;
		//update result value if result is not root and left element of its parent is not result, 
		while((result != root) && (result.parent.left != result))
			result = result.parent;
		
		if(result != root)
			//if right node of parent is null, return current result
			if(result.parent.right == null)
				return result.parent;
			else
			{
				//update result value
				result = result.parent.right;
				//get the leaf of left node
				while(result.left != null)
					result = result.left;
			}
		//get the leaf of left node
		else
		{
			while(result.left != null)
				result = result.left;
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	
	//heapify - update heap to maintain heap property
	private void heapifyAdd() 
	{
		StudentDetails temp;
		HeapNode<StudentDetails> node = lastNode;
		temp = node.element;
		//compare current node with its parent node and swap nodes when parent node is greater
		while(node != root && ((Comparable<StudentDetails>) temp).compareTo(node.parent.element) < 0)
		{
			node.element = node.parent.element;
			node = node.parent;
		}
		node.element = temp;
	}
	
	//remove method to delete root element
	
	public StudentDetails remove() 
	{
		if(isEmpty())
		{
			System.out.println("Heap is empty!");
			return null;
		}
		else
		{
			StudentDetails result = root.element;
			
			//when heap contains only one element - root is deleted
			if(count == 1)
			{
				root = null;
				lastNode = null;
			}
			else
			{
				//method to get the parent node of node of lastNode
				HeapNode<StudentDetails> nextLast = getLastNode();
				
				//update lastNode as null
				if(lastNode.parent.left == lastNode)
					lastNode.parent.left = null;
				else
					lastNode.parent.right = null;
				
				//update root element as lastNode
				root.element = lastNode.element;
				//update lastNode as parent of lastNode
				lastNode = nextLast;
				//heapify - to maintain heap property
				heapifyRemove();
			}
			//decrement count value
			count--;
			return result;
		}
	}
	
	//method to get the parent of node to be replaced with root node
	private HeapNode<StudentDetails> getLastNode()
	{
		HeapNode<StudentDetails> result = lastNode;
		
		//while result is not root and lastNode is left child, update result as parent node of the lastNode
		while(result != root && result.parent.left == result)
			result = result.parent;
		
		//go to left leaf if result is not root
		if(result != root)
			result = result.parent.left;
		
		//go to the right leaf till it is not null
		while(result.right != null)
			result = result.right;
		
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	//method to maintain heap property after delete node
	private void heapifyRemove()
	{
		StudentDetails temp;
		HeapNode<StudentDetails> next;
		HeapNode<StudentDetails> node = root;
		HeapNode<StudentDetails> leftNode = node.left, rightNode = node.right;
		
		//get the first value of next node
		if(leftNode == null && rightNode == null)
			next = null;
		else if(leftNode == null)
			next = rightNode;
		else if(rightNode == null)
			next = leftNode;
		else if(((Comparable<StudentDetails>)leftNode.element).compareTo(rightNode.element) < 0)
			next = leftNode;
		else
			next = rightNode;
		
		temp = node.element;
		
		//swap values of nodes when parent node value is greater than child node
		while((next != null) && (((Comparable<StudentDetails>)next.element).compareTo(temp)<0))
		{
			node.element = next.element;
			node = next;
			leftNode = node.left;
			rightNode = node.right;
			
			if(leftNode == null && rightNode == null)
				next = null;
			else if(leftNode == null)
				next = rightNode;
			else if(rightNode == null)
				next = leftNode;
			else if(((Comparable<StudentDetails>)leftNode.element).compareTo(rightNode.element) < 0)
				next = leftNode;
			else
				next = rightNode;
		}
		node.element = temp;
	}

	//method to get the top most element of the heap
	public StudentDetails peek()
	{
		return root.element;
	}
	
	//method to check whether heap is empty or not
	private boolean isEmpty() {
		return (count == 0);
	}
	
	//method to get number of nodes in heap
	public int size()
	{
		return count;
	}
}
