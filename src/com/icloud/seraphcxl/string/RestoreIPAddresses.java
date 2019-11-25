package com.icloud.seraphcxl.string;

import java.util.*;

/**
 * 93. Restore IP Addresses
 * 递归，DFS
 *
 * @author xiaoliangchen
 */
public class RestoreIPAddresses {

    int n;
    String s;
    LinkedList<String> segments = new LinkedList<String>();
    ArrayList<String> output = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println(new RestoreIPAddresses().restoreIpAddresses_Gen2("25525511135"));
    }

    public List<String> restoreIpAddresses(String s) {
        n = s.length();
        this.s = s;
        backtrack(-1, 3);
        return output;
    }

    public boolean valid(String segment) {
        int m = segment.length();
        if (m > 3) {
            return false;
        }
        return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
    }

    public void update_output(int curr_pos) {
        String segment = s.substring(curr_pos + 1, n);
        if (valid(segment)) {
            segments.add(segment);
            output.add(String.join(".", segments));
            segments.removeLast();
        }
    }

    public void backtrack(int prev_pos, int dots) {
        int max_pos = Math.min(n - 1, prev_pos + 4);
        for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
            String segment = s.substring(prev_pos + 1, curr_pos + 1);
            if (valid(segment)) {
                segments.add(segment);
                if (dots - 1 == 0) {
                    update_output(curr_pos);
                } else {
                    backtrack(curr_pos, dots - 1);
                }
                segments.removeLast();
            }
        }
    }

    public List<String> restoreIpAddresses_Gen2(String s) {
        // 尾递归（ip段不会大于3位数）
        List<String> ret = new ArrayList<>();

        StringBuilder ip = new StringBuilder();

        for (int a = 1 ; a < 4 ; ++ a) {
            for (int b = 1 ; b < 4 ; ++ b) {
                for (int c = 1; c < 4; ++c) {
                    for (int d = 1; d < 4; ++d) {
                        if (a + b + c + d == s.length()) {
                            int n1 = Integer.parseInt(s.substring(0, a));
                            int n2 = Integer.parseInt(s.substring(a, a + b));
                            int n3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int n4 = Integer.parseInt(s.substring(a + b + c));
                            if (n1 <= 255 && n2 <= 255 && n3 <= 255 && n4 <= 255) {
                                ip.append(n1).append('.').append(n2)
                                    .append('.').append(n3).append('.').append(n4);
                                if (ip.length() == s.length() + 3) {
                                    ret.add(ip.toString());
                                }
                                // 清理
                                ip.delete(0, ip.length());
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}
