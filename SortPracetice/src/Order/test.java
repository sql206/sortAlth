package Order;

/**
 * @author admin
 *
 */
public class test {

	int age=9;
	public test() {
		
	}
	void changeAge(int newa) {
		//setAge(newa);
		age=newa;
	}
	
	void changeObj() {
		//setAge(newa);
		this.age=10;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age1) {
		age = age1;
	}

}
