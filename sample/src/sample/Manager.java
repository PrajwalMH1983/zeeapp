package sample;

import lombok.AllArgsConstructor; 
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
//@NoArgsConstructor
//@AllArgsConstructor

public class Manager extends Employee {
	private String projectName;
	private float projectAllowance;
	
	//We have to check some validations for each and every fields
	public Manager(String empId, String empName, String address, float salary) {
		super(empId, empName, address, salary);
		// TODO Auto-generated constructor stub
	}
	
	public Manager() {
		// TODO Auto-generated constructor stub
		this("pmh001", "Prajwal", "Bangalore", 100000.0f);
		//To call the constructor from the same class
		
	}

	public Manager(String projectName, float projectAllowance) {
		//super();
		//super("pmh001", "Prajwal", "Bangalore", 10000.0f);
		this("pmh001", "Prajwal", "Bangalore", 10000.0f);
		this.projectName = projectName;
		this.projectAllowance = projectAllowance;
	}
	
	//this / super method must be the 1st call
	//inside the constructor either you can use this method or super method
	
	//Here we are overriding the calculating salary method 
	@Override
	public float calculateSalary() {
		// TODO Auto-generated method stub
		return super.calculateSalary() + this.projectAllowance;
	}
}
