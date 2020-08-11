package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @program: LeetCode
 * @description: 手写跳表
 * @author: wd
 * @create: 2020-08-11 15:23
 **/

public class MySkipList {
    SkipListNode head, tail;
    int size;
    int high;
    Random random;

    public MySkipList() {
        this.head = new SkipListNode(SkipListNode.negInf, null);
        this.tail = new SkipListNode(SkipListNode.posInf, null);
        head.right = tail;
        tail.left = head;
        this.size = 0;
        this.high = 0;
        random = new Random();
    }

    public static void main(String[] args) {
        MySkipList mySkipList = new MySkipList();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
//        for (int i = 0; i < 20; i++) {
//            int k = random.nextInt(100);
//            skipList.put(Integer.toString(k), k);
//        }
        for (Integer integer : list) {
            mySkipList.put(integer, integer);
        }
        System.out.println(mySkipList.toString());

    }

    public String toString() {
        SkipListNode now = head;
        while (now.down != null) now = now.down;
        now = now.right;
        StringBuilder sb = new StringBuilder();
        while (now != null && !now.equals(tail.key)) {
            SkipListNode p = now;
            while (p != null) {
                if (p != now) sb.append(" -> ");
                sb.append(p.key);
                p = p.up;
            }
            sb.append("\n$\n");
            now = now.right;
        }
        sb.append("\nhigh    := " + high);
        sb.append("\nsize    := " + size);
        return sb.toString();
    }

    public Integer get(Integer key) {
        SkipListNode node = findSkipListNode(key);
        if (node.equals(key)) {
            return node.value;
        } else {
            return null;
        }
    }

    public void put(Integer key, Integer value) {
        SkipListNode node = findSkipListNode(key);
        if (node.key == key) {
            node.value = value;
        } else {
            SkipListNode now = new SkipListNode(key, value);
            insertNodeByHorizontal(node, now);
            int currentLevel = 0;
            while (random.nextDouble() < 0.5) {
                if (currentLevel >= high) {
                    addMaxHighLevel();
                }
                while (node.left != null && node.up == null) {
                    node = node.left;
                }
                node = node.up;
                SkipListNode temp = new SkipListNode(key, null);
                insertNodeByHorizontal(node, temp);
                linkByVertial(temp, now);
                now = now.up;
                currentLevel++;
            }
            size++;
        }

    }

    /**
     * 需要 增加新的 一层
     **/
    private void addMaxHighLevel() {
        SkipListNode p1 = new SkipListNode(SkipListNode.negInf, null);
        SkipListNode p2 = new SkipListNode(SkipListNode.posInf, null);
        p1.right = p2;
        p2.left = p1;

        linkByVertial(p1, head);
        linkByVertial(p2, tail);

        head = head.up;
        tail = tail.up;
        high++;
    }

    /**
     * 将 up 节点 连接到 down 节点上面
     **/
    private void linkByVertial(SkipListNode up, SkipListNode down) {
        down.up = up;
        up.down = down;
    }

    /**
     * 将 节点 插入横向的链表中
     **/
    private void insertNodeByHorizontal(SkipListNode prev, SkipListNode now) {
        now.left = prev;
        now.right = prev.right;
        prev.right.left = now;
        prev.right = now;
    }

    public void remove(Integer key) {
        SkipListNode now = findSkipListNode(key);
        if (now.equals(key)) {
            while (now != null) {
                now.left.right = now.right;
                now.right.left = now.left;
                now = now.up;
                now.down.up = null;
                now.down = null;
            }
        }


    }

    private SkipListNode findSkipListNode(Integer key) {
        SkipListNode now = head;
        while (true) {
            while (now.right.key != SkipListNode.posInf && now.right.key <= key) now = now.right;
            if (now.down != null) {
                now = now.down;
            } else {
                break;
            }
        }
        return now;
    }

    static class SkipListNode {
        static final Integer negInf = Integer.MIN_VALUE;
        static final Integer posInf = Integer.MAX_VALUE;
        SkipListNode left, right, up, down;
        Integer key;
        Integer value;

        public SkipListNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "key=" + key;
        }
    }
}
