package sample;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Employee employee = new Employee("pmh001" , "Prajwal" , "Bangalore" , 123.0f);
		
		Manager manager = new Manager("Zee" , 123.0f);
		
		System.out.println(manager);
		System.out.println(manager.calculateSalary());
		//System.out.println(employee);

	}

}
