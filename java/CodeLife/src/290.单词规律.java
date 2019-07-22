import java.util.*;

/*
 * @lc app=leetcode.cn id=290 lang=java
 *
 * [290] 单词规律
 */
class Solution {
    public boolean wordPattern(String pattern, String str) {
        char[] chars = pattern.toCharArray();
        String[] strs = str.split(" ");
        if (chars.length != strs.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        // 保存str不重复的value
        Set<String> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            String s = map.get(chars[i]);
            if (s == null) {
                if (set.contains(strs[i])) {
                    // pattern未重复，str已重复
                    return false;
                }
                map.put(chars[i], strs[i]);
                set.add(strs[i]);
            } else if (!s.equals(strs[i])) {
                return false;
            }
        }
        return true;
    }
}
