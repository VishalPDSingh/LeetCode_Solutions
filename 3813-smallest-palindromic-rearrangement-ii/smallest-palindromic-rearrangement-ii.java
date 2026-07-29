import java.util.Arrays;

class Solution {
    private static final long MAX_K = 1_000_001; // Cap to avoid overflow beyond k's limit

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int m = n / 2;
        int[] counts = new int[26];
        char midChar = 0;

        for (int i = 0; i < 26; i++) {
            counts[i] = freq[i] / 2;
            if (freq[i] % 2 != 0) {
                midChar = (char) ('a' + i);
            }
        }

        // 1. Verify if total possible permutations is less than k
        long totalPermutations = countArrangements(counts);
        if (totalPermutations < k) {
            return "";
        }

        // 2. Construct left half character by character
        StringBuilder leftHalf = new StringBuilder();

        for (int step = 0; step < m; step++) {
            for (int i = 0; i < 26; i++) {
                if (counts[i] == 0) continue;

                // Try placing character ('a' + i)
                counts[i]--;
                long arrangements = countArrangements(counts);

                if (arrangements >= k) {
                    leftHalf.append((char) ('a' + i));
                    break; // Successfully fixed this position, move to next step
                } else {
                    k -= arrangements; // Skip these arrangements
                    counts[i]++;      // Backtrack
                }
            }
        }

        // 3. Reconstruct full palindrome
        StringBuilder result = new StringBuilder(leftHalf);
        if (n % 2 != 0) {
            result.append(midChar);
        }
        result.append(new StringBuilder(leftHalf).reverse());

        return result.toString();
    }

    // Calculates valid multiset permutations: (sum(counts))! / (c1! * c2! * ... * c26!)
    private long countArrangements(int[] counts) {
        int total = 0;
        for (int c : counts) total += c;

        long res = 1;
        for (int count : counts) {
            if (count == 0) continue;
            res *= nCk(total, count);
            if (res >= MAX_K) return MAX_K; // Cap to avoid overflow
            total -= count;
        }
        return res;
    }

    // Combination formula nCk capped at MAX_K
    private long nCk(int n, int k) {
        long res = 1;
        int r = Math.min(k, n - k);
        for (int i = 1; i <= r; i++) {
            res = res * (n - i + 1) / i;
            if (res >= MAX_K) return MAX_K;
        }
        return res;
    }
}