package Test;

public class PrintABVolatile {
    private static final int MAX_PRINT_NUM = 100;
    private static volatile int count = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (count < MAX_PRINT_NUM) {
                if (count % 2 == 0) {
                    System.out.println("num:" + count);
                    count++;
                }
            }
        }).start();
        new Thread(() -> {
            while (count < MAX_PRINT_NUM) {
                if (count % 2 == 1) {
                    System.out.println("num:" + count);
                    count++;
                }
            }
        }).start();
    }
}