class Solution {
    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftSize = 0;
        int rightSize = 0;
        long leftSum = 0;
        long rightSum = 0;
        void add(int num) {
            if (left.isEmpty() || num <= left.lastKey()) {
                insert(left, num);
                leftSize++;
                leftSum += num;
            } else {
                insert(right, num);
                rightSize++;
                rightSum += num;
            }
            balance();
        }
        void remove(int num) {
            if (left.containsKey(num)) {
                removeOne(left, num);
                leftSize--;
                leftSum -= num;
            } else {
                removeOne(right, num);
                rightSize--;
                rightSum -= num;
            }
            balance();
        }
        void balance() {
            while (leftSize > rightSize + 1) {
                int num = left.lastKey();
                removeOne(left, num);
                insert(right, num);
                leftSize--;
                rightSize++;
                leftSum -= num;
                rightSum += num;
            }
            while (leftSize < rightSize) {
                int num = right.firstKey();
                removeOne(right, num);
                insert(left, num);
                rightSize--;
                leftSize++;
                rightSum -= num;
                leftSum += num;
            }
        }
        long getCost() {
            int median = left.lastKey();
            long leftCost = (long) median * leftSize - leftSum;
            long rightCost = rightSum - (long) median * rightSize;
            return leftCost + rightCost;
        }
        void insert(TreeMap<Integer, Integer> map, int num) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        void removeOne(TreeMap<Integer, Integer> map, int num) {
            if (map.get(num) == 1) {
                map.remove(num);
            } else {
                map.put(num, map.get(num) - 1);
            }
        }
    }
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        MedianWindow window = new MedianWindow();
        for (int i = 0; i < n; i++) {
            window.add(nums[i]);
            if (i >= x) {
                window.remove(nums[i - x]);
            }
            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];
        for (int j = 0; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];
                if (i >= x && dp[j - 1][i - x] != INF) {
                    long take = dp[j - 1][i - x] + cost[i - x];
                    dp[j][i] = Math.min(dp[j][i], take);
                }
            }
        }
        return dp[k][n];
    }
}
