package bilibili;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-13 19:43
 **/

public class Mian_1 {
    public boolean Game24Points(int[] arr) {
        // write code here
        if (arr.length == 1 && arr[0] == 24) {
            return true;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) {
                    continue;
                }
                int[] temp = new int[arr.length - 1];
                int k = 0, l = 0;
                for (; l < arr.length; l++) {
                    if (l != i && l != j) {
                        temp[k] = arr[l];
                        k++;
                    }
                }
                temp[k] = arr[i] + arr[j];
                if (Game24Points(temp)) return true;
                temp[k] = arr[i] - arr[j];
                if (Game24Points(temp)) return true;
                temp[k] = arr[i] * arr[j];
                if (Game24Points(temp)) return true;
                if (arr[j] != 0)
                    temp[k] = arr[i] / arr[j];
                if (Game24Points(temp)) return true;
            }
        }
        return false;
    }


}
