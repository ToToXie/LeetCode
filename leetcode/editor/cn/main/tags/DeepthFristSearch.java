package tags;

import util.ListNode;
import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-06-24 20:50
 **/

public class DeepthFristSearch {
    static Integer[] integers = {1, 2, 3, null, 5};
    static int[] ints1 = {1, 2, 3, 4, 5, 6, 7};
    static Integer[] integers1 = {1, 2, 3, 4, 5, 6, 7};
    static int[] ints2 = {9, 15, 7, 20, 3};

    static int[][] intss = {
            {5, 2, 1, 5, 6, 3, 2, 0, 6, 9},
            {0, 9, 5, 4, 2, 9, 5, 2, 0, 4},
            {0, 3, 3, 3, 1, 8, 1, 7, 3, 1},
            {0, 1, 1, 6, 8, 7, 4, 9, 1, 2},
            {1, 3, 4, 8, 2, 5, 1, 6, 2, 2},
            {5, 5, 8, 1, 2, 7, 2, 3, 9, 3},
            {2, 6, 2, 0, 1, 7, 0, 9, 4, 6},
            {9, 5, 0, 7, 6, 6, 7, 8, 4, 2},
            {3, 4, 9, 3, 4, 8, 2, 8, 9, 4},
            {4, 9, 1, 3, 9, 5, 4, 9, 1, 3}
    };
    /**
     * 图像渲染
     **/
    static int[] nextIndex = {-1, 0, 1, 0, 0, -1, 0, 1};
    /**
     * 求根到叶子节点的数字之和
     **/
    int ans = 0;
    /**
     * 递增顺序查找树
     **/
    TreeNode now;
    /**
     * 二叉树原地展开为链表
     **/
    private TreeNode pre = null;
    private ListNode head;

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.buildByLevelOrder(integers);
        DeepthFristSearch main = new DeepthFristSearch();
        TreeNode root = TreeNode.buildByLevelOrder(integers1);
        System.out.println(
//                main.connect(root)

        );
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        sumNumbersDFS(root, 0);
        return ans;
    }

    public void sumNumbersDFS(TreeNode root, int sum) {
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            ans += sum;
            return;
        }
        if (root.left != null) sumNumbersDFS(root.left, sum);
        if (root.right != null) sumNumbersDFS(root.right, sum);
    }

    /**
     * 填充每个结点的下一个右侧结点指针
     * <p>
     * 层次遍历
     **/
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        Node now = root, next = null;
        root.next = next;
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            next = null;
            for (int i = 0; i < n; i++) {
                now = queue.poll();
                if (now == null) break;
                if (now.right != null) {
                    queue.add(now.right);
                    now.right.next = next;
                    next = now.right;
                }
                if (now.left != null) {
                    queue.add(now.left);
                    now.left.next = next;
                    next = now.left;
                }
            }
        }
        return root;
    }

    /**
     * 非层次遍历
     * <p>
     * 利用前面已经连接好的next指针
     **/
    public Node connectB(Node root) {
        if (root == null) return null;
        Node now = root;
        while (now.left != null) {
            Node next = now;
            while (next != null) {
                next.left.next = next.right;
                if (next.next != null) next.right.next = next.next.left;
                next = next.next;
            }
            now = now.left;
        }
        return root;
    }

    /**
     * 填充每个结点的下一个右侧结点指针2
     * 非层次遍历
     * <p>
     * 利用前面已经连接好的next指针
     **/
    public Node connectC(Node root) {
        if (root == null) return null;
        Node now = root;
        while (getFristNode(now) != null) {
            Node next = now;
            while (next != null) {
                if (next.left != null) {
                    if (next.right != null) {
                        next.left.next = next.right;
                        next.right.next = getFristNode(next.next);
                    } else {
                        next.left.next = getFristNode(next.next);
                    }
                } else {
                    if (next.right != null) {
                        next.right.next = getFristNode(next.next);
                    }
                }

                next = next.next;
            }
            now = getFristNode(now);
        }
        return root;
    }

    private Node getFristNode(Node node) {
        if (node == null) return null;
        else if (node.left != null) return node.left;
        else if (node.right != null) return node.right;
        else return getFristNode(node.next);
    }

    public void flatten(TreeNode root) {
        flattenDFS(root);
    }

    public void flattenDFS(TreeNode root) {
        if (root == null) return;

        flattenDFS(root.right);
        flattenDFS(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    /**
     * 迭代写法
     **/
    public void flattenB(TreeNode root) {
        while (root != null) {
            TreeNode left = root.left;
            if (left == null) {
                root = root.right;
                continue;
            }
            while (left.right != null) {
                left = left.right;
            }
            left.right = root.right;
            root.right = root.left;
            root.left = null;
            root = root.right;
        }
    }

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;
        now = new TreeNode(-1);
        TreeNode ans = now;
        increasingBSTDFS(root);
        return ans.right;
    }

    public void increasingBSTDFS(TreeNode root) {
        if (root == null) return;
        increasingBSTDFS(root.left);
        now.right = new TreeNode(root.val);
        now = now.right;
        increasingBSTDFS(root.right);
    }

    /**
     * 路径总和2
     **/
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        pathSumDFS(root, 0, sum, ans, new ArrayList<>());
        return ans;
    }

    public void pathSumDFS(TreeNode root, int total, int sum, List<List<Integer>> ans, List<Integer> list) {
        if (root == null) {
            if (total == sum) ans.add(new ArrayList<>(list));
        } else if (root != null) {
            list.add(root.val);
            if (root.left == null) {
                pathSumDFS(root.right, total + root.val, sum, ans, list);
            } else if (root.right == null) {
                pathSumDFS(root.left, total + root.val, sum, ans, list);
            } else {
                pathSumDFS(root.left, total + root.val, sum, ans, list);
                pathSumDFS(root.right, total + root.val, sum, ans, list);
            }
            list.remove(list.size() - 1);
        }
    }

    /**
     * 叶子相似的树
     **/
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        leafSimilarDFS(root1, leftList);
        leafSimilarDFS(root2, rightList);
        return leftList.equals(rightList);
    }

    public void leafSimilarDFS(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            list.add(root.val);
        }
        leafSimilarDFS(root.left, list);
        leafSimilarDFS(root.right, list);
    }

    /**
     * 有序链表，建立平衡二叉搜索树
     **/
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        TreeNode treeNode = sortedListToBSTDFS(nums, 0, nums.size());
        return treeNode;
    }

    public TreeNode sortedListToBSTDFS(List<Integer> nums, int left, int right) {
        if (left >= right) return null;
        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = sortedListToBSTDFS(nums, left, mid);
        root.right = sortedListToBSTDFS(nums, mid + 1, right);
        return root;
    }

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertListToBST(l, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;

        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;

        // Recurse on the right hand side and form BST out of them
        node.right = this.convertListToBST(mid + 1, r);
        return node;
    }

    /**
     * 模拟中序遍历，实际的访问顺序就是升序，
     **/
    public TreeNode sortedListToBSTB(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        this.head = head;

        // Form the BST now that we know the size
        return convertListToBST(0, size - 1);
    }

    /**
     * 中序 后序 建树
     **/
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length < 1) return null;
        return buildTreeDFS(inorder, postorder, 0, inorder.length, 0, postorder.length);
    }

    public TreeNode buildTreeDFS(int[] inorder, int[] postorder,
                                 int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft >= inRight) return null;
        int x = postorder[postRight - 1];
        int mid = inLeft;
        while (inorder[mid] != x) mid++;
        TreeNode root = new TreeNode(x);
        root.left = buildTreeDFS(inorder, postorder, inLeft, mid, postLeft, postLeft + mid - inLeft);
        root.right = buildTreeDFS(inorder, postorder, mid + 1, inRight, postLeft + mid - inLeft, postRight - 1);
        return root;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (newColor != image[sr][sc]) floodFillDFS(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    public void floodFillDFS(int[][] image, int x, int y, int newColor, int oldColor) {
        image[x][y] = newColor;
        for (int i = 0; i < 8; i += 2) {
            int newx = x + nextIndex[i];
            int newy = y + nextIndex[i + 1];
            if (newx < 0 || newx >= image.length ||
                    newy < 0 || newy >= image[0].length) continue;
            if (image[newx][newy] == oldColor) {
                floodFillDFS(image, newx, newy, newColor, oldColor);
            }
        }
    }

    /**
     * 二叉树的所有路径
     **/
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return null;
        List<String> ans = new ArrayList<>();
        binaryTreePathsDFS(root, new ArrayList<>(), ans);
        return ans;
    }

    public void binaryTreePathsDFS(TreeNode root, List<Integer> list, List<String> ans) {
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : list) {
                sb.append(integer).append(">");
            }
            sb.append(root.val);
            ans.add(sb.toString());
            return;
        }
        list.add(root.val);
        if (root.left != null) binaryTreePathsDFS(root.left, list, ans);
        if (root.right != null) binaryTreePathsDFS(root.right, list, ans);
        list.remove(list.size() - 1);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
