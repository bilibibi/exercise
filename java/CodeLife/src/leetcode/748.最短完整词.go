/*
 * @lc app=leetcode.cn id=748 lang=golang
 *
 * [748] 最短完整词
 */

// @lc code=start
func shortestCompletingWord(licensePlate string, words []string) string {
    var out string
    var shortWordLen,wordLen int
    for _,val := range words {
        
        /***重复制作hash表***/
        hash := make(map[string]int)
        for _,v := range licensePlate{
            if _,has := hash[string(v)]; !has && v >= 'a' && v<= 'z' {
                hash[string(v)] = 1
            } else if _,has := hash[string(v)]; has && v >= 'a' && v<= 'z' {
                hash[string(v)] ++
            } else if _,has := hash[string(v+32)]; !has && v >= 'A' && v<= 'Z' {
                hash[string(v+32)] = 1
            } else if _,has := hash[string(v+32)]; has && v >= 'A' && v<= 'Z' {
                hash[string(v+32)] ++
            }
        }
        
        /**开始确定最短完整单词**/
        wordFlag := 0
        for _,word := range val {
            if _,ok := hash[string(word)]; ok && hash[string(word)] > 0 {
                wordFlag++
                hash[string(word)]--
            }
        }
        // 如果命中字符多于当前标记
        if wordFlag > shortWordLen {
            out,shortWordLen,wordLen = val,wordFlag,len(val)
        }
        // 如果命中字符一样多,并且单词长度比当前长度短
        if wordFlag == shortWordLen && wordLen > len(val) {
            out,wordLen = val,len(val)
        }
    }
    
	return out
}
// @lc code=end

