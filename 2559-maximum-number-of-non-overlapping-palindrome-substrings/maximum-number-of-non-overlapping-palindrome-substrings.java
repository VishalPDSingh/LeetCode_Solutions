class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnd = -1; // End index of the last selected palindrome

        // Helper check to verify if s[left..right] is a palindrome
        // We test odd and even centers across the string.
        for (int center = 0; center < 2 * n - 1; center++) {
            int left = center / 2;
            int right = left + (center % 2);

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                // Ensure this palindrome doesn't overlap with the last picked one
                if (left > lastEnd && (right - left + 1) >= k) {
                    count++;
                    lastEnd = right;
                    break; // Greedily commit to the first valid one ending as early as possible
                }
                left--;
                right++;
            }
        }

        return count;
    }
}