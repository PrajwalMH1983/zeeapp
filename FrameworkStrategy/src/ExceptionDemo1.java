import java.util.Scanner;

public class ExceptionDemo1 {
	public static int div(int a , int b) {
		int c = 0;
		//Inside the try or catch block then before returning the 
		//value it will execute the finally block
		//and then it will return the value.
		try {
			c = a / b;
			return c;
		} catch (ArithmeticException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			System.out.println("Hello from Zee");
		}
		
		return c;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Enter the value for a:");
			int a = scanner.nextInt();
			
			System.out.println("Enter the value for b");
			int b = scanner.nextInt();
			
			System.out.println(a);
			System.out.println(b);
			//System.out.println(a / b);
			int result = div(a , b);
			
			//return result;
			System.out.println(result);
		} catch (ArithmeticException e) {
			// TODO Auto-generated catch block
			System.out.println("Inside the catch block");
			e.printStackTrace();
		}
		System.out.println("Hello ");
	}
}
