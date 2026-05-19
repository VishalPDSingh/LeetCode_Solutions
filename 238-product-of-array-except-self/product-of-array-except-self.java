class Solution {
    public int[] productExceptSelf(int[] nums) {
        
       int preProduct[] = new int[nums.length];
       preProduct[0] = 1;

        for(int i=1; i<nums.length; i++)
        {
            preProduct[i] = preProduct[i-1]*nums[i-1];
        }
        int suffix = 1;

        for(int i=nums.length-1; i>=0; i--)
        {
            preProduct[i] = preProduct[i]*suffix;
            suffix = suffix*nums[i];
        }

        return preProduct;
    }

}