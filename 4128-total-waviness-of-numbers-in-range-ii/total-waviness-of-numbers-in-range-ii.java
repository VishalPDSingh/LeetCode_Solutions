class Solution {

    static class Pair {
        long cnt;
        long sum;

        Pair(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    private String s;
    private Pair[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long x) {
        if (x < 0) return 0;

        s = String.valueOf(x);
        int n = s.length();

        memo = new Pair[n][11][11][3][2];

        return dfs(0, 10, 10, 0, 1).sum;
    }

    private Pair dfs(int pos, int prev2, int prev1, int state, int tight) {

        if (pos == s.length()) {
            return new Pair(1, 0);
        }

        if (tight == 0 && memo[pos][prev2][prev1][state][0] != null) {
            return memo[pos][prev2][prev1][state][0];
        }

        int limit = tight == 1 ? s.charAt(pos) - '0' : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {

            int nextTight = (tight == 1 && d == limit) ? 1 : 0;

            if (state == 0 && d == 0) {
                Pair nxt = dfs(pos + 1, 10, 10, 0, nextTight);
                totalCnt += nxt.cnt;
                totalSum += nxt.sum;
                continue;
            }

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

            Pair nxt = dfs(
                pos + 1,
                nextPrev2,
                nextPrev1,
                nextState,
                nextTight
            );

            totalCnt += nxt.cnt;
            totalSum += nxt.sum + add * nxt.cnt;
        }

        Pair ans = new Pair(totalCnt, totalSum);

        if (tight == 0) {
            memo[pos][prev2][prev1][state][0] = ans;
        }

        return ans;
    }
}