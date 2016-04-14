//Array based implementation of Heap
public class ArrayHeap 
{
		//Array of Student class
		private StudentDetails[] data;
	    private int size;
	 
	    //default constructor of class
	    public ArrayHeap() 
	    {
	            data = new StudentDetails[1000];
	            size = 0;
	    }
	    
	    //parameterized constructor of class
	    public ArrayHeap(int num) 
	    {
	            data = new StudentDetails[num];
	            size = 0;
	    }
	 
	    //method to check heap is empty or not
	    public boolean isEmpty() 
	    {
	            return (size == 0);
	    }
	    
	    //method to get number of nodes in heap
	    public int size()
	    {
	    	return size;
	    }
	 
	    //method to print elements of heap
	    public void printAll()
	    {
	    	for(int i = 0; i < size(); i++)
	    	{
	    		System.out.println(data[i]);
	    	}
	    }
	    
	    //method to get array index of left child of current node
	    private int getLeftChildIndex(int nodeIndex) 
	    {
	            return 2 * nodeIndex + 1;
	    }
	 
	    //method to get array index of right child of current node
	    private int getRightChildIndex(int nodeIndex) {
	            return 2 * nodeIndex + 2;
	    }
	 
	    //method to get array index of parent node of current node
	    private int getParentIndex(int nodeIndex) {
	            return (nodeIndex - 1) / 2;
	    }
	 
	    //method to insert element in heap
	    public void insert(StudentDetails obj) 
	    {
	    	//increase the number of nodes when node is inserted
            size++;
            //add the element in the last in array
            data[size - 1] = obj;
            //check heap property after inserting element
            heapifyAdd(size - 1);
	    }    
     
	    //method to verify heap property
	    private void heapifyAdd(int nodeIndex) 
	    {
	    	StudentDetails tmp;
            int parentIndex;
            //if nodeIndex is not root index
            if (nodeIndex != 0) 
            {
            	//get index of parent element
                  parentIndex = getParentIndex(nodeIndex);
                  //swap nodes of parent node is greater than current node
                  if ((data[parentIndex]).compareTo(data[nodeIndex]) > 0) 
                  {
                	  	tmp = data[parentIndex];
                        data[parentIndex] = data[nodeIndex];
                        data[nodeIndex] = tmp;
                        //run heapify method again after swapping 
                        heapifyAdd(parentIndex);
                  }
            }
        }
	    
	    //method to remove root element from heap
	    public void removeMin() 
	    {
	    	//check if heap is empty or not
            if (isEmpty())
            {
            	System.out.println("Heap is empty!");
            	return;
            }
            
            //when array is not empty
            else 
            {
            	//replace value of root with last node
                  data[0] = data[size - 1];
                  
                  //decrement size of heap
                  size--;
                  if (size > 0)
                	  //call heapify method to verify heap property
                        heapifyRemove(0);
            }
	    }
	    
	    //heapify method to check heap property
	    private void heapifyRemove(int nodeIndex) 
	    {
	    	StudentDetails tmp;
            int leftChildIndex, rightChildIndex, minIndex;
            leftChildIndex = getLeftChildIndex(nodeIndex);
            rightChildIndex = getRightChildIndex(nodeIndex);
            
            //when left and right child index is greater than heap size, exit from method
            if (rightChildIndex >= size) 
            {
                  if (leftChildIndex >= size)
                        return;
                  
                  //when right child index is greater but left child index is less than size
                  else
                        minIndex = leftChildIndex;
            } else 
            {
            	//update minIndex as the value of lesser better left and right child index
                  if ((data[leftChildIndex]).compareTo(data[rightChildIndex]) <= 0)
                        minIndex = leftChildIndex;
                  else
                        minIndex = rightChildIndex;
            }
            //compare values of current node index with element at minIndex, swap the values if current is greater  
            if ((data[nodeIndex]).compareTo(data[minIndex]) > 0) 
            {
                  tmp = data[minIndex];
                  data[minIndex] = data[nodeIndex];
                  data[nodeIndex] = tmp;
                  //verify heap property
                  heapifyRemove(minIndex);
            }
	    }
	    
	    //get the first node of heap
	    public StudentDetails peek()
	    {
	    	return data[0];
	    }
}
