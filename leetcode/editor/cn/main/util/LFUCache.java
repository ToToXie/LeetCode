package util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LFUCache {
    TreeSet<Node> cache;
    Map<Integer, Node> keyMap;
    int capacity;
    int size;
    int index;

    public LFUCache(int capacity) {
        cache = new TreeSet<>();
        keyMap = new HashMap<>();
        size = 0;
        this.capacity = capacity;
        this.index = 0;
    }

    public static void main(String[] args) {

        LFUCache lfuCache = new LFUCache(2);

        lfuCache.put(3, 1);
        lfuCache.put(2, 1);
        lfuCache.put(2, 2);
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(2));

    }

    public int get(int key) {
        Node node = keyMap.get(key);
        if (node == null) {
            return -1;
        }
        cache.remove(node);
        node.times++;
        node.index = index++;
        cache.add(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;
        Node node = keyMap.get(key);
        if (node == null) {
            node = new Node(1, index++, value, key);
            if (size == capacity) {
                keyMap.remove(cache.pollFirst().key);
                size--;
            }
            size++;
            keyMap.put(key, node);
            cache.add(node);
        } else {
            cache.remove(node);
            node.index = index++;
            node.times++;
            node.value = value;
            cache.add(node);
        }
    }

    static class Node implements Comparable {
        int times, index, value, key;


        public Node(int times, int index, int value, int key) {
            this.times = times;
            this.index = index;
            this.value = value;
            this.key = key;
        }

        @Override
        public int compareTo(Object o) {
            Node node = (Node) o;
            return this.times == node.times ? this.index - node.index : this.times - node.times;
        }
    }
}