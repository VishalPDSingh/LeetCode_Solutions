import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count frequency of each letter
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Sort frequencies in ascending order
        Arrays.sort(freq);

        int totalPushes = 0;
        int distinctCount = 0;

        // Step 3: Iterate backward (from highest frequency to lowest)
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break; // No more characters left

            // Determine key position cost: 1st 8 letters cost 1, next 8 cost 2, etc.
            int cost = (distinctCount / 8) + 1;
            totalPushes += freq[i] * cost;
            distinctCount++;
        }

        return totalPushes;
    }
}