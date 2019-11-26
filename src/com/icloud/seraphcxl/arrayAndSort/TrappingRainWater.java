package com.icloud.seraphcxl.arrayAndSort;

import static java.lang.Math.*;

import java.util.*;

/**
 * 42. Trapping Rain Water
 *
 * @author xiaoliangchen
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};

        System.out.println(new TrappingRainWater().trap_Gen3(height));
    }

    /**
     * 暴力求解
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        // 最左和最右无法留住水，去掉
        for (int i = 1; i < size - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            // 找到左边最高的柱子
            for (int j = i; j >= 0; j--) { 
                maxLeft = max(maxLeft, height[j]);
            }
            // 找到右边最高的柱子
            for (int j = i; j < size; j++) { 
                maxRight = max(maxRight, height[j]);
            }
            ans += min(maxLeft, maxRight) - height[i];
        }
        return ans;
    }

    /**
     * 动态
     *
     * @param height
     * @return
     */
    public int trap_Gen2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int ans = 0;
        int size = height.length;
        int[] leftMax = new int[size];
        int[] rightMax = new int[size];

        leftMax[0] = height[0];
        for (int i = 1; i < size; i++) {
            leftMax[i] = max(height[i], leftMax[i - 1]);
        }

        rightMax[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            rightMax[i] = max(height[i], rightMax[i + 1]);
        }

        for (int i = 1; i < size - 1; i++) {
            ans += min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans; 
    }

    public int trap_Gen3(int[] height) {
        int ans = 0;
        int current = 0;
        Stack<Integer> st = new Stack<>();;
        while (current < height.length) {
            while (!st.empty() && height[current] > height[st.peek()]) {
                int top = st.pop();
                if (st.empty()) {
                    break;
                }
                int distance = current - st.peek() - 1;
                int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            st.push(current++);
        }
        return ans;
    }
}
