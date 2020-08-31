package tags;

import util.UnionFind;

import java.util.*;

/**
 * @program: LeetCode
 * @description: 并查集
 * @author: wd
 * @create: 2020-08-26 11:08
 **/

public class DisjointSetUnion {
    /**
     * 面试题 17.07 婴儿名字
     * 输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"],
     * synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
     * 输出：["John(27)","Chris(36)"]
     **/
    static String[] names = {"John(15)", "Jon(12)", "Chris(13)", "Kris(4)", "Christopher(19)"};
    static String[] synonyms = {"(Jon,John)", "(John,Johnny)", "(Chris,Kris)", "(Chris,Christopher)"};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(trulyMostPopular(names, synonyms)));
    }

    static public String[] trulyMostPopular(String[] names, String[] synonyms) {
        int[] times = new int[names.length];
        String[] ans = new String[names.length];
        Map<String, Integer> map = new HashMap<>();
        int id = 0;
        for (String name : names) {
            int index = name.lastIndexOf("(");
            String nameStr = name.substring(0, index);
            String timesStr = name.substring(index + 1, name.length() - 1);
            map.putIfAbsent(nameStr, id);
            times[id++] = Integer.parseInt(timesStr);
        }
        UnionFind unionFind = new UnionFind(id);
        Set<Integer> set = new HashSet<>();
        for (String synonym : synonyms) {
            int index = synonym.lastIndexOf(",");
            String a = synonym.substring(1, index);
            String b = synonym.substring(index + 1, synonym.length() - 1);
            Integer aid = map.get(a);
            Integer bid = map.get(b);
            if (aid == null || bid == null) continue;
            int ap = unionFind.find(aid);
            int bp = unionFind.find(bid);
            times[ap] += times[bp];
            unionFind.union(ap, bp);
            String c = a.compareTo(b) < 0 ? a : b;
            ans[ap] = c;
            ans[bp] = c;
        }
        for (String name : ans) {
            if (name == null) continue;
            set.add(unionFind.find(map.get(name)));
        }
        String[] anss = new String[set.size()];
        id = 0;
        for (Integer integer : set) {
            anss[id++] = ans[integer] + "(" + times[integer] + ")";
        }
        return anss;
    }

    /**
     * 684 冗余链接
     **/
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind set = new UnionFind(n);
        for (int[] edge : edges) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            if (set.isConnected(a, b)) {
                return edge;
            } else {
                set.union(a, b);
            }
        }
        return null;
    }

    /**
     * 685 冗余链接2
     **/
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        UnionFind set = new UnionFind(n);
        int[] input = new int[n];
        int[] indexs = new int[2];
        int target = -1;
        int cnt = 0;
        for (int[] edge : edges) {
            int b = edge[1] - 1;
            input[b]++;
            if (input[b] == 2) {
                target = b;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            if (b == target) {
                indexs[cnt++] = i;
                continue;
            }
            if (set.isConnected(a, b)) {
                return edge;
            } else {
                set.union(a, b);
            }
        }
        int[] edge = edges[indexs[0]];
        int a = edge[0] - 1;
        int b = edge[1] - 1;
        if (set.isConnected(a, b)) {
            return edges[indexs[0]];
        } else {
            return edges[indexs[1]];
        }
    }

    /**
     * 721 合并账户
     **/
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> mailToName = new HashMap<>();
        Map<String, Integer> mailToID = new HashMap<>();
        int id = 0;
        for (List<String> account : accounts) {
            String name = "";
            for (String email : account) {
                if (name.equals("")) {
                    name = email;
                    continue;
                }
                mailToID.putIfAbsent(email, id++);
                mailToName.putIfAbsent(email, name);
            }
        }
        UnionFind un = new UnionFind(id);
        for (List<String> account : accounts) {
            for (int i = 2; i < account.size(); i++) {
                String email = account.get(i);
                un.union(mailToID.get(account.get(1)), mailToID.get(email));
            }
        }
        Map<Integer, List<String>> map = new HashMap<>();
        for (String email : mailToName.keySet()) {
            int parent = un.find(mailToID.get(email));
            map.computeIfAbsent(parent, x -> new ArrayList<>()).add(email);
        }
        List<List<String>> ans = new ArrayList<>();
        map.values().stream().forEach(it -> {
            Collections.sort(it);
            it.add(0, mailToName.get(it.get(0)));
            ans.add(it);
        });
        return ans;
    }
