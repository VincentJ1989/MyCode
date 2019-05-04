package t5;

import java.util.Stack;

/**
 * 题目：用一个栈实现另一个栈的排序（从顶到底按从大到小的顺序） 
 * 思路：利用辅助栈生成一个有序排列，而后在依次pop出即可。 
 *      1.如果cur＜help栈顶元素，直接push； 
 *      2.如果cur>=help栈顶元素，则将help的元素逐一弹出，并逐一压入stack。 
 *        直到cur<=help的栈顶元素，再将cur压入help.
 *
 * @author VincentJ
 * @date 2019-05-05
 */
public class SortStackByStackMain {
    public static void main(String[] args) {
        Stack<Integer> stackData = new Stack<>();
        stackData.push(1);
        stackData.push(3);
        stackData.push(5);
        stackData.push(4);
        stackData.push(7);
        stackData.push(2);
        stackData.push(4);
        stackData.push(6);

        sortStackByStack(stackData);

        while (!stackData.isEmpty()) {
            System.out.println(stackData.pop());
        }
    }

    private static void sortStackByStack(Stack<Integer> pStack) {
        Stack<Integer> helpStack = new Stack<>();
        // ①构建从小到大的辅助栈(因为题目要求是把原有栈按从大到小排序)
        while (!pStack.isEmpty()) {
            int cur = pStack.pop();
            while (!helpStack.isEmpty() && cur > helpStack.peek()) {
                pStack.push(helpStack.pop());
            }
            helpStack.push(cur);
        }
        // ②这里将从小到大的辅助栈依次压会原有栈
        while (!helpStack.isEmpty()) {
            pStack.push(helpStack.pop());
        }
    }
}
