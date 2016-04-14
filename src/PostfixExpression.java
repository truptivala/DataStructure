import java.io.*;
import java.util.*;

public class PostfixExpression {
	public static void main(String args[]) throws IOException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("Enter postfix expression: ");
			String expression = br.readLine();
			String[] exp = expression.split(" ");
			int charcount=0;

			Stack<Integer> stack = new Stack<Integer>();

			for (int i = 0; i < exp.length; i++) 
			{
				if (exp[i].matches("[0-9-]*") == true) 
				{
					int num = Integer.parseInt(exp[i]);
					stack.push(num);
				} 
				else 
				{
					if (stack.size() >= 2) 
					{
						int a = (int) stack.pop();
						int b = (int) stack.pop();
						char c = exp[i].charAt(0);
						charcount++;

						if (c == '+')
							stack.push(b + a);
						else if (c == '-')
							stack.push(b - a);
						else if (c == '*')
							stack.push(a * b);
						else if (c == '/')
							stack.push(b / a);
						else if (c == '%')
							stack.push(a % b);
					} 
					else
					{
						System.out.println("Invalid input");
						break;
					}
				}
			}
			if(charcount>0)
				System.out.println("Answer: " + stack.peek());
			else
				System.out.println("Invalid postfix expression");
			
		} catch (ArithmeticException ae) {
			System.out.println("Error in calculation");
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid number");
		} catch (EmptyStackException es) {
			System.out.println("Stack is empty!");
		} catch (IllegalArgumentException iae) {
			System.out.println("Invalid Argument");
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}
}
