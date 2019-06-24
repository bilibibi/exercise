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

    // offer:二维数组中的查找
    public boolean find(int target, int[][] array) {
        boolean hasTarget = false;
        if (array != null) {
            int x = array[0].length - 1;
            int y = 0;
            while (x >= 0 && y <= array.length - 1) {
                if (array[y][x] == target) {
                    hasTarget = true;
                    break;
                } else if (array[y][x] > target) {
                    x--;
                } else {
                    y++;
                }
            }
        }
        return hasTarget;
    }

    public static void main(String[] args) {
        Solution so = new Solution();

        // romanToInt
        // System.out.println(so.romanToInt("III"));

        // find
        int[][] array = new int[][]{
                {1,2,8,9},
                {4,7,10,13}
        };
        System.out.println(so.find(7, array));
    }
}