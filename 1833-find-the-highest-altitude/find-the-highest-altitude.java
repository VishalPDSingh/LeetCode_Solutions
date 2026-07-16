class Solution {
    int pf[];
    public int largestAltitude(int[] gain) {
        pf = new int[gain.length+1];
        int startpoint = 0;
        int maxValue = 0;
        pf[startpoint] = 0;

        // prefixSum
        for(int i=1; i<pf.length; i++)
        {
            pf[i] = pf[i-1]+gain[i-1];
            if(pf[i]>maxValue)
            {
                maxValue = pf[i];
            }
        }

        return maxValue;
    }
}