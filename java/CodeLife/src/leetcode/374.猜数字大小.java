/*
 * @lc app=leetcode.cn id=374 lang=java
 *
 * [374] 猜数字大小
 */
/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        if (n == 1) {
            return 1;
        }
        int start = 1;
        int end = n;
        int mid = Math.toIntExact(((long) n + (long) 1) / 2);
        while (guess(mid) != 0) {
            if (guess(mid) == -1) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            if (start == end) {
                mid = start;
                break;
            }
            mid = Math.toIntExact(((long) start + (long) end) / 2); 
        }
        return mid;
    }
}
