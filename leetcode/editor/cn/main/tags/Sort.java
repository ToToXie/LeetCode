package tags;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-09-01 16:33
 **/

public class Sort {
    public static void main(String[] args) {
        Sort main = new Sort();
        int[] nums = {1, 1, 1, 1, 1, 5, 5, 5, 5, 5};

        System.out.println(main.maximumGap(nums));

    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if ((max - min) == 0) return 0;
        int size = Math.max(1, (max - min) / (nums.length - 1));
        int len = (max - min) / size + 1;
        Bucket[] buckets = new Bucket[len];
        for (int num : nums) {
            int index = (num - min) / size;
            if (buckets[index] == null) buckets[index] = new Bucket();
            buckets[index].isUsed = true;
            buckets[index].max = Math.max(buckets[index].max, num);
            buckets[index].min = Math.min(buckets[index].min, num);
        }
        int pre = min, ans = 0;
        for (Bucket bucket : buckets) {
            if (bucket == null || !bucket.isUsed) continue;
            ans = Math.max(ans, bucket.min - pre);
            pre = bucket.max;
        }
        return ans;
    }

    /**
     * 164 最大间距
     **/
    class Bucket {
        boolean isUsed = false;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
    }
}
