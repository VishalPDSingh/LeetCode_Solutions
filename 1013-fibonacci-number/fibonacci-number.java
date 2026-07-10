class Solution {
    public int fib(int n) {
        
        int ans = 0;
        int a = 0;
        int b = 1;
         if(n==1)
            {
              ans = a+b;
            }
        for(int i=2; i<=n; i++)
        {
            ans = a+b;
            a = b;
            b = ans;

        }
        return ans;
    }
}