class Solution {
    public int[] smallerNumbersThanCurrent(int[] arr) {
         int nums[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j] && j != i) {
                    count = count + 1;
                }
            }
            nums[i] = count;
        }
        return nums;
    }
}