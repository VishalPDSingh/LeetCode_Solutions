class Solution {
    private static final int[] PRIMES = {2, 3, 5, 7};

    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        // Precompute prime exponents v_p(x) for x in 1..10
        int[][] exp = new int[11][PRIMES.length];
        for (int x = 1; x <= 10; x++) {
            int val = x;
            for (int idx = 0; idx < PRIMES.length; idx++) {
                int p = PRIMES[idx];
                while (val % p == 0) {
                    exp[x][idx]++;
                    val /= p;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int[] sumExp = new int[PRIMES.length];
            int[] maxExp = new int[PRIMES.length];
            int[] minExp = new int[PRIMES.length];
            java.util.Arrays.fill(minExp, Integer.MAX_VALUE);

            for (int j = i; j < n; j++) {
                int[] cur = exp[nums[j]];
                boolean valid = true;

                for (int idx = 0; idx < PRIMES.length; idx++) {
                    sumExp[idx] += cur[idx];
                    maxExp[idx] = Math.max(maxExp[idx], cur[idx]);
                    minExp[idx] = Math.min(minExp[idx], cur[idx]);

                    // prod == lcm * gcd iff sum(v_p) == max(v_p) + min(v_p) for every prime p
                    if (sumExp[idx] != maxExp[idx] + minExp[idx]) {
                        valid = false;
                    }
                }

                if (valid) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
}