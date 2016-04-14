public class SingletonClass {

	private static SingletonClass obj;

	private SingletonClass() {
	}

	public static SingletonClass getInstance() {
		if (obj == null) {
			return new SingletonClass();
		}
		return obj;
	}

	public static void printMessage() {
		System.out.println("This is singleton class");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonClass obj = SingletonClass.getInstance();
		obj.printMessage();
	}

}
