class Solution {
    public int maximumWealth(int[][] arr) {
        int maxWealth = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = 0; j < arr[0].length; j++) {
                sum = sum + arr[i][j];
            }
            if (maxWealth < sum)
                maxWealth = sum;
        }
        return maxWealth;
    }
}