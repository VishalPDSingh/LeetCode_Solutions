import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Store reserved seats using bitmask
        for (int[] seat : reservedSeats) {

            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }

        int answer = (n - map.size()) * 2;

        // Masks for:
        // Left  = seats 2,3,4,5
        // Middle = seats 4,5,6,7
        // Right  = seats 6,7,8,9

        int left = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int right = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        int middle = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);

        for (int reserved : map.values()) {

            boolean leftFree = (reserved & left) == 0;
            boolean rightFree = (reserved & right) == 0;

            if (leftFree && rightFree) {
                // Can place two groups
                answer += 2;
            }
            else if (leftFree || rightFree) {
                // Can place one group
                answer += 1;
            }
            else if ((reserved & middle) == 0) {
                // Left and right unavailable,
                // but middle block is available
                answer += 1;
            }
        }

        return answer;
    }
}