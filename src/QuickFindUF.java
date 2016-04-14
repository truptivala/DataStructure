
class QuickFind {
	
	private int[] id ;
	
	public QuickFind(int n)
	{
		id = new int[n];
		for(int i = 0; i < id.length; i++)
		{
			id[i] = i;
		}
	}

	public boolean connected(int p, int q)
	{
		return (id[p] == id[q]);
	}
	
	public void union(int p, int q)
	{
		int pid = id[p];
		int qid = id[q];
		for(int i = 0; i < id.length; i++)
		{
			if(id[i] == pid) 
				id[i] = qid;
		}
	}
	
	public int findmax(int i)
	{
		int iid = id[i];
		int max = 0;
		for(int j = 0; j < id.length; j++)
		{
			if(id[j] == iid && j > max)
				max = j;
		}
		return max;
	}
}

class QuickFindUF
{
	public static void main(String args[])
	{
		QuickFind quick = new QuickFind(10);
		quick.union(4, 3);
		quick.union(3, 8);
		//quick.union(6, 5);
		//quick.union(8, 9);
		if(quick.connected(4, 9)) 
			System.out.println("4 and 9 are connected");
		else
			System.out.println("4 and 9 are not connected");
		System.out.println("Max value is : " + quick.findmax(3));
	}
}