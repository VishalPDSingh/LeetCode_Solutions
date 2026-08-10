class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        // Base case: dp[0] = false (0 stones means the current player loses)
        
        for (int i = 1; i <= n; i++) {
            // Try subtracting every valid square number k*k <= i
            for (int k = 1; k * k <= i; k++) {
                if (!dp[i - k * k]) {
                    dp[i] = true; // Found a move that forces the opponent to lose
                    break;        // No need to check other square moves
                }
            }
        }
        
        return dp[n];
    }
}