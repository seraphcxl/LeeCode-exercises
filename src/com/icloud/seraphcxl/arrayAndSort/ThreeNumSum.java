package com.icloud.seraphcxl.arrayAndSort;

import java.util.*;

/**
 * 15. 3Sum
 *
 * @author xiaoliangchen
 */
public class ThreeNumSum {

    public static void main(String[] args) {
        System.out.println(new ThreeNumSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();;
        do {
            if (nums == null || nums.length < 3) {
                break;
            }
            // 数组ASC排序
            Arrays.sort(nums);
            // 第一次循环，定位第一个数
            for (int i = 0; i < nums.length - 2; ++i) {
                if (nums[i] > 0) {
                    break;
                }
                // 如果在第一个数的位置有重复，需要跳过，不然结果会重复
                if (i > 0 && nums[i - 1] == nums[i]) {
                    continue;
                }

                int left = i + 1;
                int right = nums.length - 1;
                // 两个游标开始走起来
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 左游标开始向后走，排重
                        while (left < right && nums[left] == nums[left + 1]) {
                            ++left;
                        }
                        // 右游标开始向前走，排重
                        while (left < right && nums[right - 1] == nums[right]) {
                            --right;
                        }
                        ++left;
                        --right;
                    } else if (sum < 0) {
                        ++left;
                    } else if (sum > 0) {
                        --right;
                    }
                }
            }
        } while (false);
        return res;
    }
}
