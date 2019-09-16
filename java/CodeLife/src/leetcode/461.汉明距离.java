/*
 * @lc app=leetcode.cn id=461 lang=java
 *
 * [461] 汉明距离
 */
class Solution {
    public int hammingDistance(int x, int y) {
        int distance = 0;
        int localx = 0;
        int localy = 0;
        while (x > 0 || y > 0) {
            localx = x % 2;
            localy = y % 2;
            x = x / 2;
            y = y / 2;
            if (localx != localy) {
                distance++;
            }
        }
        return distance;
    }
}
