class Solution {
    int pf[];
    public int largestAltitude(int[] gain) {
        pf = new int[gain.length+1];
        // starting point
        int start = 0;
        pf[start] = 0;
        int maxPoint = 0;
        for(int i=1; i<pf.length; i++)
        {
            pf[i] = pf[i-1]+gain[i-1];
            if(maxPoint < pf[i])
            {
                maxPoint = pf[i];
            }
        }
        return maxPoint;
    }
}