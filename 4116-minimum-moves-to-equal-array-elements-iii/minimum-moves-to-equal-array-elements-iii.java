class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        int sum = 0;
        
        // Find the maximum value and the sum of all elements
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            sum += num;
        }
        
        // Total moves = (maxVal * n) - sum
        return maxVal * n - sum;
    }
}