class FinallyTest {
	public static int getNumber() {
		int a = 0;
		try {
			String s = "t";
			a = Integer.parseInt(s);
			return a;
		} catch (NumberFormatException e) {
			a = 1;
			System.out.println(a);
			return a;
		} finally {
			a = 2;
			System.out.println(a);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getNumber());
	}
}