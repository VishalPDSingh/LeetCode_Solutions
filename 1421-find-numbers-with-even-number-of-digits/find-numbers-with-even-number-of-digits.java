class Solution {
    public int findNumbers(int[] arr) {
        
        int ans =0;

        for(int i=0; i<arr.length; i++)
        {
            int n = arr[i];
            int count =0;
            while(n>0)
            {
                int rem = n%10;
                count++;
                n/=10;
            }
            if(count%2==0)
            {
                ans++;
            }
        }
        return ans;
    }
}