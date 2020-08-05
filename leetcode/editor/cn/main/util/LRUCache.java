package util;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    DLinkedNode head, tail;
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;

    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.print();
        cache.put(2, 2);
        cache.print();
        cache.get(1);       // 返回  1
        cache.print();
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.print();
        cache.get(2);       // 返回 -1 (未找到)
        cache.print();
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.print();
        cache.get(1);       // 返回 -1 (未找到)
        cache.print();
        cache.get(3);       // 返回  3
        cache.print();
        cache.get(4);       // 返回  4
        cache.print();

    }

    public void print() {
        DLinkedNode node = head.next;
        while (node != tail) {
            System.out.print(node.value);
            System.out.print(" -> ");
            node = node.next;
        }
        System.out.println();
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        } else {
            moveToHead(node);
        }
        return node.value;
    }

    private void moveToHead(DLinkedNode node) {
        deleteNode(node);
        addToHead(node);
    }

    private void addToHead(DLinkedNode node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    private void deleteNode(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private int removeTail() {
        DLinkedNode rest = tail.pre;
        deleteNode(rest);
        return rest.key;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            size++;
            node = new DLinkedNode(key, value);
            cache.put(key, node);
            addToHead(node);
            if (size > capacity) {
                size--;
                int oldKey = removeTail();
                cache.remove(oldKey);
            }
        } else {
            node.value = value;
            moveToHead(node);
        }

    }

    class DLinkedNode {
        DLinkedNode pre;
        DLinkedNode next;
        int key;
        int value;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}