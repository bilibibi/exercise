/*
 * @lc app=leetcode.cn id=507 lang=java
 *
 * [507] 完美数
 */
class Solution {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        double sqrt = Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                sum += i;
                // System.out.println("sum+=i ==> " + sum);
                if (i != sqrt) {
                    sum += num / i;
                    // System.out.println("sum+=num/i ==> " + sum);
                }
            }
        }
        return sum == num;
    }

    // public static void main(String[] args) {
    //     SSolution s = new SSolution();
    //     s.checkPerfectNumber(28);
    // }
}
