package cn.knightzz.skill.uf;

/**
 * @author 王天赐
 * @title: UF
 * @projectName algorithm-codes
 * @description:
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-12-22 16:52
 */
@SuppressWarnings("all")
public class UF {

    // https://labuladong.github.io/algo/2/22/53/

    private int count;
    // 记录节点的父节点, 比如 parent[1] = 3 表示 1 的父节点是 3
    private int[] parent;

    public UF(int n) {
        this.count = n;
        // 默认自己是自己的父节点
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /* 将 p 和 q 连接 */
    public void union(int p, int q) {
        // 如果某两个节点被连通，则让其中的（任意）一个节点的根节点接到另一个节点的根节点上：
        // 1. 找到两个节点的根节点
        int rootP = find(p);
        int rootQ = find(q);

        // 如果二者相等的情况下, 说明他们在同一个颗树上
        if (rootP == rootQ) {
            return;
        }

        parent[rootQ] = rootP;

        // 连通分量减一
        // 两个分量合二为一
        count--;
    }

    /**
     * 找到节点x所在的这棵树的根节点
     *
     * @param x
     * @return
     */
    private int find(int x) {
        // 注意根节点的父节点是它本身
        if (parent[x] != x) {
            // parent 是记录节点的父节点的数组, 比如 x 的父节点是 parent[x]
            // 路径压缩, 因为实际上我们并不关心这个树是什么结构的, 我们只需要知道这个颗树的所有最终根节点都是同一个节点
            // find(parent[x]) 返回的是这棵树的根节点;, 等于说把所有节点直接连到root上
            // 另外，如果使用路径压缩技巧，那么 size 数组的平衡优化就不是特别必要了。所以你一般看到的 Union Find 算法应该是如下实现：
            parent[x] = find(parent[x]);
            x = parent[x];
        }
        return parent[x];
    }

    /* 判断 p 和 q 是否连通 */
    public boolean connected(int p, int q) {

        // 这样，如果节点 p 和 q 连通的话，它们一定拥有相同的根节点：
        int rootP = find(p);
        int rootQ = find(q);

        return rootP == rootQ;
    }

    /* 返回图中有多少个连通分量 */
    public int count() {
        return count;
    }
}
