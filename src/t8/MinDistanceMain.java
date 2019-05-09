package t8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 题目：给定一个不含重复值的数组arr，找到每个i位置左边和右边离i位置最近且值比arr[i]小的位置。返回所有位置相应的信息。
 * 进阶问题就是包含可重复值。
 * 核心：暴力双循环可以解答，但是时间复杂度为O(N2),
 * @author VincentJ
 * @date 2019-05-08
 */
public class MinDistanceMain {
    private static final int NOT_EXIT = -1;

    public static void main(String[] args) {

        int[] arr = {3, 4, 1, 5, 6, 2, 7};
        int[][] result = getNearLessNoRepeat(arr);
        System.out.println("不重复的");
        System.out.println(Arrays.deepToString(result));
        System.out.println("有重复值的");
        int[] arr2 = {3, 1, 3, 4, 3, 5, 3, 2, 2};
        int[][] result2 = getNearLess(arr2);
        System.out.println(Arrays.deepToString(result2));
    }

    /**
     * 没重复值
     */
    private static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        // 弹出阶段
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? NOT_EXIT : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }

        // 清算阶段
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? NOT_EXIT : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = NOT_EXIT;
        }
        return res;
    }

    /**
     * 有重复值
     */
    private static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        // ①弹出阶段
        for (int i = 0; i < arr.length; i++) {
            // 把重复的压缩到一个List,相当于把之前的单独一个数换成一个List对待
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                // 取位于下面位置的列表中，最晚加入的那个
                int leftLessIndex = stack.isEmpty() ? NOT_EXIT : stack.peek().get(stack.peek().size() - 1);
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            // 之前的add操作
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                // 值一样则加入之前的List
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        // ②清算阶段
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            // 取位于下面位置列表中，最晚加入的那个
            int leftLessIndex = stack.isEmpty() ? NOT_EXIT : stack.peek().get(stack.peek().size() - 1);
            for (Integer popi : popIs) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = NOT_EXIT;
            }
        }

        return res;
    }
}
