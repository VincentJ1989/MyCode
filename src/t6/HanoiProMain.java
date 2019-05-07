package t6;

/**
 * 题目：用栈来处理不能直接移动左右2端的汉诺塔问题
 *
 * @author VincentJ
 * @date 2019-05-07
 */
public class HanoiProMain {
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String MID = "mid";

    private static int count = 0;

    public static void main(String[] args) {
        hanoiSolve1(2, LEFT, MID, RIGHT);
        System.out.println("总次数：" + count);
    }

    /**
     * 实现方法1：递归
     * @param pNum 层数
     * @param pLeft 左侧塔
     * @param pMid 中间塔
     * @param pRight 右侧塔
     */
    private static int hanoiSolve1(int pNum, String pLeft, String pMid, String pRight) {
        if (pNum < 1) {
            return 0;
        }
        return process(pNum, pLeft, pMid, pRight, pLeft, pRight);
    }

    private static int process(int pNum, String pLeft, String pMid, String pRight, String pFrom, String pTo) {
        if (pNum == 1) {
            // 考虑终止条件
            if (pFrom.equals(pMid) || pTo.equals(pMid)) {
                System.out.println("①Move 1 from " + pFrom + " to " + pTo);
                count++;
                return 1;
            } else {
                System.out.println("②Move 1 from " + pFrom + " to " + pMid);
                count++;
                System.out.println("③Move 1 from " + pMid + " to " + pTo);
                count++;
                return 2;
            }
        }

        // 考虑一般情况
        // ①当从左塔准备把A往右塔移动时，先处理之前移动过的
        int part1 = process(pNum - 1, pLeft, pMid, pRight, pFrom, pTo);
        int part2 = 1;
        // ②把A移动到中间
        System.out.println("⑤Move " + pNum + " from " + pFrom + " to " + pMid);
        count++;
        // ③把之前移动到右塔的移回左塔
        int part3 = process(pNum - 1, pLeft, pMid, pRight, pTo, pFrom);
        int part4 = 1;
        // ④把A从中塔移动到右塔
        System.out.println("⑥Move " + pNum + " from " + pMid + " to " + pTo);
        count++;
        // ⑤再把之前从右塔移回过来的再移回去
        int part5 = process(pNum - 1, pLeft, pMid, pRight, pFrom, pTo);
        return part1 + part2 + part3 + part4 + part5;
    }
}
