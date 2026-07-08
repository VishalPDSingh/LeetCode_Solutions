import java.util.*;

class Solution {

    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> digits = new ArrayList<>();

        // Store positions and values of all non-zero digits
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                pos.add(i);
                digits.add(s.charAt(i) - '0');
            }
        }

        int k = digits.size();

        long[] prefSum = new long[k + 1];
        long[] prefNum = new long[k + 1];
        long[] pow10 = new long[k + 1];

        pow10[0] = 1;
        for (int i = 1; i <= k; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        for (int i = 1; i <= k; i++) {
            prefSum[i] = prefSum[i - 1] + digits.get(i - 1);
            prefNum[i] = (prefNum[i - 1] * 10 + digits.get(i - 1)) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            int len = right - left + 1;

            long sum = prefSum[right + 1] - prefSum[left];

            long num = (prefNum[right + 1]
                    - (prefNum[left] * pow10[len]) % MOD
                    + MOD) % MOD;

            ans[i] = (int) ((num * (sum % MOD)) % MOD);
        }

        return ans;
    }

    private int lowerBound(ArrayList<Integer> arr, int target) {
        int low = 0, high = arr.size();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr.get(mid) >= target)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    private int upperBound(ArrayList<Integer> arr, int target) {
        int low = 0, high = arr.size();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr.get(mid) > target)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }
}