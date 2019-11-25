package com.icloud.seraphcxl.arrayAndSort;

import java.util.ArrayList;
import java.util.List;

/**
 * 695. Max Area of Island
 *
 * @author xiaoliangchen
 */
public class MaxAreaOfIsland {

    private int result = 0;
    private int curArea;
    private List<Integer> islandAreas = new ArrayList<Integer>();

    public static void main(String[] args) {
        int[][] grid = {
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        System.out.println(new MaxAreaOfIsland().maxAreaOfIsland(grid));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        do {
            // 遍历整个二维矩阵
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (grid[i][j] == 1) {
                        curArea = 0;
                        // 开始递归查找（DFS）
                        maxArea(i, j, grid);
                        islandAreas.add(curArea);
                        result = Math.max(curArea, result);
                    }
                }
            }
            res = result;
        } while (false);
        return res;
    }

    /**
     * 递归函数
     * 函数的结果从 curArea 中返回
     *
     * @param i 所在点纵坐标
     * @param j 所在点横坐标
     * @param grid 原始二维矩阵
     */
    public void maxArea(int i, int j, int[][] grid) {
        if (i > -1 && i < grid.length
            && j > -1 && j < grid[0].length
            && grid[i][j] == 1
        ) {
            // 把当前位置的值从 1 变成 > 1 来表记已经访问过了
            grid[i][j] = ++curArea + 1;

            // 向四周探索
            maxArea(i + 1, j, grid);
            maxArea(i - 1, j, grid);
            maxArea(i, j + 1, grid);
            maxArea(i, j - 1, grid);
        }
    }
}
