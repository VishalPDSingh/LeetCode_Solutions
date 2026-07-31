class Solution {
    public boolean checkPerfectNumber(int num) {
        
      //  boolean isPrefect = false;
      if(num<=0)
      {
        return false;
      }
        int sum = 0;
        int n = num;
        for(int i=1; i<num; i++)
        {
            if(num%i==0)
            {
                sum = sum+i;
            }
        }
        if(sum==n)
        {
            return true;
        }
        return false;
    }
}