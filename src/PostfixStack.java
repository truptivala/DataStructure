public class PostfixStack implements IStack
{
	private int[] data ;
	private int position = 0;
	public int size;
	int max;
	
	public PostfixStack(int n)
	{
		this.size = 0;
		data = new int[n];
		System.out.println("Stack size "+data.length);
		max = n-1;
	}
	
	public boolean isEmpty()
	{
		if(size <= 0)
			return true;
		return false;
	}
	
	public boolean isFull()
	{
		if(size >= max)
			return true;
		return false;
	}
	
	public void push(int obj)
	{
		if(isFull())
			System.out.println("Stack is full!");
		else
		{
			data[position++] = obj;
			size++;
		}
	}
	
	public int pop()
	{
		if(isEmpty())
		{
			System.out.println("Stack is empty!");
			return 0;
		}
		else
		{
			int dt = data[position];
			position--; 
			size--;
			return dt;
		}
	}
	
	public int peek()
	{
		if(isEmpty())
			return 0;
		else
			return data[position];
	}
}