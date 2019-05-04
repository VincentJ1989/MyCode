package t3;

import java.util.Stack;

/**
 * 题目：仅使用递归和栈操作实现逆序一个栈.
 * 核心：理解2个递归的作用.
 *
 * @author VincentJ
 * @date 2019-05-05
 */
public class ReverseSortStackMain {
    private static final int MAX = 6;

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < MAX; i++) {
            stack.push(i);
        }

        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 将栈的栈底元素返回并移除(调用一次移除一次)
     */
    private static int getAndRemoveLastElement(Stack<Integer> pStack) {
        int result = pStack.pop();
        if (pStack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(pStack);
            pStack.push(result);
            return last;
        }
    }

    /**
     * 逆序一个栈
     */
    private static void reverse(Stack<Integer> pStack) {
        if (pStack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(pStack);
        reverse(pStack);
        pStack.push(i);
    }

}
