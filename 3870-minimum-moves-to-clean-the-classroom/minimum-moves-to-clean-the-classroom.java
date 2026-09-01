import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1, startC = -1;
        List<int[]> litterList = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterList.add(new int[]{r, c});
                }
            }
        }

        int numLitter = litterList.size();
        if (numLitter == 0) {
            return 0;
        }

        // Map litter positions to unique bit indices (0 to numLitter - 1)
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < numLitter; i++) {
            int[] pos = litterList.get(i);
            litterIndex[pos[0]][pos[1]] = i;
        }

        int totalMasks = 1 << numLitter;
        int targetMask = totalMasks - 1;

        // maxEnergy[r][c][mask] stores the maximum remaining energy seen for that state
        int[][][] maxEnergy = new int[m][n][totalMasks];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }

        // Queue stores int[]{row, col, mask, current_energy}
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC, 0, energy});
        maxEnergy[startR][startC][0] = energy;

        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int remEnergy = curr[3];

                if (mask == targetMask) {
                    return moves;
                }

                // If out of energy and not on a reset area, movement is blocked
                if (remEnergy <= 0) {
                    continue;
                }

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int nEnergy = remEnergy - 1;
                    int nMask = mask;

                    // If moving into a litter cell, mark it as collected
                    if (litterIndex[nr][nc] != -1) {
                        nMask |= (1 << litterIndex[nr][nc]);
                    }

                    // If moving into a reset cell, restore energy to full capacity
                    if (classroom[nr].charAt(nc) == 'R') {
                        nEnergy = energy;
                    }

                    // Prune if reached before with greater or equal energy
                    if (nEnergy > maxEnergy[nr][nc][nMask]) {
                        maxEnergy[nr][nc][nMask] = nEnergy;
                        queue.offer(new int[]{nr, nc, nMask, nEnergy});
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}