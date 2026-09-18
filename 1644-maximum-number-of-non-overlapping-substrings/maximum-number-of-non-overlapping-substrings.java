import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Generate valid intervals starting at each character's first occurrence
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;

            int start = first[c];
            int end = last[c];
            boolean valid = true;

            for (int i = start; i <= end; i++) {
                int ch = s.charAt(i) - 'a';
                if (first[ch] < start) {
                    valid = false;
                    break;
                }
                end = Math.max(end, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Sort intervals by their ending positions
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> res = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {
                res.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return res;
    }
}