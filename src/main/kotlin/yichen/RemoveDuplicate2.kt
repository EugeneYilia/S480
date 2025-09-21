package yichen

//| 中文版                                                                                                                                         | 国际版                                                                                                                                                        |
//| ------------------------------------------------------------------------------------------------------------------------------------          | ---------------------------------------------------------------------------------------------------------------------------------------------------------- |
//| [去除重复字母（Remove Duplicate Letters）](https://leetcode.cn/problems/remove-duplicate-letters/)                                               | [LeetCode 国际版 Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/) ([leetcode.com][1])                                       |
//| [不同字符的最小子序列（Smallest Subsequence of Distinct Characters）](https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters/)  | [LeetCode 国际版 Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) ([leetcode.com][2]) |
//
// 1ms
// Runtime complexity outperforms 100% of users
fun removeDuplicateLetters2(s: String): String {
    val result = CharArray(s.length)
    var charEndIndex = -1
    val lastCharIndexMap = IntArray(26)
    val charExistMap = BooleanArray(26)

    for ((index, char) in s.withIndex()) {
        lastCharIndexMap[char - 'a'] = index
    }

    for ((index, char) in s.withIndex()) {
        if (charExistMap[char - 'a']) {
            continue
        }

        while (charEndIndex >= 0 && result[charEndIndex] > char && lastCharIndexMap[result[charEndIndex] - 'a'] > index) {
            charExistMap[result[charEndIndex] - 'a'] = false
            charEndIndex--
        }

        result[++charEndIndex] = char
        charExistMap[char - 'a'] = true
    }

    val sb = StringBuilder()

    for (index in 0..charEndIndex) {
        sb.append(result[index])
    }

    return sb.toString()
}