//    static String[] names = {"Fcclu(70)",
//            "Ommjh(63)",
//            "Dnsay(60)",
//            "Qbmk(45)",
//            "Unsb(26)",
//            "Gauuk(75)",
//            "Wzyyim(34)",
//            "Bnea(55)",
//            "Kri(71)",
//            "Qnaakk(76)",
//            "Gnplfi(68)",
//            "Hfp(97)",
//            "Qoi(70)",
//            "Ijveol(46)",
//            "Iidh(64)",
//            "Qiy(26)",
//            "Mcnef(59)",
//            "Hvueqc(91)",
//            "Obcbxb(54)",
//            "Dhe(79)",
//            "Jfq(26)",
//            "Uwjsu(41)",
//            "Wfmspz(39)",
//            "Ebov(96)",
//            "Ofl(72)",
//            "Uvkdpn(71)",
//            "Avcp(41)",
//            "Msyr(9)",
//            "Pgfpma(95)",
//            "Vbp(89)",
//            "Koaak(53)",
//            "Qyqifg(85)",
//            "Dwayf(97)",
//            "Oltadg(95)",
//            "Mwwvj(70)",
//            "Uxf(74)",
//            "Qvjp(6)",
//            "Grqrg(81)",
//            "Naf(3)",
//            "Xjjol(62)",
//            "Ibink(32)",
//            "Qxabri(41)",
//            "Ucqh(51)",
//            "Mtz(72)",
//            "Aeax(82)",
//            "Kxutz(5)",
//            "Qweye(15)",
//            "Ard(82)",
//            "Chycnm(4)",
//            "Hcvcgc(97)",
//            "Knpuq(61)",
//            "Yeekgc(11)",
//            "Ntfr(70)",
//            "Lucf(62)",
//            "Uhsg(23)",
//            "Csh(39)",
//            "Txixz(87)",
//            "Kgabb(80)",
//            "Weusps(79)",
//            "Nuq(61)",
//            "Drzsnw(87)",
//            "Xxmsn(98)",
//            "Onnev(77)",
//            "Owh(64)",
//            "Fpaf(46)",
//            "Hvia(6)",
//            "Kufa(95)",
//            "Chhmx(66)",
//            "Avmzs(39)",
//            "Okwuq(96)",
//            "Hrschk(30)",
//            "Ffwni(67)",
//            "Wpagta(25)",
//            "Npilye(14)",
//            "Axwtno(57)",
//            "Qxkjt(31)",
//            "Dwifi(51)",
//            "Kasgmw(95)",
//            "Vgxj(11)",
//            "Nsgbth(26)",
//            "Nzaz(51)",
//            "Owk(87)",
//            "Yjc(94)",
//            "Hljt(21)",
//            "Jvqg(47)",
//            "Alrksy(69)",
//            "Tlv(95)",
//            "Acohsf(86)",
//            "Qejo(60)",
//            "Gbclj(20)",
//            "Nekuam(17)",
//            "Meutux(64)",
//            "Tuvzkd(85)",
//            "Fvkhz(98)",
//            "Rngl(12)",
//            "Gbkq(77)",
//            "Uzgx(65)",
//            "Ghc(15)",
//            "Qsc(48)",
//            "Siv(47)"};
//    static String[] synonyms = {"(Gnplfi,Qxabri)",
//            "(Uzgx,Siv)",
//            "(Bnea,Lucf)",
//            "(Qnaakk,Msyr)",
//            "(Grqrg,Gbclj)",
//            "(Uhsg,Qejo)",
//            "(Csh,Wpagta)",
//            "(Xjjol,Lucf)",
//            "(Qoi,Obcbxb)",
//            "(Npilye,Vgxj)",
//            "(Aeax,Ghc)",
//            "(Txixz,Ffwni)",
//            "(Qweye,Qsc)",
//            "(Kri,Tuvzkd)",
//            "(Ommjh,Vbp)",
//            "(Pgfpma,Xxmsn)",
//            "(Uhsg,Csh)",
//            "(Qvjp,Kxutz)",
//            "(Qxkjt,Tlv)",
//            "(Wfmspz,Owk)",
//            "(Dwayf,Chycnm)",
//            "(Iidh,Qvjp)",
//            "(Dnsay,Rngl)",
//            "(Qweye,Tlv)",
//            "(Wzyyim,Kxutz)",
//            "(Hvueqc,Qejo)",
//            "(Tlv,Ghc)",
//            "(Hvia,Fvkhz)",
//            "(Msyr,Owk)",
//            "(Hrschk,Hljt)",
//            "(Owh,Gbclj)",
//            "(Dwifi,Uzgx)",
//            "(Iidh,Fpaf)",
//            "(Iidh,Meutux)",
//            "(Txixz,Ghc)",
//            "(Gbclj,Qsc)",
//            "(Kgabb,Tuvzkd)",
//            "(Uwjsu,Grqrg)",
//            "(Vbp,Dwayf)",
//            "(Xxmsn,Chhmx)",
//            "(Uxf,Uzgx)"};

    /**
     * 947 移除最多的同行或同列石头
     **/
    public int removeStones(int[][] stones) {
        final int N = 1000;
        UnionFind unionFind = new UnionFind(N);
        for (int[] stone : stones) {
            unionFind.union(stone[0], stone[1] + N / 2);
        }
        Set<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            set.add(unionFind.find(stone[0]));
        }
        return stones.length - set.size();
    }


}
