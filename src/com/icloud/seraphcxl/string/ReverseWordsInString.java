package com.icloud.seraphcxl.string;

/**
 * 151. Reverse Words in a String
 *
 * @author xiaoliangchen
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
//        System.out.println(reverseWords("the sky is blue"));
//        System.out.println(reverseWords("  hello world!  "));
        System.out.println(reverseWords(" a good   example "));
    }

    public static String reverseWords(String s) {
        String res = "";
        do {
            if (s == null) {
                break;
            }
            s = s.trim();
            if (s.length() == 0) {
                break;
            }
            String[] subStrArray = s.split(" ");
            StringBuilder strBuilder = new StringBuilder();
            for (int i = subStrArray.length - 1; i >= 0; --i) {
                String subStr = subStrArray[i].trim();
                if (subStr.length() == 0) {
                    continue;
                }
                strBuilder.append(subStr).append(" ");
            }
            res = strBuilder.toString().trim();
        } while (false);
        return res;
    }

    public static String reverseWords_Gen2(String s) {
        String res = "";
        do {
            if (s == null || s.trim().equals("")) {
                break;
            }
            s = s.trim();
            int strLen = s.length();
            String reverseStr = new StringBuilder(s).reverse().toString();
            int startLoc = 0;
            for (int i = 0; i < strLen; ++i) {
                if (reverseStr.charAt(i) == ' ') {
                    if (i == startLoc) {
                        startLoc = (i + 1);
                    } else {
                        if (startLoc > 0) {
                            res += " ";
                        }
                        res += new StringBuilder(reverseStr.substring(startLoc, i)).reverse().toString();
                        startLoc = (i + 1);
                    }
                }
            }

            if (startLoc != strLen) {
                if (startLoc > 0) {
                    res += " ";
                }
                res += new StringBuilder(reverseStr.substring(startLoc, strLen)).reverse().toString();
            }
        } while (false);
        return res;
    }
}
