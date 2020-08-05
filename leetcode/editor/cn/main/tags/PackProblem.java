package tags;

import java.util.Arrays;

/**
 * @program: LeetCode
 * @description: 背包问题
 * @author: wd
 * @create: 2020-07-18 23:13
 **/

public class PackProblem {
    static int[] ints = {0, 0, 0, 0, 0, 0, 0, 0, 1};

    public static void main(String[] args) {
        PackProblem main = new PackProblem();
        System.out.println(
                main.findTargetSumWays(ints, 1)
        );
    }

    /**
     * 416 分割等和子集
     **/
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum != sum / 2 * 2) return false;
        sum /= 2;
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int num : nums) {
            for (int i = sum; i >= num; i--) {
                dp[i] = Math.max(dp[i], dp[i - num] + num);
            }
        }
        return dp[sum] == sum;
    }

    /**
     * 322 零钱兑换
     **/
    public int coinChange(int[] coins, int amount) {
        if (coins.length < 1) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 518 零钱兑换2
     **/
    public int change(int amount, int[] coins) {
        if (amount < 0) return -1;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = (dp[i] + dp[i - coin]);
            }
        }
        return dp[amount];
    }

    /**
     * 494 目标和
     **/
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length < 1) return 0;
        int sum = Arrays.stream(nums).sum() + 1;
        int[] dp = new int[sum + sum + 1];
        dp[sum + nums[0]] = 1;
        dp[sum - nums[0]] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] temp = new int[sum + sum + 1];
            for (int j = -sum; j <= sum; j++) {
                if (dp[j + sum] > 0) {
                    temp[j + nums[i] + sum] += dp[j + sum];
                    temp[j - nums[i] + sum] += dp[j + sum];
                }
            }
            dp = temp;
        }
        return S > sum ? 0 : dp[S + sum];
    }
}
