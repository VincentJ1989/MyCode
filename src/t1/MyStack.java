package t1;

import java.util.Stack;

/**
 * 题目：带getMin()的栈结构
 * 思路：双栈，一个正常保存数据，一个用来存储最小值（当前数据栈中的最小值，有重复的）。
 *
 * @author VincentJ
 * @date 2019-05-04
 */
public class MyStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack(Stack<Integer> stackData, Stack<Integer> stackMin) {
        this.stackData = stackData;
        this.stackMin = stackMin;
    }


    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("MinStack is empty.");
        }
        return this.stackMin.peek();
    }

    public void push(int pNewNum) {
        this.stackData.push(pNewNum);

        if (this.stackMin.isEmpty()) {
            this.stackMin.push(pNewNum);
        } else if (this.getMin() > pNewNum) {
            this.stackMin.push(pNewNum);
        } else {
            int curMin = this.getMin();
            this.stackMin.push(curMin);
        }
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("DataStack is empty.");
        }
        this.stackMin.pop();
        return this.stackData.pop();
    }

}
