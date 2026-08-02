class Solution {
    public boolean stoneGame(int[] nums) {
        int n = nums.length;
        
        // dp[j] stores the maximum score advantage for subarray [i, j]
        int[] dp = new int[n];

        // Process starting index i from right to left
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = nums[i]; // Base case: single element subarray [i, i]
            
            for (int j = i + 1; j < n; j++) {
                // dp[j] on the right side of = is the old dp[j] (i.e., dp[i+1][j])
                // dp[j-1] is the updated value for the current row (i.e., dp[i][j-1])
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }

        // If score difference for full array [0, n-1] is >= 0, Player 1 wins
        return dp[n - 1] >= 0;
    }
}