import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // minLens[i] stores the minimum length of a valid subarray ending at or before index i
        int[] minLens = new int[n + 1];
        Arrays.fill(minLens, Integer.MAX_VALUE);

        // Map: prefixSum -> index (0-indexed prefix sum where 0 is at index 0)
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int prefixSum = 0;
        int minSumLengths = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            prefixSum += arr[i - 1];
            map.put(prefixSum, i);

            // Inherit the minimum length found up to index i - 1
            minLens[i] = minLens[i - 1];

            int need = prefixSum - target;
            if (map.containsKey(need)) {
                int leftIndex = map.get(need);
                int currLen = i - leftIndex;

                // Check if a non-overlapping valid subarray exists before leftIndex
                if (minLens[leftIndex] != Integer.MAX_VALUE) {
                    minSumLengths = Math.min(minSumLengths, currLen + minLens[leftIndex]);
                }

                // Update minLens[i] with the shortest valid subarray ending at or before i
                minLens[i] = Math.min(minLens[i], currLen);
            }
        }

        return minSumLengths == Integer.MAX_VALUE ? -1 : minSumLengths;
    }
}