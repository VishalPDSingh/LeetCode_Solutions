class Solution {
    public int findMiddleIndex(int[] nums) {
        int pf[] = prefixSum(nums);
        int n = nums.length;
        if (0 == pf[n - 1] - pf[0]) {
            return 0;
        }
        for(int i=1; i<n-1; i++)
        {
          int lSum = pf[i-1];
          int rSum = pf[n-1]-pf[i];
           if(lSum==rSum)
           {
            return i;
           }
        }
        if(0==pf[n-1]-pf[0])
        {
            return 0;
        }
        if(pf[n-2]==0)
        {
            return n-1;
        }
        return -1;
    }
    static int[] prefixSum(int arr[])
    {
        int pf[] = new int[arr.length];
        pf[0] = arr[0];
        for(int i=1; i<arr.length; i++)
        {
            pf[i] = pf[i-1] + arr[i];
        }
        return pf;
    }
}