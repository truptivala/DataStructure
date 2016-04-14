
public class MatrixRotation {
	
	public static void main(String args[])
	{
		int matrix[][] = {
			    {1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		};
		
		for(int i = 0; i <= 2; i++)
		{
			for(int j = 2; j >= 0; j--)
			{
				System.out.print(matrix[j][i] + " ");
			}
			System.out.println();
		}
		
	}

}
