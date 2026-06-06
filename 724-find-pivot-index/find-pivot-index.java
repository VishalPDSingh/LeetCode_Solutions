class Solution {
    public int pivotIndex(int[] nums) {
        int[] pf = prefixSum(nums);
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int lsum = 0;
            int rsum = 0;

            if (i>0) {
                lsum=pf[i-1];
            }
            rsum = pf[n-1]-pf[i];
            if(lsum==rsum)
            return i;
        }
        return -1;
    }

    static int[] prefixSum(int[] arr) {
        int[] pf = new int[arr.length];
        pf[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            pf[i] = pf[i - 1] + arr[i];
        }

        return pf;
    }
}