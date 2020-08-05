package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    public int val;
    public List<Node> children;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
        next = null;
        random = null;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    public static Node buildNode(Integer[][] nums) {
        Node root = new Node(-1);
        Node pre = root;
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer[] num = nums[i];
            Integer val = num[0];
            pre.next = new Node(val);
            map.put(i, pre.next);
            pre = pre.next;
        }
        for (int i = 0; i < nums.length; i++) {
            Integer[] num = nums[i];
            Integer ram = num[1];
            if (ram == null) {
                map.get(i).random = null;
            } else {
                map.get(i).random = map.get(ram);
            }
        }

        return root.next;
    }

};