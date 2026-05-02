class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        
         // Create the traget array
        int[] target = new int[nums.length];
        int size = 0;

        for (int i = 0; i < nums.length; i++) {
            int pos = index[i];

            // shift the element right
            for (int j = size; j > pos; j--) {
                target[j] = target[j - 1];
            }

            target[pos] = nums[i];
            size++;
        }

        return target;

    }
}