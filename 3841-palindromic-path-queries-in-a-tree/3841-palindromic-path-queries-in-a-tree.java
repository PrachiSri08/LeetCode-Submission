class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int log = 1;
        while ((1 << log) <= n) {
            log++;
        }
        int[][] parent = new int[log][n];
        int[] depth = new int[n];
        int[] start = new int[n];
        int[] end = new int[n];
        int[] stack = new int[2 * n + 5];
        int[] stackParent = new int[2 * n + 5];
        boolean[] visitedExit = new boolean[2 * n + 5];
        int top = 0;
        int timer = 0;
        stack[top] = 0;
        stackParent[top] = 0;
        visitedExit[top] = false;
        while (top >= 0) {
            int node = stack[top];
            int par = stackParent[top];
            boolean exiting = visitedExit[top];
            top--;
            if (exiting) {
                end[node] = timer;
                continue;
            }
            start[node] = ++timer;
            parent[0][node] = par;
            for (int j = 1; j < log; j++) {
                parent[j][node] =
                    parent[j - 1][parent[j - 1][node]];
            }
            top++;
            stack[top] = node;
            stackParent[top] = par;
            visitedExit[top] = true;

            for (int child : graph[node]) {
                if (child == par) {
                    continue;
                }
                depth[child] = depth[node] + 1;
                top++;
                stack[top] = child;
                stackParent[top] = node;
                visitedExit[top] = false;
            }
        }
        int[] bit = new int[n + 2];
        char[] letters = s.toCharArray();
        for (int node = 0; node < n; node++) {
            int mask = 1 << (letters[node] - 'a');
            updateRange(bit, start[node], end[node], mask);
        }
        List<Boolean> answer = new ArrayList<>();
        for (String query : queries) {
            String[] parts = query.split(" ");
            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                int oldMask = 1 << (letters[node] - 'a');
                int newMask = 1 << (newChar - 'a');
                if (oldMask != newMask) {
                    updateRange(bit, start[node], end[node],
                                oldMask ^ newMask);
                    letters[node] = newChar;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int common = findLCA(u, v, depth, parent, log);
                int mask = getPrefix(bit, start[u])
                         ^ getPrefix(bit, start[v])
                         ^ (1 << (letters[common] - 'a'));
                answer.add((mask & (mask - 1)) == 0);
            }
        }
        return answer;
    }
    private void updateRange(int[] bit, int left, int right, int mask) {
        update(bit, left, mask);
        update(bit, right + 1, mask);
    }
    private void update(int[] bit, int index, int mask) {
        while (index < bit.length) {
            bit[index] ^= mask;
            index += index & -index;
        }
    }
    private int getPrefix(int[] bit, int index) {
        int mask = 0;
        while (index > 0) {
            mask ^= bit[index];
            index -= index & -index;
        }
        return mask;
    }
    private int findLCA(int u, int v, int[] depth,
                        int[][] parent, int log) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int difference = depth[u] - depth[v];
        for (int j = 0; j < log; j++) {
            if ((difference & (1 << j)) != 0) {
                u = parent[j][u];
            }
        }
        if (u == v) {
            return u;
        }
        for (int j = log - 1; j >= 0; j--) {
            if (parent[j][u] != parent[j][v]) {
                u = parent[j][u];
                v = parent[j][v];
            }
        }
        return parent[0][u];
    }
}
