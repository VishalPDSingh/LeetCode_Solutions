import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";
        
        // Parse run-length encoding for t:
        // t alternates: 1^a[0], 0^b[0], 1^a[1], 0^b[1], ..., 1^a[m-1]
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        
        int n = t.length();
        int idx = 0;
        int totalOnesInT = 0;
        
        while (idx < n) {
            // Count '1's
            int count1 = 0;
            while (idx < n && t.charAt(idx) == '1') {
                count1++;
                idx++;
            }
            a.add(count1);
            totalOnesInT += count1;
            
            // Count '0's
            if (idx < n) {
                int count0 = 0;
                while (idx < n && t.charAt(idx) == '0') {
                    count0++;
                    idx++;
                }
                b.add(count0);
            }
        }
        
        // Base case: initial active '1's in original string s (excluding augmented '1's)
        int initialOnes = totalOnesInT - 2;
        int maxActive = initialOnes;
        
        int m = a.size(); // Number of '1'-blocks
        int numZeroBlocks = b.size();
        
        if (numZeroBlocks == 0) {
            return maxActive;
        }
        
        // Track the top 3 largest '0'-block sizes and their indices
        int max1 = -1, idx1 = -1;
        int max2 = -1, idx2 = -1;
        int max3 = -1, idx3 = -1;
        
        for (int i = 0; i < numZeroBlocks; i++) {
            int val = b.get(i);
            if (val > max1) {
                max3 = max2; idx3 = idx2;
                max2 = max1; idx2 = idx1;
                max1 = val;  idx1 = i;
            } else if (val > max2) {
                max3 = max2; idx3 = idx2;
                max2 = val;  idx2 = i;
            } else if (val > max3) {
                max3 = val;  idx3 = i;
            }
        }
        
        // Consider removing each valid internal '1'-block a[i] (where 0 < i < m - 1)
        for (int i = 1; i < m - 1; i++) {
            int aVal = a.get(i);
            int leftZeroIdx = i - 1;
            int rightZeroIdx = i;
            
            // Strategy 1: Fill the merged adjacent '0'-blocks (b[i-1] + a[i] + b[i])
            int gain1 = b.get(leftZeroIdx) + b.get(rightZeroIdx);
            maxActive = Math.max(maxActive, initialOnes + gain1);
            
            // Strategy 2: Fill the best non-adjacent '0'-block b[j] where j != i-1 and j != i
            int bestOtherB = -1;
            if (idx1 != leftZeroIdx && idx1 != rightZeroIdx) {
                bestOtherB = max1;
            } else if (idx2 != leftZeroIdx && idx2 != rightZeroIdx) {
                bestOtherB = max2;
            } else if (idx3 != leftZeroIdx && idx3 != rightZeroIdx) {
                bestOtherB = max3;
            }
            
            if (bestOtherB != -1) {
                int gain2 = bestOtherB - aVal;
                maxActive = Math.max(maxActive, initialOnes + gain2);
            }
        }
        
        return maxActive;
    }
}