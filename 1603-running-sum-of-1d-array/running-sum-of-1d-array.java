class Solution {
    public int[] runningSum(int[] arr) {
      int pf[] = new int[arr.length];
        pf[0] = arr[0];
        for(int i=1; i<arr.length; i++)
        {
            pf[i] = pf[i-1] + arr[i];
        }
        return pf;
    }
}