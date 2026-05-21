class Solution {

    public int subarraySum(int[] nums, int k) {

        int count = 0;

        int pf[] = prefixSum(nums);

        for(int i = 0; i < nums.length; i++) {

            for(int j = i; j < nums.length; j++) {

                int sum;

                if(i == 0) {
                    sum = pf[j];
                }
                else {
                    sum = pf[j] - pf[i - 1];
                }

                if(sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    static int[] prefixSum(int arr[]) {

        int pf[] = new int[arr.length];

        pf[0] = arr[0];

        for(int i = 1; i < arr.length; i++) {
            pf[i] = pf[i - 1] + arr[i];
        }

        return pf;
    }
}