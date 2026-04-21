class Solution {
    public int[] runningSum(int[] nums) {

       int arr[] = new int[nums.length];
       int sum = 0;
       for(int i=0; i<arr.length; i++)
       {
        sum = sum+nums[i];
        arr[i] = sum;
       } 
       return arr;
    }
}