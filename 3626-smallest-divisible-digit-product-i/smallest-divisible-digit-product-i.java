class Solution {
    public int smallestNumber(int n, int t) {
        int num = n;
        
        // Keep incrementing num until the product of its digits is divisible by t
        while (multiple(num) % t != 0) {
            num++;
        }
        
        return num;
    }

    // Helper function to return the product of digits of a given number
    private int multiple(int n) {
        int mul = 1;
        while (n > 0) {
            int rem = n % 10;
            mul = mul * rem;
            n /= 10;
        }
        return mul;
    }
}