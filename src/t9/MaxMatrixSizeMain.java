package t9;

import java.util.Stack;

/**
 * 题目：求最大矩阵的大小--单调栈的应用
 * 核心：①切割获取连续区域的数组②(核心)利用单调栈计算面积
 * @author VincentJ
 * @date 2019-05-13
 */
public class MaxMatrixSizeMain {
    public static void main(String[] args) {
        int[][] testData = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        System.out.println(maxRecSize(testData));
    }

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }

        int maxArea = 0;
        int[] height = new int[map[0].length];

        for (int i = 0; i < map.length; i++) {
            // 切割每一行
            for (int j = 0; j < map.length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            // 计算面积
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }

        return maxArea;
    }

    // 根据每一行生成的计算最大面积，例如{3,4,5,6}--核心就是利用单调栈快速计算
    private static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        // 正常弹栈过程
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }

        // 清理阶段
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }

        return maxArea;
    }
}
