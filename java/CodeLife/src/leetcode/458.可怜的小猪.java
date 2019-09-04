/*
 * @lc app=leetcode.cn id=458 lang=java
 *
 * [458] 可怜的小猪
 */
class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        int base = minutesToTest / minutesToDie + 1;
        while (Math.pow(base, pigs) < buckets) {
            // 每多一只猪，多一统计维度
            pigs++;
        }
        return pigs;
    }
}
