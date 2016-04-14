
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//The main method class

public class StudentBinaryHeap {

	public static void main(String[] args) 
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//object of Binary tree implementation of heap
		HeapNode<StudentDetails> studentheap = new HeapNode<StudentDetails>();
		
		//object of array implementation of heap
		ArrayHeap arrayheap = new ArrayHeap(10);
		
		String name="";
		int p, choice=1;
		
		System.out.println("Welcome to Priority Queue of Student...");
		System.out.println("1. Insert Student Record");
		System.out.println("2. Delete Student Record");
		System.out.println("3. Print the top Student Record");
		System.out.println("4. Print All Records in Order");
		System.out.println("5. Exit");
		
		do
		{
			try
			{
			System.out.print("\nEnter your choice:");
			choice = Integer.parseInt(br.readLine());
			
			switch(choice)
			{
				case 1:
				{
					System.out.print("Enter Student Name:");
					name = br.readLine();
					System.out.print("Enter priority:");
						p = Integer.parseInt(br.readLine());
					StudentDetails stud = new StudentDetails(name,p);
					studentheap.insert(stud);
					arrayheap.insert(stud);
					System.out.println("Elements in Heap:");
					//studentheap.printInOrder();
					//System.out.println("Elements in ArrayHeap:");
					arrayheap.printAll();
					
					//System.out.println("A record is added");
						break;
				}
				
				case 2:
				{
					studentheap.remove();
					//System.out.println("Elements in StudentHeap:");
					//studentheap.printInOrder();
					arrayheap.removeMin();
					System.out.println("Elements in Heap:");
					arrayheap.printAll();
					break;
				}
				
				case 3:
				{
					System.out.println(studentheap.peek() + "\t is the top most element in StudentHeap");
					System.out.println(arrayheap.peek() + "\t is the top most element in ArrayHeap");
					break;
				}
				
				case 4:
				{
					//heap sort algorithm to print elements of heap in order
					
					System.out.println("Elements in StudentHeap:");
					
					HeapNode<StudentDetails> newheap = new HeapNode<StudentDetails>();
					newheap = studentheap;
					int value = newheap.size();
					StudentDetails[] sorted = new StudentDetails[newheap.size()];
					for(int i = 0; i < value; i++)
					{
						sorted[i] = newheap.remove();
						System.out.println("Element " + i + " is:" + sorted[i]);
					}
				
				
					System.out.println("Elements in ArrayHeap:");
					
					ArrayHeap newarray = new ArrayHeap();
					newarray = arrayheap;
					int len = newarray.size();
					
					for(int i = 0; i < len; i++)
					{
						System.out.println("Element " + i + " is:" + newarray.peek());
						newarray.removeMin();
					}
						
					break;
				}
				
				case 5:
				{
					System.out.println("GoodBye!");
					break;
				}
			}		
			}
			catch(NumberFormatException n)
			{
				System.out.println("Please enter valid number");
			}
			catch(IOException e)
			{
				System.out.println("Error in input");
			}
			catch(NullPointerException np)
			{
				System.out.println("This is null object");
			}
		} while(choice != 5);
	
	
	}
}

