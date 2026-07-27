class Solution {

    public int reverse(int num) {
        int rev = 0;

        while (num > 0) {
            int rem = num % 10;
            rev = rev * 10 + rem;
            num /= 10;
        }

        return rev;
    }

    public boolean isSameAfterReversals(int num) {
        int reversed1 = reverse(num);
        int reversed2 = reverse(reversed1);

        return reversed2 == num;
    }
}