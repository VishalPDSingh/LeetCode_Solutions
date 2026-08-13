class Solution {
    
    // Segment Tree Node Structure
    static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        int totalLen;

        public Node(int len) {
            this.maxLen = len;
            this.prefLen = len;
            this.suffLen = len;
            this.totalLen = len;
        }

        public Node() {}
    }

    private Node[] tree;
    private char[] str;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length; // Uses .length property for array
        this.str = s.toCharArray();
        this.tree = new Node[4 * n];

        // Build the initial segment tree
        build(1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            // Update character and tree
            str[idx] = ch;
            update(1, 0, n - 1, idx);

            // The root node (1) holds the answer for range [0, n - 1]
            result[i] = tree[1].maxLen;
        }

        return result;
    }

    // Merges left and right nodes into a parent node
    private Node merge(Node left, Node right, int midLeftIdx, int midRightIdx) {
        Node res = new Node();
        res.totalLen = left.totalLen + right.totalLen;

        // Base maxLen is the best of either child
        res.maxLen = Math.max(left.maxLen, right.maxLen);

        // If characters around the split match, combine their boundary lengths
        if (str[midLeftIdx] == str[midRightIdx]) {
            res.maxLen = Math.max(res.maxLen, left.suffLen + right.prefLen);
        }

        // Calculate Prefix Length for parent
        res.prefLen = left.prefLen;
        if (left.prefLen == left.totalLen && str[midLeftIdx] == str[midRightIdx]) {
            res.prefLen = left.totalLen + right.prefLen;
        }

        // Calculate Suffix Length for parent
        res.suffLen = right.suffLen;
        if (right.suffLen == right.totalLen && str[midLeftIdx] == str[midRightIdx]) {
            res.suffLen = right.totalLen + left.suffLen;
        }

        return res;
    }

    // Build tree recursively
    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(1);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid, mid + 1);
    }

    // Update single index recursively
    private void update(int node, int start, int end, int idx) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx);
        } else {
            update(2 * node + 1, mid + 1, end, idx);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid, mid + 1);
    }
}