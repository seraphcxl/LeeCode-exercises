package com.icloud.seraphcxl.string;

/**
 * 14. Longest Common Prefix
 *
 * @author xiaoliangchen
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        String res = "";
        if (strs.length == 0) {
            return res;
        }

        for (int i = 0; i < strs[0].length(); ++i) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; ++j) {
                if (i >= strs[j].length() || c != strs[j].charAt(i)) {
                    return res;
                }
            }
            res += c;
        }
        return res;
    }
}
