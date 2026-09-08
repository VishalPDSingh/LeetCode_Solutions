class Solution {
    public int countCommas(int n) {
        long commas = 0;
        long threshold = 1000L;

        while (n >= threshold) {
            commas += (n - threshold + 1);
            if (threshold > Long.MAX_VALUE / 1000) {
                break;
            }
            threshold *= 1000L;
        }

        return (int) commas;
    }
}