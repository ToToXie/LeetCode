package Test;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintABAtomic {
    // 打印何时结束需要设置一个上限，打印到100结束；
    private static final int MAX_PRINT_NUM = 100;
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(() -> {
            while (atomicInteger.get() < MAX_PRINT_NUM) {
                // 打印奇数.
                if (atomicInteger.get() % 2 == 0) {
                    System.out.println("num:" + atomicInteger.get());
                    atomicInteger.incrementAndGet();
                }
            }
        }).start();
        new Thread(() -> {
            while (atomicInteger.get() < MAX_PRINT_NUM) {
                // 打印偶数.
                if (atomicInteger.get() % 2 == 1) {
                    System.out.println("num:" + atomicInteger.get());
                    atomicInteger.incrementAndGet();
                }
            }
        }).start();
    }
}