package t7;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 题目：获取最大的窗口数组（复杂度为O(N*W)肯定不是别人想要的）
 * 核心：双端队列
 * @author VincentJ
 * @date 2019-05-08
 */
public class MaxWindowArrMain {
    public static void main(String[] args) {
        int[] testData = new int[] {4, 3, 5, 4, 3, 3, 6, 7};
        int testWidth = 3;
        int[] result = getMaxWindow(testData, testWidth);
        System.out.println(Arrays.toString(result));
    }

    private static int[] getMaxWindow(int[] arr, int w) {
        // 数据有效性检查
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // 生成双端队列LinkedList，记录索引
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 如果不为空，且队列末尾的值比当前的值小，就直接out
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);

            // 检测队列头部的索引是否过期，过期则out--简单而言就是不在当前的窗口里了
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }

            // 此时开始出现窗口，也就是说开始生成我们所需要的结果
            if (i >= w - 1) {
                // 根据队列中的索引获取对应的值
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}
