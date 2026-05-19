class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        int s = 0;
        int e = n-1;
        reverse(nums,s,e);
        reverse(nums,0,k-1);
        reverse(nums,k,e);
    }

    public void reverse(int arr[], int s, int e)
    {
        while(s<e)
        {
            int temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++;
            e--;
        }
    }
}