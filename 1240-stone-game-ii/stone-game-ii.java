class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // Compute Suffix Sums: suffixSum[i] is sum of piles[i...n-1]
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // DP table initialized to 0
        // M can range up to n
        int[][] dp = new int[n][n + 1];

        // Fill DP table backwards starting from last pile
        for (int i = n - 1; i >= 0; i--) {
            for (int M = 1; M <= n; M++) {
                // Base case: If player can take all remaining piles
                if (i + 2 * M >= n) {
                    dp[i][M] = suffixSum[i];
                } else {
                    // Try all choices of X from 1 to 2 * M
                    int maxStones = 0;
                    for (int X = 1; X <= 2 * M; X++) {
                        int nextM = Math.max(M, X);
                        int opponentStones = dp[i + X][nextM];
                        int myStones = suffixSum[i] - opponentStones;
                        maxStones = Math.max(maxStones, myStones);
                    }
                    dp[i][M] = maxStones;
                }
            }
        }

        return dp[0][1];
    }
}