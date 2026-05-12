class Solution {
    public int[] runningSum(int[] arr) {
       int pf[] = new int[arr.length];
       for(int i=0; i<arr.length; i++)
       {
        int sum = 0;
        for(int j=0; j<=i; j++)
        {
            sum = sum+arr[j];
        }
        pf[i] = sum;
       }
       return pf; 
    }
}