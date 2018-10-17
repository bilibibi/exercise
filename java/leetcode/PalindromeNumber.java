// 9.回文数
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        PalindromeNumber p = new PalindromeNumber();
        System.out.println(p.isPalindrome(100));
        
    }
}