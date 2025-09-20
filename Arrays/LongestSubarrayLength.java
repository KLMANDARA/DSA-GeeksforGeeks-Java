import java.util.*;

class Solution {
    public static int longestSubarray(int[] arr) {
        int n = arr.length;
        // positions for values 1..n (values > n can never be in a valid subarray)
        ArrayList<Integer>[] pos = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) pos[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] <= n) pos[arr[i]].add(i);
        }

        boolean[] active = new boolean[n];
        int[] parent = new int[n];
        int[] sz = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sz[i] = 0; // size meaningful only when active[i] == true
        }

        // DSU helpers
        class DSU {
            int find(int x) {
                if (parent[x] == x) return x;
                parent[x] = find(parent[x]);
                return parent[x];
            }
            void unite(int a, int b) {
                int ra = find(a), rb = find(b);
                if (ra == rb) return;
                if (sz[ra] < sz[rb]) { int t = ra; ra = rb; rb = t; }
                parent[rb] = ra;
                sz[ra] += sz[rb];
            }
        }
        DSU dsu = new DSU();

        int ans = 0;
        // activate indices in increasing value
        for (int v = 1; v <= n; v++) {
            for (int p : pos[v]) {
                active[p] = true;
                parent[p] = p;
                sz[p] = 1;
                // merge with left/right if active
                if (p - 1 >= 0 && active[p - 1]) dsu.unite(p, p - 1);
                if (p + 1 < n && active[p + 1]) dsu.unite(p, p + 1);
                int root = dsu.find(p);
                int compSize = sz[root];
                if (compSize >= v) ans = Math.max(ans, compSize);
            }
        }
        return ans;
    }
}
