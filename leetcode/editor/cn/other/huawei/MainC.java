package huawei;

import util.TreeNode;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-20 18:54
 **/

public class MainC {
    /**
     * 一个街区，为提高街区安全性，需在街区的路灯上安装若干摄像头，
     * 用一个二叉树表示街区的路灯，每个节点表示一个路灯，在路灯上安装摄像头。
     * 每个摄影头都可以监视其父对象、自身及其直接子对象。为保证每个路灯都被监控，
     * 请计算街区所需的最小摄像头数量。
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Integer[] nums = new Integer[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'o') {
                nums[i] = 1;
            } else {
                nums[i] = null;
            }
        }
        TreeNode treeNode = TreeNode.bulideByPreOrder(nums);
        System.out.println(treeNode);

    }
}
//class Node{
//    Node left,right,parent;
//    boolean is;
//    public Node() {
//        this.left = null;
//        this.right = null;
//        this.parent = null;
//        this.is = false;
//    }
//    public Node builder(String str){
//        Node root = new Node();
//        Node p = root,pre = null;
//        return null;
//    }
//}
