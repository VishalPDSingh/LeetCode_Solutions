import java.util.Arrays;

class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        
        // Option 1: Product of 3 largest
        int option1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        
        // Option 2: Product of 2 smallest (negative) * 1 largest
        int option2 = nums[0] * nums[1] * nums[n - 1];
        
        return Math.max(option1, option2);
    }
}