package t2;

import java.util.Stack;

/**
 * 题目：双栈实现一个队列.
 *
 * 思路：栈是FILO，而队列是FIFO，双栈刚好把顺序反过来（一个作为压入栈，一个作为弹出栈）.
 * 注意点：在往弹出栈导数据时，要保证一次性；弹出栈只要不为空就不能往里面导数据。
 *
 * @author VincentJ
 * @date 2019-05-04
 */
public class TwoStackQueue {
    /**
     * 压入栈
     */
    private Stack<Integer> stackPush;
    /**
     * 弹出栈
     */
    private Stack<Integer> stackPop;

    public TwoStackQueue(Stack<Integer> stackPush, Stack<Integer> stackPop) {
        this.stackPush = stackPush;
        this.stackPop = stackPop;
    }

    /**
     * 压入数据的时机可以有多个，这里选择在add的时候
     */
    private void pushToPop() {
        // 只有为空了才能导数据
        if (this.stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void add(int pNewNum) {
        this.stackPush.push(pNewNum);
        pushToPop();
    }

    public int poll() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        // pushToPop();
        return stackPush.pop();
    }

    public int peek() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        // pushToPop();
        return stackPop.pop();
    }
}
