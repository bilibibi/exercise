/*
 * @lc app=leetcode.cn id=383 lang=java
 *
 * [383] 赎金信
 */
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for (char c : magazine.toCharArray()) {
            arr[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (arr[c - 'a'] > 0) {
                arr[c - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }
}
