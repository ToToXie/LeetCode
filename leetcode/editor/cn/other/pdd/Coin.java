package pdd;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-22 16:36
 **/

public class Coin {

    int[] coins;
    int[] count;

    public static void main(String[] args) {
        Coin coin = new Coin();
        int[] ints = {15, 5, 10, 15, 20};
        int[] ints2 = {5, 10, 20};
        coin.init(ints);
        System.out.println(coin.buy(ints));
    }

    public void init(int[] temp) {
        coins = temp;
        count = new int[coins.length];
    }

    public int buy(int[] pers) {
        for (int i = 0; i < pers.length; i++) {
            if (isOk(pers[i] - 5)) {
                doBuy(pers[i]);
            } else {
                return i;
            }
        }
        return -1;
    }

    public boolean isOk(int coin) {
        int index = coins.length - 1;
        while (index >= 0) {
            int temp = count[index];
            while (temp > 0 && coin > 0 && coins[index] <= coin) {
                coin -= coins[index];
                temp--;
            }
            index--;
        }
        return coin == 0;
    }

    public void doBuy(int coin) {
        int temp = coin - 5;
        int index = coins.length - 1;
        while (index >= 0) {
            if (coin == coins[index]) count[index]++;
            while (count[index] > 0 && temp > 0 && coins[index] <= temp) {
                temp -= coins[index];
                count[index]--;
            }
            index--;
        }
    }

}
