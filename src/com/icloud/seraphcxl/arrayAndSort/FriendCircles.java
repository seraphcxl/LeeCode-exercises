package com.icloud.seraphcxl.arrayAndSort;

import java.util.*;

/**
 * 547. Friend Circles
 * 图的联通问题
 *
 * @author xiaoliangchen
 */
public class FriendCircles {

    private int friendCount;
    private List<Integer> friendCircles = new ArrayList<Integer>();

    public static void main(String[] args) {
        int[][] grid = {
            {1,1,0,0,0,0},
            {1,1,0,0,0,0},
            {0,0,1,1,1,0},
            {0,0,1,1,0,0},
            {0,0,1,0,1,0},
            {0,0,0,0,0,1}
        };
        System.out.println(new FriendCircles().findCircleNum_Gen3(grid));
    }

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

    /**
     * 深度优先 DFS
     * 矩阵看成树的邻接矩阵，从点0开始，如果和那个点有边就跳转到那个点，直到没有跳出
     * 同时放置一个已访问点点标记
     *
     * @param M 树的邻接矩阵
     * @return
     */
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    /**
     * 广度优先 BFS
     * 1. 将一个节点入栈
     * 2. 出栈一个节点，然后遍历关联节点，都入栈
     * 3. 从栈里出栈一个节点，重复第二部（要有 Visited 标记）
     *
     * @param M 树的邻接矩阵
     * @return
     */
    public int findCircleNum_Gen2(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue < Integer > queue = new LinkedList < > ();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.remove();
                    visited[s] = 1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && visited[j] == 0) {
                            queue.add(j);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    /**
     * 递归查找 i 节点的根节点是谁
     *
     * @param parent 保存连接关系的数组
     * @param i i节点
     * @return i节点的根节点ID，如果没有，就是返回自己的ID
     */
    int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return find(parent, parent[i]);
    }

    /**
     * 查找 i 节点的根节点是谁，带 路径压缩 能力
     *
     * @param parent 保存连接关系的数组
     * @param i i节点
     * @return i节点的根节点ID，如果没有，就是返回自己的ID
     */
    int find_Gen2(int[] parent, int i) {
        int r = i;
        while (parent[r] != r) {
            r = parent[r];
        }

        // 路径压缩
        int x = i;
        int y = 0;
        while (x != r) {
            // 在改变上级之前用临时变量 y 记录下他的值
            y = parent[x];
            // 把上级改为根节点
            parent[x]= r;
            x = y;
        }

        return r;
    }

    /**
     * 用来将两个节点连接起来
     *
     * @param parent 保存连接关系的数组
     * @param x 子节点ID
     * @param y 父节点ID
     */
    void union(int[] parent, int x, int y) {
        int xSet = find_Gen2(parent, x);
        int ySet = find_Gen2(parent, y);
        if (xSet != ySet) {
            parent[xSet] = ySet;
        }
    }

    public int findCircleNum_Gen3(int[][] M) {
        // 构建 并查集
        int[] parent = new int[M.length];
//        Arrays.fill(parent, -1);
        for (int i = 0; i < M.length; ++i) {
            parent[i] = i;
        }

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) {
                    // 如果 邻接矩阵 里有边，且不是自关联环，就连接两个节点
                    union(parent, i, j);
                }
            }
        }
        // 计算有几个根节点
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }
}
