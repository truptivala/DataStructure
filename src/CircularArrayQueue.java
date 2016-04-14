
public class CircularArrayQueue<T> {
	
	private T[] queue;
	private int front, rear, count;
	
	@SuppressWarnings("unchecked")
	public CircularArrayQueue()
	{
		queue = (T[]) (new Object[10]);
		front = rear = count = 0;
	}
	
	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int size)
	{
		queue = (T[]) (new Object[size]);
		front = rear = count = 0;
	}
	
	public int size()
	{
		int size = rear - front;
		
		return size;
	}
	
	public boolean isEmpty()
	{
		return (count == 0 && front == rear);
	}
	
	public void expandCapacity()
	{
		@SuppressWarnings("unchecked")
		T[] newqueue = (T[]) (new Object[queue.length * 2]);
		
		for(int i = 0; i < queue.length; i++)
		{
			newqueue[i] = queue[front];
			front = (front + 1) % queue.length;
		}
		front = 0;
		rear = count;
		queue = newqueue;
		
	}
	public void enqueue(T element)
	{
		if(size() == queue.length)
			expandCapacity();
		
		queue[rear] = element;
		rear = (rear + 1) % queue.length;
		count++;
	}
	
	public T dequeue()
	{
		if(isEmpty())
			System.out.println("Queue is empty!");
		
		T result = queue[front];
		queue[front] = null;
		front = (front + 1) % queue.length;
		count--;
		
		return result;
	}
}

