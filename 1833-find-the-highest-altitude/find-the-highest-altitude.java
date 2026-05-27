class Solution {
    int pf[];
    public int largestAltitude(int[] gain) {
        pf = new int[gain.length+1];
        int startPoint = 0;
        int maxPoint = 0;
        pf[startPoint] = 0;
        for(int i=1; i<pf.length; i++)
        {
            pf[i] = pf[i-1]+gain[i-1];
            if(pf[i]>maxPoint)
            maxPoint = pf[i];
        }
        return maxPoint;
    }
}