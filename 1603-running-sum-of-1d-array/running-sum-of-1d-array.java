class Solution {
    public int[] runningSum(int[] arr) {
        int nums[] = new int[arr.length];
        int sum = 0;
        for(int i=0; i<arr.length; i++)
        {
            sum = sum+arr[i];
            nums[i] = sum;
        }
        return nums; 
    }
}