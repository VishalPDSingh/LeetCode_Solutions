import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        List<Integer> missing = new ArrayList<>();
        
        // Iterate through adjacent pairs in the sorted array
        for (int i = 0; i < nums.length - 1; i++) {
            // Fill in any gaps between nums[i] and nums[i + 1]
            for (int val = nums[i] + 1; val < nums[i + 1]; val++) {
                missing.add(val);
            }
        }
        
        return missing;
    }
}