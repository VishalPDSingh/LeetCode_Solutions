import java.util.*;

class Solution {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Unique sorted values
        TreeSet<Integer> set = new TreeSet<>();
        for (int x : nums) {
            set.add(x);
        }

        int m = set.size();
        int[] A = new int[m];
        int idx = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : set) {
            A[idx] = x;
            map.put(x, idx);
            idx++;
        }

        // Step 2: Determine greedy next hop for each unique value
        int[] next = new int[m];
        for (int i = 0; i < m; i++) {
            int limit = A[i] + maxDiff;

            int l = i, r = m - 1;
            while (l < r) {
                int mid = (l + r + 1) / 2;
                if (A[mid] <= limit) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            next[i] = l;
        }

        // Step 3: Binary lifting table configuration
        int LOG = 18; 
        int[][] up = new int[LOG][m];

        for (int i = 0; i < m; i++) {
            up[0][i] = next[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < m; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        // Step 4: Answer each query
        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];

            if (u == v) {
                ans[q] = 0;
                continue;
            }

            if (nums[u] == nums[v]) {
                ans[q] = 1;
                continue;
            }

            int a = nums[u];
            int b = nums[v];

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            int curr = map.get(a);
            int steps = 0;

            // Lift as far as possible while staying strictly below target value `b`
            for (int j = LOG - 1; j >= 0; j--) {
                int nxt = up[j][curr];
                if (A[nxt] < b) {
                    curr = nxt;
                    steps += (1 << j);
                }
            }

            // Take the final leap to meet or cross `b`
            curr = up[0][curr];
            steps++;

            if (A[curr] >= b) {
                ans[q] = steps;
            } else {
                ans[q] = -1;
            }
        }

        return ans;
    }
}