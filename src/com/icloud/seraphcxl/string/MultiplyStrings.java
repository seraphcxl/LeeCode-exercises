package com.icloud.seraphcxl.string;

/**
 * 43. Multiply Strings
 *
 * @author xiaoliangchen
 */
public class MultiplyStrings {
    public static void main(String[] args) {
//        System.out.println(multiply("2", "3"));
        System.out.println(multiply("123", "456"));
    }

    public static String multiply(String num1, String num2) {
        // 模拟纸上笔算乘法
        String res = "";
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // 参数长度小于110，结果长度不会大于250
        int strArrayLen = 120;
        int resArrayLen = 250;
        int[] x = new int[strArrayLen];
        int[] y = new int[strArrayLen];
        int[] z = new int[resArrayLen];

        int len1 = num1.length();
        int len2 = num2.length();

        // 倒叙num1
        for (int i = len1 - 1, k = 0; i >= 0; i--) {
            // 从字符数据变成int数据
            x[k++] = num1.charAt(i) - '0';
        }

        // 倒叙num2
        for (int i = len2 - 1, k = 0; i >= 0; i--) {
            y[k++] = num2.charAt(i) - '0';
        }

        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                z[i + j] += (x[i] * y[j]);
            }
        }

        for (int i = 0; i < resArrayLen; ++i) {
            if (z[i] > 9) {
                z[i + 1] += z[i] / 10;
                z[i] %= 10;
            }
        }

        // 找到结果的开始位
        int firstZeroLoc = -1;
        int num0 = (int)'0';
        for (int i = resArrayLen - 1; i >= 0; --i) {
            if (z[i] > 0 && firstZeroLoc == -1) {
                firstZeroLoc = i;
            }
            if (firstZeroLoc >= 0) {
                // 从int数据转换回字符数据
                res += (char)(z[i] + num0);
            }
        }

        return res;
    }
}
