class Solution {
    public int pivotIndex(int[] nums) {
        int[] pf = prefixSum(nums);
        int total = pf[nums.length - 1];

        for (int i = 0; i < nums.length; i++) {
            int ls = (i == 0) ? 0 : pf[i - 1];
            int rs = total - pf[i];

            if (ls == rs) {
                return i;
            }
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