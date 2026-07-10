class Solution {
    public int subtractProductAndSum(int n) {
        
        int mul = 1;
        int add = 0;
        while(n>0)
        {
            int rem = n%10;
            mul = mul*rem;
            add = add+rem;
            n/=10;
        }
        return (mul-add);
    }
}