import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        // Effective shifts needed
        k = k % totalElements;
        
        // Initialize the new shifted grid structure
        int[][] shifted = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Calculate the 1D index of the current element, shift it, and wrap around
                int new1DIndex = (i * n + j + k) % totalElements;
                
                // Convert back to 2D coordinates
                int newRow = new1DIndex / n;
                int newCol = new1DIndex % n;
                
                shifted[newRow][newCol] = grid[i][j];
            }
        }
        
        // Convert the 2D array back into the List<List<Integer>> format required by LeetCode
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(shifted[i][j]);
            }
            result.add(row);
        }
        
        return result;
    }
}