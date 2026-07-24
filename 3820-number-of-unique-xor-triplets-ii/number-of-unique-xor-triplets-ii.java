import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Step 1: Remove duplicates from input to minimize operations
        Set<Integer> uniqueSet = new HashSet<>();
        for (int num : nums) {
            uniqueSet.add(num);
        }
        
        int[] uNums = new int[uniqueSet.size()];
        int index = 0;
        for (int num : uniqueSet) {
            uNums[index++] = num;
        }

        // Maximum possible XOR value for numbers <= 1500 is < 2048 (2^11)
        boolean[] pairXor = new boolean[2048];
        boolean[] tripletXor = new boolean[2048];

        // Step 2: Find all unique XOR values from pairs (x ^ y)
        int[] pairs = new int[2048];
        int pairCount = 0;

        for (int i = 0; i < uNums.length; i++) {
            for (int j = i; j < uNums.length; j++) {
                int val = uNums[i] ^ uNums[j];
                if (!pairXor[val]) {
                    pairXor[val] = true;
                    pairs[pairCount++] = val;
                }
            }
        }

        // Step 3: Combine pair XORs with the 3rd element to get triplet XORs
        int uniqueCount = 0;
        for (int i = 0; i < pairCount; i++) {
            int p = pairs[i];
            for (int num : uNums) {
                int val = p ^ num;
                if (!tripletXor[val]) {
                    tripletXor[val] = true;
                    uniqueCount++;
                }
            }
        }

        return uniqueCount;
    }
}