import java.util.*;

class Solution {
    // 9.回文数
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int num = 0;
        while (x > num) {
            num = num * 10 + (x % 10);
            x /= 10;
        }
        if (x == num || x == num / 10) {
            return true;
        }
        return false;
    }

    // 13.罗马数字转整数
    public int romanToInt(String s) {
        Map map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        char[] c = new char[s.length()];
        c = s.toCharArray();

        int res = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            int val = (int) map.get(String.valueOf(c[i]));
            if (i == c.length - 1 || (int) map.get(String.valueOf(c[i + 1])) <= val) {
                res += val;
            } else {
                res -= val;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.romanToInt("III"));
    }
}