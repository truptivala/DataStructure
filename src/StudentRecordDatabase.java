/*
 
/////////////////////////////////////////////////////////////////////
// Assignment 1: Student Record Database                           //
// ver 1.0                                                         //
//                                                                 //
// Author:      Trupti Kholiya  (84829)                            //
//              MS (Software Engineering), ITU           		   //
// Instructor:  Prof. Barbara Hecker							   //
/////////////////////////////////////////////////////////////////////
 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


//Student class - to hold student details

class Students 
{
	private String fname;
	private String lname;
	private String major;
	private float grade;
	
	//Link to next object
	Students next;
	
	//Constructor of Student class
	public Students(String fname, String lname, String major, float grade) {
		this.fname = fname;
		this.lname = lname;
		this.major = major;
		this.grade = grade;
	}

	//Method to display contents of Student object
	public void Display() {
		System.out.println(fname + "\t" + lname + "\t" + major + "\t" + grade);
	}

	//Overridden toString() to print whole object
	public String toString() {
		return fname + "\t" + lname + "\t" + major + "\t" + grade + "\n";
	}
	
	//Getters of each value of Student class
	public String getFirstName()
	{
		return fname;
	}
	public String getLastName()
	{
		return lname;
	}
	public String getMajor()
	{
		return major;
	}
	public float getGrade()
	{
		return grade;
	}
}


//StudentLink - A Linked-list class

class StudentLink 
{
	//Head of linked list
	
	public Students head;

	//Constructor
	public StudentLink() {
		head = null;
	}

	//isEmpty() returns true is list is empty, false otherwise
	public boolean isEmpty() {
		return head == null;
	}

	//AddRecord() - to add records in front of the list (without position)
	public void AddRecord(String f, String l, String m, String gd) 
	{
		float g = Float.parseFloat(gd);
		
		//creates new Student instance
		Students s = new Students(f, l, m, g);
		
		//change head to next position
		s.next = head;
		head = s;
	}
	
	////AddRecord() - overloaded - to add records in the list (with position)
	public void AddRecord(String f, String l, String m, String gd, String p) 
	{
		float g = Float.parseFloat(gd);
		
		//creates new Student instance
		Students s = new Students(f, l, m, g);
		
		Students current = head;
		Students prev = head;
		int count = 0;
		int pos = Integer.parseInt(p);
		
		//If position <= 0 or list is empty, a record is added in the front.
		if(pos <= 0 || head == null)
		{
			AddRecord(f,l, m, gd);
			return;
		}
		else
		{
			//While loop iterates till position is reached
			while(pos != count)
			{
				//To check end of list?
				if(current.next != null)
				{
					prev = current;
					current = current.next;
					count++;
				}
				else
				{
					System.out.println("A record is added at last.");
					current.next = s;
					s.next = null;
					return;
				}
			}
			
			prev.next = s;
			s.next = current;
			System.out.println("A record has been added");
		}
	}
	
	//DeleteRecord() - to delete record from position given
	public void DeleteRecord(String position) 
	{
		Students current = head;
		Students prev = head;
		int pos = Integer.parseInt(position);
		int count = 1;
		
		//When position is less than or equal to 0 or position is 1. 
		if(pos <= 0 || pos == 1)
		{
			head = current.next;
			current = null;
			return;
		}
		
		while(current != null)
		{
			//If matching element is found, record is deleted
			if(pos == count)
			{
				prev.next = current.next;
				current = null;
				return;
			}
			
			//Exception: if list has only one record and when position is greater than size of list i.e. greater than 1, the first and only record is deleted
			if(current.next == null && current == prev)
			{
				head = null;
				return;
			}
			
			prev = current;
			current = current.next;
			count++;
			
			if(current == null)
			{
				prev.next = null;
				return;
			}
		}
	}

	//display() - to display all records
	public void display() 
	{
		Students current = head;

		while (current != null) {
			current.Display();
			//System.out.println("Next Link : " + current.next);
			current = current.next;
		}
	}
	
	//display() - to display records in given range
	public void display(String s, String e) 
	{
		
		Students current = head;
		int i = 1;
		int start = Integer.parseInt(s);
		int end = Integer.parseInt(e);
		int range = end-start;
		if(range >= 0 &&  end <= size())
		{
			while (i <= end) 
			{
					if(i < start)
					{
						current = current.next;
					}
					else
					{
						current.Display();
						//System.out.println("Next Link : " + current.next);
						current = current.next;
					}
					i++;
			}
		}
		else
			System.out.println("Range is above the size of the List. Please enter again");
		
	}

	//Method to search records by last name
	public void SearchRecord(String lname) 
	{
		int count = 0;
		Students current = head;
		while(current != null)
		{
			//if last name of record matches with input
			if(current.getLastName().equalsIgnoreCase(lname))
			{
				current.Display();
				count++;
			}
			current = current.next;
		}
		if(count == 0)
			System.out.println("No matching records found");
	}

	//calculates size of the list - number of elements
	public int size() {
		int size = 0;
		Students current = head;
		while (current != null) {
			current = current.next;
			size++;
		}
		return size;
	}

	//Method to calculate average of course code
	public void average(String code) {
		float temp = 0.0f;
		Students s = head;
		float grade = 0.0f;
		while(s != null)
		{
			if(s.getMajor().equalsIgnoreCase(code)) 
			{
				temp++;
				grade += s.getGrade();
			}
			s = s.next;
		}
		if(temp == 0.0f)
			System.out.println("No matching course found");
		else
			System.out.println("Average :" + (grade/temp));		
	}
	
}


//Main method class 

class StudentRecordDatabase {
	public static void main(String args[]) {
		try {
			
			//taking input from keyboard
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			//A new StudentLink object
			StudentLink s = new StudentLink();
			
			//Some pre-stored records
			s.AddRecord("Trupti", "Vala", "SE", "3.9");
			s.AddRecord("Manisha", "Vala", "SE", "3.0");
			s.AddRecord("Akanksha", "Avinash", "CS", "3.1");
			s.AddRecord("Pooja", "Vijay", "SE", "3.2");
			s.AddRecord("Smith", "Mathew", "CE", "3.3");
			
			//Display menu
			System.out.println("Welcome to Student Record Database..");
			System.out.println("Press 1 to Insert New Record");
			System.out.println("Press 2 to Delete the Record");
			System.out.println("Press 3 to Search the Database by Last Name");
			System.out.println("Press 4 to Print a Range in the Database");
			System.out.println("Press 5 to Print Class Average of a Course");
			System.out.println("Press 9 to Quit");
			int choice = 0;
			
			//do-while loop iterates until 9 is pressed
			do
			{
			System.out.print("\nEnter your choice here: ");
			String c = br.readLine();
			if(c.equals(""))
			{
				System.out.println("Invalid choice. Try again");
			}
			else
			{
				choice = Integer.parseInt(c);
				
				String f, l, m, p, gd;
				
				if(s.isEmpty())
				{
					System.out.println("List is empty. Add records to the list");
					choice = 1;
				}
				
				//switch-case for given choice
				switch (choice) {
				case 1: {
					System.out.println("Enter First Name: ");
					f = br.readLine();
					System.out.println("Enter Last Name: ");
					l = br.readLine();
					System.out.println("Enter Course Code: ");
					m = br.readLine();
					System.out.println("Enter Grade: ");
					gd = br.readLine();
					System.out.println("Enter position to Add Record: ");
					p = br.readLine();
					if(f.equals("") || l.equals("") || m.equals("") || gd.equals(""))
						System.out.println("A field cannot left blank. Please try again");
					
					else if(p.equals("") || p.isEmpty())
						s.AddRecord(f, l, m, gd);
					
					else
						s.AddRecord(f, l, m, gd, p);
					s.display();
					break;
				}

				case 2: {
					System.out.println("Enter position to Delete Record : ");
					l = br.readLine();
					if(l.equals(""))
						System.out.println("Invalid input position. Please enter again");
					else
						s.DeleteRecord(l);
					s.display();
					break;
				}

				case 3: {
					System.out.println("Search Record by Last Name : ");
					l = br.readLine();
					if(l.equalsIgnoreCase(""))
						System.out.println("Last name is null. Please try again");
					else
						s.SearchRecord(l);
					break;
				}
				case 4: {
					System.out.println("Enter Start: ");
					String start = br.readLine();
					System.out.println("Enter End: ");
					String end = br.readLine();
					if(start.equals("") && end.equals(""))
						s.display();
					else
						s.display(start,end);
					break;

				}
				case 5: {
					System.out.println("Enter course code: ");
					m = br.readLine();
					s.average(m);
					break;
				}
				case 9: {
					System.out.println("Good Bye!");
					return;
				}
				default: {
					System.out.println("Please enter valid choice");
					break;
				}
				}
			}
			
			}while(choice != 9);
			
		} catch (Exception e) {
			System.out.println("Error in Input");
		}
	}
}