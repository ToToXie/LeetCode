package pdd;

import java.util.Scanner;

/*
 * A 国的手机号码由且仅由 N 位十进制数字(0-9)组成。
 * 一个手机号码中有至少 K 位数字相同则被定义为靓号。
 * A 国的手机号可以有前导零，比如 000123456 是一个合法的手机号。
 * 小多想花钱将自己的手机号码修改为一个靓号。
 * 修改号码中的一个数字需要花费的金额为新数字与旧数字之间的差值。
 * 比如将 1 修改为 6 或 6 修改为 1 都需要花 5 块钱。
 * 给出小多现在的手机号码，问将其修改成一个靓号，最少需要多少钱？
 *
 * 输入描述:
 * 第一行包含2个整数 N、K，分别表示手机号码数字个数以及靓号至少有 K 个数字相同。
 * 第二行包含 N 个字符，每个字符都是一个数字('0'-'9')，数字之间没有任何其他空白符。表示小多的手机号码。
 * 数据范围：2 <= K <= N <= 10000
 *
 * 输出描述:
 * 第一行包含一个整数，表示修改成一个靓号，最少需要的金额。
 * 第二行包含 N 个数字字符，表示最少花费修改的新手机号。若有多个靓号花费都最少，则输出字典序最小的靓号。
 *
 * 示例1
 * 输入
 * 6 5
 * 787585
 *
 * 输出
 * 4
 * 777577
 *
 * 说明
 * 花费为4的方案有两种：777577与777775，前者字典序更小。
 */

/*
 * 算法：最小代价优先的贪心算法
 * 数据结构：字符串
 * 字典序：
 * 1）如果手机号中数字比最佳数字大的情况就从前往后改；
 * 2）如果手机号中数字比最佳数字小的情况就从后往前改;
 * 3）1、2顺序不能颠倒
 */
public class Main {

    //得到0-9在原手机号中出现的次数
    private static int[] getRepeatTimes(char[] phoneNum) {
        int[] repeatTimes = new int[10];
        for (int i = 0; i < phoneNum.length; i++) {
            repeatTimes[phoneNum[i] - '0']++;
        }
        return repeatTimes;
    }

    //最小代价优先的贪心算法
    private static void GreedyMinCost(int phoneNumSize, int neededRepeatTimes, char[] phoneNum) {
        int minCost = Integer.MAX_VALUE;//想要最小值，初值设置最大整数
        char[] newPhoneNum = new char[phoneNumSize];
        int[] repeatTimes = getRepeatTimes(phoneNum);

        for (int currentNum = 0; currentNum < 10; currentNum++) {

            int restNeededRepeatTimes = neededRepeatTimes - repeatTimes[currentNum];
            //初始可能重复数字次数就大于需求值
            if (restNeededRepeatTimes <= 0) {
                minCost = 0;
                newPhoneNum = phoneNum;
                break;
            }

            int currentNumCost = 0;
            int upperLimitNum = currentNum + 1;
            int lowerLimitNum = currentNum - 1;

            char[] alternativePhoneNum = new char[phoneNumSize];
            //alternativePhoneNum每次循环前都初始为phoneNum的复制
            System.arraycopy(phoneNum, 0, alternativePhoneNum, 0, phoneNumSize);

            while (restNeededRepeatTimes > 0) {
                //如果手机号中数字比最佳数字大的情况就从前往后改
                if (upperLimitNum < 10) {
                    for (int i = 0; i < phoneNumSize && restNeededRepeatTimes > 0; i++) {
                        if (phoneNum[i] - '0' == upperLimitNum) {
                            currentNumCost += upperLimitNum - currentNum;
                            alternativePhoneNum[i] = (char) (currentNum + '0');
                            restNeededRepeatTimes--;
                        }
                    }
                }
                //如果手机号中数字比最佳数字小的情况就从后往前改
                if (lowerLimitNum >= 0) {
                    for (int i = phoneNumSize - 1; i >= 0 && restNeededRepeatTimes > 0; i--) {
                        if (phoneNum[i] - '0' == lowerLimitNum) {
                            currentNumCost += currentNum - lowerLimitNum;
                            alternativePhoneNum[i] = (char) (currentNum + '0');
                            restNeededRepeatTimes--;
                        }
                    }
                }
                //扩大上下限
                upperLimitNum++;
                lowerLimitNum--;
            }

            if (currentNumCost < minCost) {
                minCost = currentNumCost;
                newPhoneNum = alternativePhoneNum;
            }
        }

        System.out.println(minCost);
        System.out.println(newPhoneNum);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int phoneNumSize = input.nextInt();
        int neededRepeatTimes = input.nextInt();
        //第二行的输入的手机号是字符串不是整数！输出也很方便，值运算时清除'0'的影响
        char[] phoneNum = input.next().toCharArray();
        input.close();

        GreedyMinCost(phoneNumSize, neededRepeatTimes, phoneNum);
    }
}