 class Solution {
    public boolean hasValidPath(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if ((n + m - 1) % 2 != 0)
            return false;
        if (grid[0][0] == ')' || grid[n - 1][m - 1] == '(')
            return false;
        boolean[][][] dp = new boolean[n][m][n + m + 1];
        dp[0][0][1] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int bal = 0; bal < n + m; bal++) {
                    if (!dp[i][j][bal])
                        continue;
                    if (i + 1 < n) {
                        int nb = grid[i + 1][j] == '('
                                ? bal + 1 : bal - 1;
                        if (nb >= 0)
                            dp[i + 1][j][nb] = true;
                    }
                    if (j + 1 < m) {
                        int nb = grid[i][j + 1] == '('
                                ? bal + 1 : bal - 1;
                        if (nb >= 0)
                            dp[i][j + 1][nb] = true;
                    }
                }
            }
        }
        return dp[n - 1][m - 1][0];
    }
}