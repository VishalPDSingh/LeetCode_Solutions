class Solution {

    public int pivotIndex(int[] nums) {

        int pf[] = prefixSum(nums);

        for (int i = 0; i < nums.length; i++) {

            int ls = 0;
            int rs = 0;

            if (i > 0) {
                ls = pf[i - 1];
            }

            rs = pf[nums.length - 1] - pf[i];

            if (ls == rs) {
                return i;
            }
        }

        return -1;
    }

    static int[] prefixSum(int arr[]) {

        int pf[] = new int[arr.length];

        pf[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            pf[i] = pf[i - 1] + arr[i];
        }

        return pf;
    }
}