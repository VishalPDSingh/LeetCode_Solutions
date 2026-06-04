class Solution {

    static class Pair {
        long count;
        long waviness;

        Pair(long count, long waviness) {
            this.count = count;
            this.waviness = waviness;
        }
    }

    private String s;
    private Pair[][][][][] dp;

    public int totalWaviness(int num1, int num2) {
        return (int) (calc(num2) - calc(num1 - 1));
    }

    private long calc(int n) {
        if (n < 0) return 0;

        s = String.valueOf(n);
        int len = s.length();

        dp = new Pair[len][11][11][3][2];

        return dfs(0, 10, 10, 0, 1).waviness;
    }

    private Pair dfs(int pos, int prev2, int prev1, int state, int tight) {
        if (pos == s.length()) {
            return new Pair(1, 0);
        }

        if (tight == 0 && dp[pos][prev2][prev1][state][0] != null) {
            return dp[pos][prev2][prev1][state][0];
        }

        int limit = tight == 1 ? s.charAt(pos) - '0' : 9;

        long totalCount = 0;
        long totalWaviness = 0;

        for (int d = 0; d <= limit; d++) {
            int nextTight = (tight == 1 && d == limit) ? 1 : 0;

            if (state == 0 && d == 0) {
                Pair next = dfs(pos + 1, 10, 10, 0, nextTight);
                totalCount += next.count;
                totalWaviness += next.waviness;
            } else {
                int nextState;
                int nextPrev2;
                int nextPrev1;
                long add = 0;

                if (state == 0) {
                    nextState = 1;
                    nextPrev2 = 10;
                    nextPrev1 = d;
                } else if (state == 1) {
                    nextState = 2;
                    nextPrev2 = prev1;
                    nextPrev1 = d;
                } else {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }

                    nextState = 2;
                    nextPrev2 = prev1;
                    nextPrev1 = d;
                }

                Pair next = dfs(pos + 1, nextPrev2, nextPrev1, nextState, nextTight);

                totalCount += next.count;
                totalWaviness += next.waviness + add * next.count;
            }
        }

        Pair result = new Pair(totalCount, totalWaviness);

        if (tight == 0) {
            dp[pos][prev2][prev1][state][0] = result;
        }

        return result;
    }
}