package com.icloud.seraphcxl.arrayAndSort;

/**
 * 33. Search in Rotated Sorted Array
 * 基本思路就会二分法，但是旋转数组要做一些特殊判断
 *
 * @author xiaoliangchen
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(new SearchInRotatedSortedArray().search_Gen2(nums, 0));
    }

    public int search(int[] nums, int target) {
        int res = -1;
        do {
            int lo = 0;
            int hi = nums.length - 1;

            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if ((nums[0] > target) ^ (nums[0] > nums[mid]) ^ (target > nums[mid])) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            res = lo == hi && nums[lo] == target ? lo : -1;
        } while (false);
        return res;
    }

    public int search_Gen2(int[] nums, int target) {
        int res = -1;
        do {
            if (nums == null || nums.length == 0) {
                break;
            }
            int start = 0;
            int end = nums.length - 1;
            int mid;
            while (start <= end) {
                mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    res =  mid;
                    break;
                }
                // 前半部分有序,注意此处用小于等于
                if (nums[start] <= nums[mid]) {
                    // target在前半部分
                    if (target >= nums[start] && target < nums[mid]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else {
                    if (target <= nums[end] && target > nums[mid]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
            }
        } while (false);
        return res;
    }
}
