package Order;
import Order.test;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		test t=new test();
		test t2=new test();
		t.changeAge(15);
				
//		t1.changeAge(12);
//		System.out.println(t.getAge());	
		System.out.println(t.age);	
		System.out.println(t2.age);	
		t2.changeObj();
		System.out.println(t2.age);	
		t.changeObj();
		System.out.println(t.age);	
	}
}
