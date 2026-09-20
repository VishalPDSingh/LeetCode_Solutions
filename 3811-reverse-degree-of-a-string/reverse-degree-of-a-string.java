class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int reversedVal = 26 - (c - 'a');
            int position = i + 1;
            totalDegree += reversedVal * position;
        }

        return totalDegree;
    }
}