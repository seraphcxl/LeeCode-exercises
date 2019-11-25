package com.icloud.seraphcxl.string;

import java.util.Stack;

/**
 * 71. Simplify Path
 * 主要是用栈
 *
 * @author xiaoliangchen
 */
public class SimplifyPath {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/home/"));
    }

    public static String simplifyPath(String path) {
        String res = "/";
        do {
            // 参数检查
            if (path == null) {
                break;
            }
            path = path.trim();
            if (path.length() == 0) {
                break;
            }

            String[] dirs = path.split("/");
            Stack<String> stack = new Stack<>();
            for (String dir : dirs) {
                if (!stack.isEmpty() && "..".equals(dir)) {
                    // 如果遇到 ".." 且栈不为空，就出栈
                    stack.pop();
                } else if (!"".equals(dir) && !".".equals(dir) && !"..".equals(dir)) {
                    // 如果 dir 不是 ""，"."，".." 就入栈
                    stack.push(dir);
                }
            }
            if (stack.isEmpty()) {
                break;
            }
            StringBuilder strBuilder = new StringBuilder();
            for (String s : stack) {
                strBuilder.append("/" + s);
            }
            res = strBuilder.toString();
        } while (false);
        return res;
    }

    public static String simplifyPath_Gen2(String path) {
        path = path.replaceAll("(/)(/*)", "/").replaceAll("^/*", "");
        String[] subStrs = path.split("/");
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < subStrs.length; ++i) {
            if (subStrs[i].equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!subStrs[i].equals(".")) {
                stack.add(subStrs[i]);
            }
        }
        while (!stack.isEmpty()) {
            sb.insert(0, '/' + stack.pop());
        }
        return sb.length() <= 1 ? "/" : sb.toString();
    }
}
