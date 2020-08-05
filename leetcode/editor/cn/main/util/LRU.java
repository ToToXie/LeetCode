package util;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: LeetCode
 * @description: 手写LRU
 * @author: wd
 * @create: 2020-07-17 16:02
 **/

public class LRU {
    private Node head;
    private Node tail;
    private Map<Integer, Node> cache = new HashMap<>();
    private int capacity;
    private int size;

    public LRU(int capacity) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
        size = 0;
    }

    public static void main(String[] args) {

        LRU lru = new LRU(2);

        lru.put(1, 1);
        lru.print();
        lru.put(2, 2);
        lru.print();
        lru.get(1);       // 返回  1
        lru.print();
        lru.put(3, 3);    // 该操作会使得密钥 2 作废
        lru.print();
        lru.get(2);       // 返回 -1 (未找到)
        lru.print();
        lru.put(4, 4);    // 该操作会使得密钥 1 作废
        lru.print();
        lru.get(1);       // 返回 -1 (未找到)
        lru.print();
        lru.get(3);       // 返回  3
        lru.print();
        lru.get(4);       // 返回  4
        lru.print();
    }

    public void print() {

        Node now = head.next;
        while (now != tail) {
            System.out.print(now.value);
            if (now.next != tail) System.out.print(" -> ");
            now = now.next;
        }
        System.out.println();
    }

    public int get(Integer key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        } else {
            moveToHead(node);
            return node.value;
        }
    }

    public void put(Integer key, Integer value) {
        Node node = cache.get(key);
        if (node == null) {
            node = new Node(value);
            size++;
            cache.put(key, node);
            addToHead(node);
            if (size > capacity) {
                cache.remove(deleteTail());
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void moveToHead(Node node) {
        deleteNode(node);
        addToHead(node);
    }

    private int deleteTail() {
        Node last = tail.pre;
        last.pre.next = tail;
        tail.pre = last.pre;
        return last.value;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    private void deleteNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    class Node {
        private Node pre;
        private Node next;
        private Integer value;

        public Node() {
        }

        public Node(Integer value) {
            this.value = value;
        }
    }
}
