package sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

//We can stop utilizing Employee class as the base class by making the class final
//That is we can stop inheritance
public class Employee {
	
	private String empId;
	private String empName;
	private String address;
	private float salary;
	
	//To declare the constants
	//Like celsius to farhenite we have 9 / 5 which will never change for that we can use these
	public static final int a = 100;
	
	
	//To cancel overriding we can use final keyword
	public float calculateSalary() {
		//To calculate salary for an employee
		
		return salary + salary * 0.1f + salary * 0.15f + salary * 0.2f;
	}
}
