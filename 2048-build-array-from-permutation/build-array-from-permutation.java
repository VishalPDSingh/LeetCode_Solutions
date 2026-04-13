class Solution {
    public int[] buildArray(int[] arr) {
        
        int n = arr.length;
        int ans[] = new int[n];
        for(int i=0; i<n; i++)
        {
            ans[i] = arr[arr[i]];
        }
        return ans;
    }
}