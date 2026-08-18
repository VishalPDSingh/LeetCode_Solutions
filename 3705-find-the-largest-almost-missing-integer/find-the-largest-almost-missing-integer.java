import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Generate every subarray of size k
        for (int i = 0; i <= nums.length - k; i++) {

            HashSet<Integer> set = new HashSet<>();

            // Elements inside current window
            for (int j = i; j < i + k; j++) {
                set.add(nums[j]);
            }

            // Count this window only once for each element
            for (int num : set) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        int answer = -1;

        // Find the largest element appearing in exactly one window
        for (int num : map.keySet()) {
            if (map.get(num) == 1) {
                answer = Math.max(answer, num);
            }
        }

        return answer;
    }
}