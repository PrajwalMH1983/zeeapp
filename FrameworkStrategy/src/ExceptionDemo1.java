import java.util.Scanner;

public class ExceptionDemo1 {
	public static int div(int a , int b) {
		int c = 0;
		//If you do not handle the unchecked exception it'll be handled by JVM
		//Inside the try or catch block then before returning the 
		//value it will execute the finally block
		//and then it will return the value.
		try {
			//Your try block will handle the first exception which is going to occur
			
			c = a / b;
			String s = null;	//It is not referring any object
			System.out.println(s.length());	//s.length() ==> NullPointerException ,, This is handled by JVM
			return c;
		} catch (ArithmeticException e) {
			// TODO: handle exception
			System.out.println("Enter the value of b again");
			Scanner scanner = new Scanner(System.in);
			
			int d = scanner.nextInt();
			
			c = a / d;
			return c;
			//e.printStackTrace();
		}
		catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("NPE caught");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Any exception can be handled by this handler.");
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
