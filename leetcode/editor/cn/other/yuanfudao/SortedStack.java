package yuanfudao;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈排序
 **/
public class SortedStack {
    Deque<Integer> a, b;


    public SortedStack() {
        a = new ArrayDeque<>();
        b = new ArrayDeque<>();
    }

    public void push(int val) {
        int max = a.isEmpty() ? Integer.MAX_VALUE : a.peekLast();
        int min = b.isEmpty() ? Integer.MIN_VALUE : b.peekLast();
        //比较原始栈与辅助栈栈顶值，使其满足 辅助栈 <= val <= 原始栈
        while (true) {
            if (val > max) {
                b.addLast(a.pollLast());
                max = a.isEmpty() ? Integer.MAX_VALUE : a.peekLast();
            } else if (val < min) {
                a.addLast(b.pollLast());
                min = b.isEmpty() ? Integer.MIN_VALUE : b.peekLast();
            } else {
                a.addLast(val);
                break;
            }
        }
    }

    public void pop() {
        while (!b.isEmpty()) {
            a.addLast(b.pollLast());
        }
        if (!a.isEmpty()) a.pollLast();
    }

    public int peek() {
        while (!b.isEmpty()) {
            a.addLast(b.pollLast());
        }
        if (!a.isEmpty()) return a.peekLast();
        else return -1;
    }

    public boolean isEmpty() {
        return a.isEmpty() && b.isEmpty();
    }
}