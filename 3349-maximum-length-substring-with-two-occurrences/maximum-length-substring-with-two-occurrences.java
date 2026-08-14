import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maximumLengthSubstring(String s) {
        Map<Character, Integer> count = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char rChar = s.charAt(right);
            count.put(rChar, count.getOrDefault(rChar, 0) + 1);

            while (count.get(rChar) > 2) {
                char lChar = s.charAt(left);
                count.put(lChar, count.get(lChar) - 1);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
