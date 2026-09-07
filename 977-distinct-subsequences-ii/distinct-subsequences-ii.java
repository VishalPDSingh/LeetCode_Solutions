class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        // endWith[c] stores the number of distinct subsequences ending with character c
        int[] endWith = new int[26];

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            
            // Sum all currently existing subsequences, plus 1 for the character itself
            long sum = 1;
            for (int count : endWith) {
                sum = (sum + count) % MOD;
            }
            
            endWith[c] = (int) sum;
        }

        long total = 0;
        for (int count : endWith) {
            total = (total + count) % MOD;
        }

        return (int) total;
    }
}