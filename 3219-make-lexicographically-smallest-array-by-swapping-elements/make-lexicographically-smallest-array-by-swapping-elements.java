import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Pair each number with its original index
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        
        // Sort based on values
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        int i = 0;
        
        while (i < n) {
            int j = i + 1;
            // Extend the group as long as the adjacent difference is <= limit
            while (j < n && pairs[j][0] - pairs[j - 1][0] <= limit) {
                j++;
            }
            
            // Collect the original indices belonging to the current group
            int[] indices = new int[j - i];
            for (int k = i; k < j; k++) {
                indices[k - i] = pairs[k][1];
            }
            
            // Sort indices so that smaller values are placed in earlier positions
            Arrays.sort(indices);
            
            // Assign sorted values to sorted indices
            for (int k = 0; k < indices.length; k++) {
                result[indices[k]] = pairs[i + k][0];
            }
            
            i = j;
        }
        
        return result;
    }
}