//Student Details class, stores student name, priority and order

public class StudentDetails implements Comparable<StudentDetails>
{ 
	private String name;
	private int priority;
	private int order;
	public static int nextOrder=0;
	
	public StudentDetails()
	{
		
	}
	public StudentDetails(String n, int p)
	{
		name = n;
		priority = p;
		order = nextOrder;
		nextOrder++;
	}
	
	//overrides toString method to print the object
	
	public String toString()
	{
		return name + "\t" + priority;
	}
	
	//overrides compareTo() to compare objects by priority and order values
	
	public int compareTo(StudentDetails obj)
	{
		int result;
		StudentDetails temp = obj;
		if(this.priority > temp.priority)
			result = 1;
		else if(this.priority < temp.priority)
			result = -1;
		else if(this.priority == temp.priority)
			result = 0;
		else if(this.order > temp.order)
			result = 1;
		else
			result = -1;
		
		return result;
	}
}
