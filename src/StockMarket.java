import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Stock class to hold stock details
class Stock
{
	private String name;
	private int nshare;
	private float price;
	public Stock next;
	
	//constructor
	public Stock(String n, int s, float p)
	{
		this.name = n;
		this.nshare = s;
		this.price = p;
	}
	
	//overrides toString to print current object
	public String toString()
	{
		return this.name + "\t" + this.nshare + "\t" + this.price;
	}
	
	public float getPrice()
	{
		return this.price;
	}
	public int getNumOfShare()
	{
		return this.nshare;
	}
	public String getName()
	{
		return this.name;
	}
	public void setPrice(float p)
	{
		this.price = p;
	}
	public void setNumOfShare(int n)
	{
		this.nshare = n;
	}
	public void setName(String n)
	{
		this.name = n;
	}
}


//A Stack class - used for LIFO price calculation
class StackStock
{
	private final int MAXSIZE = 10;
	private Stock[] s;
	private int top = 0;
	
	public StackStock()
	{
		s = new Stock[MAXSIZE];
		top = 0;
	}
	
	public boolean isEmpty()
	{
		return (top == 0);
	}
	
	public void expandcapacity()
	{	
		Stock newStock[] = new Stock[s.length*2];
		for(int i = 0; i < s.length * 2; i++)
			newStock[i] = s[i];
		s = newStock;
	}
	public void Push(Stock stk)
	{
		if(s.length == size())
			expandcapacity();
		
		s[top] = stk;
		top++;
	}
		
	public Stock Pop()
	{
		Stock result = null;
		if(!isEmpty())
		{
			top--;
			result = s[top];
			s[top] = null;
		}
		return result;
	}
	
	public int size()
	{
		return top;
	}
}


//A class for LinkedList node - used for FIFO Price calculation
class LinkedStock
{
	private Stock head, rear;
	int size = 0;
	
	public LinkedStock()
	{
		head = rear = null;
		size = 0;
	}
	public boolean isEmpty()
	{
		return (size == 0);
	}
	
	public void AddRecord(Stock element)
	{
		if(isEmpty())
			head = element;
		else
			rear.next = element;
		
		rear = element;
		size++;
	}
	
	public Stock DeleteRecord()
	{
		if(isEmpty())
		{
			rear = null;
			return null;
		}
		Stock s = head;
		head = head.next;
		size--;
		return s;
	}
	
	public int size()
	{
		return size;
	}
	
	public void display()
	{
		Stock current = head;

		while (current != null) {
			System.out.println(current);
			current = current.next;
		}
	}
}


// FIFOClass - calculation of FIFO price
class FIFOClass
{	
	//two linkedlist objects for queue. Queue1 fetches data and Queue2 stores it back. This is needed to store data back to original queue in same
	//order.
	LinkedStock queue1 = new LinkedStock();
	LinkedStock queue2 = new LinkedStock();
	
	
	public float avgprice = 0.0f;
	
	// Function for the calculation of FIFO price, which takes stock symbol and number of shares as parameters.
	public void getFIFOPrice(String sname, int shares)
	{
		String name;
		int share, temp = shares, totalshares = 0;
		float price, totalprice = 0.0f, avgprice = 0.0f;
		
		//loop iterates for all stocks
		do
		{
			Stock stock2 = queue1.DeleteRecord();
			name = stock2.getName();
			share = stock2.getNumOfShare();
			price = stock2.getPrice();
			
			if(name.equalsIgnoreCase(sname))
			{
				if(shares > 0)
				{
					shares = shares - share;
					
					if(shares >= 0)
						totalprice = totalprice + (price * share);
					else
					{
						totalprice = totalprice + (price * (shares * -1));
					}
				}
				avgprice += price * share;
				totalshares += share;
			}
		
			queue2.AddRecord(stock2);

		}while(!queue1.isEmpty());
		
		//If number of shares in query is more than existing shares in queue.
		if(shares > 0)
		{
			System.out.println("Stock symbol does not exist OR Number of shares exceeds total number of shares of " + sname + ". Please try again.");
		}
		else
		{
			System.out.println("Average FIFO price of " + temp + " stocks of " + sname + " per share is : " + totalprice/temp);
			System.out.println("Average FIFO price of all stocks of " + sname + " per share is : " + avgprice/totalshares);
			if((totalprice / temp) > (avgprice / totalshares))
				System.out.println("Profit by " + ((totalprice / temp) - (avgprice / totalshares)));
			else
				System.out.println("Loss by " + ((totalprice / temp) - (avgprice / totalshares)));
		}
		
		//Queue1 is restored with original data for next operation.
		while(!queue2.isEmpty())
		{
			Stock item = queue2.DeleteRecord();
			if(item.getNumOfShare() != 0)
				queue1.AddRecord(item);;
		}	
	}
}


