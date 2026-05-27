class Solution {
    public int subarraySum(int[] nums, int k) {
        // Step 1: Generate the prefix sum array
        int pf[] = prefixSum(nums);
        int count = 0;
        
        // i is the starting point of the subarray
        for (int i = 0; i < nums.length; i++) {
            // j is the ending point of the subarray
            for (int j = i; j < nums.length; j++) {
                
                // Calculate subarray sum from i to j using prefix sums
                int sum = 0;
                if (i == 0) {
                    sum = pf[j];
                } else {
                    sum = pf[j] - pf[i - 1];
                }
                
                // If it matches our target k, increment count
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Your helper method (Fixed the return type to int[])
    static int[] prefixSum(int arr[]) {
        int pf[] = new int[arr.length];
        pf[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pf[i] = pf[i - 1] + arr[i];
        }
        return pf;
    }
}