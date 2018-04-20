class StringTest {
	public static void main(String[] args) {
		StringBuffer s1 = new StringBuffer("AAA");
		StringBuffer s2 = s1;
		s1.append("BBB");
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println("s1="+s1+" s2="+s2);
	}
}