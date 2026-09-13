import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> points1 = new ArrayList<>();
        List<int[]> points2 = new ArrayList<>();

        // Collect positions of 1s in both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    points1.add(new int[]{r, c});
                }
                if (img2[r][c] == 1) {
                    points2.add(new int[]{r, c});
                }
            }
        }

        // Count frequencies of each displacement vector (dr, dc)
        // Since dr and dc range from -(n - 1) to (n - 1), we can map (dr, dc) into a single integer key:
        // key = (dr + 100) * 1000 + (dc + 100)
        Map<Integer, Integer> count = new HashMap<>();
        int maxOverlap = 0;

        for (int[] p1 : points1) {
            for (int[] p2 : points2) {
                int dr = p1[0] - p2[0];
                int dc = p1[1] - p2[1];
                int key = (dr + 100) * 1000 + (dc + 100);

                int currentCount = count.getOrDefault(key, 0) + 1;
                count.put(key, currentCount);
                maxOverlap = Math.max(maxOverlap, currentCount);
            }
        }

        return maxOverlap;
    }
}