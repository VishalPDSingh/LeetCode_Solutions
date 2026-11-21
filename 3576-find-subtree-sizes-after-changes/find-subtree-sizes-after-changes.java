import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build original tree from parent array
        List<List<Integer>> origTree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            origTree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            origTree.get(parent[i]).add(i);
        }

        // Find new parent for each node simultaneously using DFS
        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        findNewParents(0, parent, origTree, s, lastSeen, newParent);

        // Build rewired tree
        List<List<Integer>> finalTree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            finalTree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            finalTree.get(newParent[i]).add(i);
        }

        // Compute subtree sizes in the final tree
        int[] answer = new int[n];
        computeSubtreeSize(0, finalTree, answer);
        return answer;
    }

    private void findNewParents(
            int u,
            int[] parent,
            List<List<Integer>> origTree,
            String s,
            int[] lastSeen,
            int[] newParent
    ) {
        int c = s.charAt(u) - 'a';
        int closestMatch = lastSeen[c];

        if (u != 0) {
            newParent[u] = (closestMatch != -1) ? closestMatch : parent[u];
        }

        lastSeen[c] = u;
        for (int v : origTree.get(u)) {
            findNewParents(v, parent, origTree, s, lastSeen, newParent);
        }
        lastSeen[c] = closestMatch;
    }

    private int computeSubtreeSize(int u, List<List<Integer>> finalTree, int[] answer) {
        int size = 1;
        for (int v : finalTree.get(u)) {
            size += computeSubtreeSize(v, finalTree, answer);
        }
        answer[u] = size;
        return size;
    }
}