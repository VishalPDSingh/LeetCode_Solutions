class Solution {
    public boolean canReach(int[] start, int[] target) {

        int sc = (start[0]+start[1])%2;
        int tc = (target[0]+target[1])%2;

        return sc==tc;
    }
}