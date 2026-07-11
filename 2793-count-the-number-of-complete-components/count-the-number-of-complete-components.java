class Solution {

    List<Integer>[] graph;
    boolean[] visited;

    int vertices;
    int edges;

    public int countCompleteComponents(int n, int[][] edgesArr) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edgesArr) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        visited = new boolean[n];

        int answer = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                vertices = 0;
                edges = 0;

                dfs(i);

                edges /= 2;

                if (edges == vertices * (vertices - 1) / 2) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private void dfs(int node) {

        visited[node] = true;

        vertices++;

        edges += graph[node].size();

        for (int neighbour : graph[node]) {

            if (!visited[neighbour]) {
                dfs(neighbour);
            }
        }
    }
}