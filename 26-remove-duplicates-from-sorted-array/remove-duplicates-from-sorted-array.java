class Solution {
    public int removeDuplicates(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int[] temp = new int[nums.length];

        int rd = 0;
        temp[rd] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (temp[rd] != nums[i]) {
                rd++;
                temp[rd] = nums[i];
            }
        }

        // Copy back into nums
        for (int i = 0; i <= rd; i++) {
            nums[i] = temp[i];
        }

        return rd + 1;
    }
}