//LIFOClass - for the calculation of LIFO price
class LIFOClass
{
	StackStock stack1 = new StackStock();
	StackStock stack2 = new StackStock();
	float avgprice = 0.0f;
	
	//method for calculation of LIFO price
	public void getLIFOPrice(String sname, int shares)
	{
		String name;
		int share, temp = shares, totalshares = 0;
		float price, totalprice = 0.0f, avgprice = 0.0f;
		
		do
		{
			Stock stock = stack1.Pop();
			name = stock.getName();
			share = stock.getNumOfShare();
			price = stock.getPrice();
			
			if(name.equalsIgnoreCase(sname))
			{
				if(shares > 0)
				{
					shares = shares - share;
					
					if(shares >= 0)
						totalprice = totalprice + (price * share);
					else
					{
						totalprice = totalprice + (price * (shares * -1));
					}
				}
				avgprice += price * share;
				totalshares += share;
			}
			
			stack2.Push(stock);
		
		}while(!stack1.isEmpty());
		
		//If number of shares in query is more than total existing shares of a stock
		if(shares > 0)
		{	
			//System.out.println("Stock symbol does not exist OR Number of shares exceeds total number of shares of " + sname + ". Please try again.");
		}
		else
		{
			System.out.println("Average LIFO price of " + temp + " stocks of " + sname + " per share is : " + totalprice/temp);
			System.out.println("Average LIFO price of all stocks of " + sname + " per share is : " + avgprice/totalshares);
			if((totalprice / temp) > (avgprice / totalshares))
				System.out.println("Profit by " + ((totalprice / temp) - (avgprice / totalshares)));
			else
				System.out.println("Loss by " + ((totalprice / temp) - (avgprice / totalshares)));
		}
		
		//Stack1 is restores back with original data for next operation
		while(!stack2.isEmpty())
		{
			Stock item = stack2.Pop();
			if(item.getNumOfShare() != 0)
				stack1.Push(item);
		}
	}
}


//Main class
public class StockMarket {
	
	public static void main(String args[])
	{
		//Objects of LIFOclass and FIFOClass are created
		LIFOClass lclass = new LIFOClass();
		FIFOClass fclass = new FIFOClass();
		
		int choice = 2, shares;
		String symbol;
		float price;
		
		try{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
			System.out.println("Welcome to StockMarket..");
			System.out.println("Press 1 to Add Stock details");
			System.out.println("Press 2 to find LIFO and FIFO price of the stock");
			System.out.println("Press 3 to Exit");
			
			do{
				
				System.out.print("Enter your choice here: ");
				choice = Integer.parseInt(br.readLine());
				
				switch(choice)
				{
				
				//Inserts new stock
				case 1:
				{
					System.out.print("Enter Stock symbol: ");
					symbol = br.readLine();
					System.out.print("Enter number of shares: ");
					shares = Integer.parseInt(br.readLine());
					System.out.print("Enter price of share: ");
					price = Float.parseFloat(br.readLine());
					
					Stock s = new Stock(symbol, shares, price);
					lclass.stack1.Push(s);
					
					Stock s2 = new Stock(symbol, shares, price);
					fclass.queue1.AddRecord(s2);
						
					System.out.println("A new stock is added\n");
					break;
				}
				
				//calculates LIFO and FIFO prices of stock
				case 2:
				{
					System.out.print("Enter Stock symbol: ");
					symbol = br.readLine();
					System.out.print("Enter number of shares: ");
					shares = Integer.parseInt(br.readLine());
					
					lclass.getLIFOPrice(symbol,shares);
					fclass.getFIFOPrice(symbol,shares);
		
					break;
				}
				
				//exits program
				case 3:
				{
					System.out.println("Good bye!");
					return;
				}
				default:
				{
					System.out.println("Invalid choice. Try again");
					break;
				}
				}
				
				
			}while (choice != 3);
		}
		catch(IOException e)
		{
			System.out.println("Error in input. Please try again.");
		}
		catch(NullPointerException np)
		{
			System.out.println("The stock list is empty. Please try again.");
		}
		catch(NumberFormatException nf)
		{
			System.out.println("Invalid number. Please try again.");
		}
		
	}

}
