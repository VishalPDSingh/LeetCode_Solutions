class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int num : nums1) {
            if (num < minVal) {
                minVal = num;
            }
            if ((num & 1) != 0) {
                hasOdd = true;
            }
        }

        // 1. If the minimum element is odd, we can make every element odd.
        // 2. If there are no odd numbers, all elements are already even.
        return (minVal & 1) != 0 || !hasOdd;
    }
}