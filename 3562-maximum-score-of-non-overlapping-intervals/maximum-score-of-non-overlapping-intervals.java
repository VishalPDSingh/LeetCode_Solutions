import java.util.*;

class Solution {
    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }

        static State empty() {
            return new State(0, new ArrayList<>());
        }

        // Compare two states: greater weight is better; if equal, lexicographically smaller indices list is better
        static State better(State a, State b) {
            if (a.weight != b.weight) {
                return a.weight > b.weight ? a : b;
            }
            // Compare lists lexicographically
            int len = Math.min(a.indices.size(), b.indices.size());
            for (int i = 0; i < len; i++) {
                if (!a.indices.get(i).equals(b.indices.get(i))) {
                    return a.indices.get(i) < b.indices.get(i) ? a : b;
                }
            }
            return a.indices.size() <= b.indices.size() ? a : b;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            List<Integer> cur = intervals.get(i);
            arr[i][0] = cur.get(0); // l
            arr[i][1] = cur.get(1); // r
            arr[i][2] = cur.get(2); // weight
            arr[i][3] = i;          // original index
        }

        // Sort by right endpoint
        Arrays.sort(arr, (a, b) -> Integer.compare(a[1], b[1]));

        // dp[i][k]: best state considering first i intervals (1-indexed) choosing at most k intervals
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = State.empty();
            }
        }

        for (int i = 1; i <= n; i++) {
            int l = arr[i - 1][0];
            int r = arr[i - 1][1];
            long w = arr[i - 1][2];
            int idx = arr[i - 1][3];

            // Binary search to find the largest index p (1-indexed) with arr[p - 1][1] < l
            int low = 1, high = i - 1, p = 0;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (arr[mid - 1][1] < l) {
                    p = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            for (int k = 1; k <= 4; k++) {
                // Option 1: Do not pick interval i
                State best = dp[i - 1][k];

                // Option 2: Pick interval i
                State prev = dp[p][k - 1];
                List<Integer> newIndices = new ArrayList<>(prev.indices);
                newIndices.add(idx);
                Collections.sort(newIndices);

                State cand = new State(prev.weight + w, newIndices);
                best = State.better(best, cand);

                dp[i][k] = best;
            }
        }

        List<Integer> resList = dp[n][4].indices;
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
}