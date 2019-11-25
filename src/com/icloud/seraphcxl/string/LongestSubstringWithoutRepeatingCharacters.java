package com.icloud.seraphcxl.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 3. Longest Substring Without Repeating Characters
 *
 * @author xiaoliangchen
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(str + ": " + solution(str));

        str = "bbbbb";
        System.out.println(str + ": " + solution(str));

        str = "pwwkew";
        System.out.println(str + ": " + solution(str));
    }

    static long solution(String str) {
        // 滑动窗口长度
        int res = 0;
        // 滑动窗口起点
        int start = 0;
        // 记录字符出现的最后位置
        int[] charHash = new int[128];

        for (int i = 0; i < str.length(); ++i) {
            int charLastLoc = charHash[str.charAt(i)];
            if (charLastLoc == 0 || charLastLoc < start) {
                // 字符没有出现过 or 字符出现在窗口左侧
                res = Math.max(res, i - start + 1);
            } else  {
                start = charLastLoc;
            }
            charHash[str.charAt(i)] = i + 1;
        }
        return res;
    }
}
