import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Find the maximum value in nums
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Step 1: Count frequencies of each number
        int[] cnt = new int[maxVal + 1];
        for (int num : nums) {
            cnt[num]++;
        }

        // Step 2: Count how many elements are multiples of each i
        long[] multiples = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            for (int j = i; j <= maxVal; j += i) {
                multiples[i] += cnt[j];
            }
        }

        // Step 3: Compute exact pair counts for each GCD (Inclusion-Exclusion)
        long[] gcdCounts = new long[maxVal + 1];
        for (int i = maxVal; i > 0; i--) {
            long k = multiples[i];
            long totalPairs = k * (k - 1) / 2;

            // Subtract pairs that have a strictly larger multiple of i as their GCD
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= gcdCounts[j];
            }
            gcdCounts[i] = totalPairs;
        }

        // Step 4: Create a prefix sum array of the counts
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdCounts[i];
        }

        // Step 5: Answer each query using a custom binary search (upper bound)
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = upperBound(prefixSums, queries[i]);
        }

        return ans;
    }

    // Returns the first index where prefixSums[idx] > target
    private int upperBound(long[] arr, long target) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}