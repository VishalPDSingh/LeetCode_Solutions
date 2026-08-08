class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // last[j] = max index in word1 to match word2[j...m-1] exactly
        int[] last = new int[m];
        int ptr = n - 1;

        for (int j = m - 1; j >= 0; j--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(j)) {
                ptr--;
            }
            last[j] = ptr;
            ptr--;
        }

        int[] res = new int[m];
        boolean changed = false;
        int w1Idx = 0;

        for (int w2Idx = 0; w2Idx < m; w2Idx++) {
            boolean matched = false;

            while (w1Idx < n) {
                boolean isSame = word1.charAt(w1Idx) == word2.charAt(w2Idx);
                
                // We can pick w1Idx if:
                // 1. Characters match OR
                // 2. We haven't used our change, AND the suffix word2[w2Idx + 1...] 
                //    can fit in word1[w1Idx + 1...]
                boolean canUseMismatch = !changed && (w2Idx == m - 1 || w1Idx + 1 <= last[w2Idx + 1]);

                if (isSame || canUseMismatch) {
                    if (!isSame) {
                        changed = true;
                    }
                    res[w2Idx] = w1Idx;
                    w1Idx++;
                    matched = true;
                    break;
                }
                w1Idx++;
            }

            if (!matched) {
                return new int[0]; // Impossible to complete the sequence
            }
        }

        return res;
    }
}