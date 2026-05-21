class Solution {
  
    public int largestAltitude(int[] gain) {
      // staring point
      int s = 0;
      int maxPoint = 0;
      for(int i=0; i<gain.length; i++)
      {
        s = s+gain[i];
        maxPoint = Math.max(maxPoint,s);
      }
      return maxPoint;
    }
}