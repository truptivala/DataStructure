
class MyArrayStack<T> {
	
	private T[] data;
	private int top;
	private final int DEFAULT_CAPACITY = 10;
	
	@SuppressWarnings("unchecked")
	public MyArrayStack()
	{
		top = 0;
		data = (T[]) (new Object[DEFAULT_CAPACITY]);
	}
	
	@SuppressWarnings("unchecked")
	public MyArrayStack(int size)
	{
		top = 0;
		data = (T[]) (new Object[size]);
	}
	public boolean isEmpty()
	{
		return (top == 0);
	}
	
	public boolean isFull()
	{
		return (top == DEFAULT_CAPACITY);
	}
	
	public void push(T element)
	{
		if(size() == data.length)
			expandCapacity();
		else
		{
			data[top] = element;
			top++;
		}
	}
	
	private void expandCapacity() {
		
		@SuppressWarnings("unchecked")
		T[] larger = (T[]) (new Object[data.length*2]);
		
		for(int i = 0; i < data.length; i++)
			larger[i] = data[i];
		
		data = larger;
	}

	public T pop()
	{
		if(!isEmpty())
		{
			top--;
			T result = data[top];
			data[top] = null;
			return result;
		}
		else
			return null;
	}
	
	public T peek()
	{
		if(!isEmpty())
			return data[top-1];
		else
			return null;
	}
	
	public int size()
	{
		return top;
	}
}

public class MyStack
{
	public static void main(String args[])
	{
		MyArrayStack<String> stack = new MyArrayStack<String>(5);
		System.out.println("Is the stack empty? "+stack.isEmpty());
		System.out.println("Is the stack full? "+stack.isFull());
		System.out.println("Size of stack - "+stack.size());
		stack.push("Trupti Vala");
		System.out.println("Element pushed");
		stack.push("Ketan Vala");
		System.out.println("Element pushed");
		stack.push("ITU");
		System.out.println("Element pushed");
		stack.push("Anritsu company");
		System.out.println("Element pushed");
		stack.push("Anritsu company");
		System.out.println("Element pushed");
		stack.push("Anritsu company");
		System.out.println("Element pushed");
		stack.push("Anritsu company");
		System.out.println("Element pushed");
		System.out.println("Size of stack - "+stack.size());
		//System.out.println("Elements - " + stack.pop());
		//System.out.println("Elements - " + stack.pop());
		//System.out.println("Elements - " + stack.pop());
		//System.out.println("Elements - " + stack.pop());
		//System.out.println("Elements - " + stack.pop());
		//stack.push("Chetan Vala");
		//System.out.println("Element pushed");
		System.out.println("Is the stack empty? "+stack.isEmpty());
		System.out.println("Is the stack full? "+stack.isFull());
		System.out.println("Size of stack - "+stack.size());
	}
}