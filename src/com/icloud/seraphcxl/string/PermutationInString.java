package com.icloud.seraphcxl.string;

import java.util.Arrays;

/**
 * 567. Permutation in String
 *
 * @author xiaoliangchen
 */
public class PermutationInString {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "a"));
    }

    public static boolean checkInclusion(String s1, String s2) {

        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 > len2) {
            return false;
        }

        int[] charHash1 = new int[128];
        int[] charHash2 = new int[128];

        for (int i = 0; i < len1; ++i) {
            charHash1[s1.charAt(i)]++;
            charHash2[s2.charAt(i)]++;
        }

        if (Arrays.equals(charHash1, charHash2)) {
            return true;
        }

        for (int i = len1; i < len2; ++i) {
            charHash2[s2.charAt(i)]++;
            charHash2[s2.charAt(i - len1)]--;

            if (Arrays.equals(charHash1, charHash2)) {
                return true;
            }
        }
        return false;
    }
}
