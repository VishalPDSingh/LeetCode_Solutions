class Solution {

    public int pivotIndex(int[] arr) {

        // Edge case: Only one element
        if (arr.length == 1) {
            return 0;
        }

        int[] pf = prefixSum(arr);

        // Check index 0
        if (pf[arr.length - 1] - pf[0] == 0) {
            return 0;
        }

        // Check middle indices
        for (int i = 1; i < arr.length - 1; i++) {

            // Sum of elements before index i
            int leftSum = pf[i - 1];

            // Sum of elements after index i
            int rightSum = pf[arr.length - 1] - pf[i];

            if (leftSum == rightSum) {
                return i;
            }
        }

        // Check last index
        if (pf[arr.length - 2] == 0) {
            return arr.length - 1;
        }

        // No pivot index found
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