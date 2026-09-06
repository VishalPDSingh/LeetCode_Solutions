class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length();
        int n = s.length();

        if (m > n) {
            return 0;
        }

        // dp[j] represents the number of ways to form t[0...j-1]
        int[] dp = new int[m + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            char sc = s.charAt(i);
            for (int j = m; j >= 1; j--) {
                if (sc == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[m];
    }
}