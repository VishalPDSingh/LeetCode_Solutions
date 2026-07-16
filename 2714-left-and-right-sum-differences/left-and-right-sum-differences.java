class Solution {
    public int[] leftRightDifference(int[] nums) {

        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            int lsum = 0;
            for (int j = 0; j < i; j++) {
                lsum += nums[j];
            }

            int rsum = 0;
            for (int k = i + 1; k < nums.length; k++) {
                rsum += nums[k];
            }

            ans[i] = Math.abs(lsum - rsum);
        }

        return ans;
    }
}