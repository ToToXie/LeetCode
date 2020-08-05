package zuoyebang;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-31 14:33
 **/

public class KnowNode {
    static boolean flag = false;
    int id;
    String name;
    int pid;
    List<KnowNode> child;

    /**
     *
     **/
    static public String getDeepName(KnowNode node, String name) {
        List<String> ans = new ArrayList<>();
        getDeepNameDFS(node, name, ans);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i));
            if (i < ans.size() - 1) sb.append("-");
        }
        return sb.toString();
    }

    static public void getDeepNameDFS(KnowNode node, String name, List<String> ans) {
        if (flag) return;
//        if(node.name.equals(name)){
//            ans.add(name);
//            flag = true;
//            return ;
//        }
        ans.add(node.name);
        if (node.name.equals(name)) {
            flag = true;
            return;
        }
        for (KnowNode knowNode : node.child) {
            getDeepNameDFS(knowNode, name, ans);

        }
        ans.remove(ans.size() - 1);
    }

    static KnowNode build(List<KnowNode> list) {
        KnowNode root = new KnowNode();
        root.id = 0;
        Map<Integer, List<KnowNode>> map = new HashMap<>();
        list.stream().forEach(
                it -> {
                    if (map.containsKey(it.pid)) {
                        map.get(it.pid).add(it);
                    } else {
                        List<KnowNode> one = new ArrayList<>();
                        one.add(it);
                        map.put(it.pid, one);
                    }
                }
        );
        Queue<KnowNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            KnowNode now = queue.poll();
            now.child = new ArrayList<>();
            List<KnowNode> childs = map.get(now.id);
            for (KnowNode child : childs) {
                childs.add(child);
                queue.add(child);
            }
        }
        return root;
    }
}
