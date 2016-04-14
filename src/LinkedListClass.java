class Nodes
{
	public int data;
	public Nodes next;
	
	public Nodes(int d)
	{
		data = d;
	}
	
	public String toString()
	{
		return "Value of this node: "+ data;
	}
}

class LinkedList
{
	public Nodes head, tail;
	public LinkedList()
	{
		head = tail = null;
	}
	
	public void displayList()
	{
		Nodes current = head;
		while(current != null)
		{
			System.out.println(current.data);
			current = current.next;
		}
	}
	
	public boolean insertNodeAfter(int d)
	{
		Nodes addNode = new Nodes(d);
		Nodes current = head;
		if(current == null)
		{
			head = tail = addNode;
			return true;
		}
		
		while(current != null)
		{
			if(current.data == d)
			{
				addNode.next = current.next;
				current.next = (Nodes) addNode;
				return true;
			}
			current = current.next;
		}
		current = tail = addNode;
		return true;
	}
	
	public boolean deleteNode(int d)
	{
		Nodes current = head;
		Nodes prev = head;
		
		if(current == null)
			return false;
		
		while(current != null)
		{
			if(current.data == d)
			{
				//for first and only node
				if(current == prev && current.next == null)
					current = head = tail = null;
				
				//for first node, update head
				else if(current == prev && current.next != null)
				{
					head = current.next;
					current = null;
				}
				
				//for last node, update tail
				else if(current.next == null && prev != null)
				{
					current = null;
					tail = prev;
				}
				
				//middle node
				else
				{
					prev.next = current.next;
					current = null;
				}
				
				return true;
			}
			
			prev = current;
			current = current.next;
		}
		return false;
	}
}
public class LinkedListClass {
	
	

	public static void main(String[] args) {
		
		LinkedList list = new LinkedList();
		list.insertNodeAfter(1);
		list.insertNodeAfter(2);
		list.insertNodeAfter(3);
		list.insertNodeAfter(4);
		list.displayList();
		list.deleteNode(3);
		list.displayList();
		
		

	}

}
