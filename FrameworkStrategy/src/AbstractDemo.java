
//They dont have real existence
//We cant create an object of abstract class
//Its is an incomplete 
//It may have abstract methods --> the method who doesn't have a method body.

public abstract class AbstractDemo implements Interface1 , Interface2 , Interface3{
	@Override
	public void method1() {
		// TODO Auto-generated method stub
		System.out.println("Method1");
	}
	
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("Method1");
	}
	
	public static void main(String[] args) {
		Interface1 interface1 = new Demo();
		Interface3 interface3 = new Demo();
		
		interface1.method1();
		interface3.method1();
	}
}

//Uncommon classes should be overridden by the concrete / Normal classes (Demo)
//Common required Implementation
	//We can write it inside the abstract class
	//Uncommon one ==> Don't override them , cuz that implementation will 
	//be done by the respective child class or classes.
class Demo extends AbstractDemo{

	//Even though method2 is in AbstractDemo as well but it will 
	//be called from the Demo class itself 
	@Override
	public void method2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method4() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method5() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method6() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method7() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method8() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method9() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method10() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	//public void method11() {
		// TODO Auto-generated method stub
		
	//}

	@Override
	public void method12() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method13() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method14() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method15() {
		// TODO Auto-generated method stub
		
	}
	
}
