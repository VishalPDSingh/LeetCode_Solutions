class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int low = Math.min(minIdx, maxIdx);
        int high = Math.max(minIdx, maxIdx);

        int bothFront = high + 1;
        int bothBack = n - low;
        int bothSides = (low + 1) + (n - high);

        return Math.min(bothSides, Math.min(bothFront, bothBack));
    }
}