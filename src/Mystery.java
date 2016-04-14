import java.util.ArrayList;


public class Mystery {

	public static void main(String args[])
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		list.add(7);
		list.add(9);
		list.add(8);
		list.add(0);
		list.add(5);
		list.add(4);
		for(int index = 0; index < list.size(); index++)
		{
			int elementValue = list.remove(index);
			if(elementValue % 2 == 0)
			{
				list.add(index);
			}
		}
		System.out.println(list);
	}
}
