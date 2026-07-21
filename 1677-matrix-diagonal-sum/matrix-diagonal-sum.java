class Solution {
    public int diagonalSum(int[][] mat) {
         int n = mat.length;

        int sum = 0;

        // Traverse every row.
        for (int i = 0; i < n; i++) {

            // Primary diagonal.
            sum += mat[i][i];

            // Secondary diagonal.
            if (i != n - i - 1) {

                sum += mat[i][n - i - 1];
            }
        }

        return sum;
    }
}