class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        
        int sum = 0;
        int n = x;
        while(x>0)
        {
            int rem = x%10;
            sum = sum+rem;
            x/=10;
        }
        if(n%sum!=0)
        {
            return -1;
        }

        return sum;
    }
}