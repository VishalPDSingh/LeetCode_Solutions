class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }
        
        int c0 = count[0];
        int c1 = count[1];
        int c2 = count[2];
        
        if (c0 % 2 == 0) {
            // Alice wins if there is at least one 1 and at least one 2
            return c1 >= 1 && c2 >= 1;
        } else {
            // Parity flips with odd number of 0s: Alice needs a sufficient imbalance
            return Math.abs(c1 - c2) > 2;
        }
    }
}