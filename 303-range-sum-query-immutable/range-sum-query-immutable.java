class NumArray {
    int pf[];
    public NumArray(int[] nums) {
        // find the prefix sum

         pf = new int[nums.length];
        pf[0] = nums[0];
        for(int i=1; i<nums.length; i++)
        {
            pf[i] = pf[i-1]+nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
      
      if(left==0)
      {
        return pf[right];
      }
      return (pf[right]-pf[left-1]);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */