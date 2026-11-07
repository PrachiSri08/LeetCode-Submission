class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }
        for (int i = 0; i < m; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= i) {
                int[] server = busy.poll();
                available.offer(new int[]{server[1], server[2]});
            }
            if (!available.isEmpty()) {
                int[] server = available.poll();
                int weight = server[0];
                int index = server[1];
                ans[i] = index;
                busy.offer(new int[]{i + tasks[i], weight, index});
            } else {
                int[] server = busy.poll();
                int finishTime = server[0];
                int weight = server[1];
                int index = server[2];
                ans[i] = index;
                busy.offer(new int[]{finishTime + tasks[i], weight, index});
            }
        }
        return ans;
    }
}