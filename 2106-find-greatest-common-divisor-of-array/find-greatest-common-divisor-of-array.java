class Solution {
    public int findGCD(int[] nums) {

        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;

        // Find maximum
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
            }
        }

        // Find minimum
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minValue) {
                minValue = nums[i];
            }
        }

        // Euclid's Algorithm
        while (minValue != 0) {
            int rem = maxValue % minValue;
            maxValue = minValue;
            minValue = rem;
        }

        return maxValue;
    }
}