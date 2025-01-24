class Solution {
    // Function to return a list of all safe nodes in a directed graph
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // Get the number of nodes in the graph
        int n = graph.length;

        // Create an array to store the in-degree of each node
        int[] indegree = new int[n];

        // Create an adjacency list to store the reverse graph
        List<List<Integer>> adj = new ArrayList<>();

        // Initialize the adjacency list
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the reverse graph and update in-degrees
        for (int i = 0; i < n; i++) {
            for (int node : graph[i]) {
                adj.get(node).add(i); // Add edge in reverse direction
                indegree[i]++; // Increment in-degree of node i
            }
        }

        // Create a queue to store all nodes with in-degree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i); // Add nodes with no incoming edges to the queue
            }
        }

        // Create a boolean array to mark safe nodes
        boolean[] safe = new boolean[n];
        while (!q.isEmpty()) {
            int node = q.poll(); // Get the next node from the queue
            safe[node] = true; // Mark the node as safe

            // Reduce the in-degree of all adjacent nodes
            for (int next : adj.get(node)) {
                indegree[next]--; // Decrement in-degree of the adjacent node
                if (indegree[next] == 0) {
                    q.add(next); // Add node to the queue if in-degree becomes 0
                }
            }
        }

        // Create a list to store all safe nodes
        List<Integer> safeNode = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (safe[i]) {
                safeNode.add(i); // Add safe nodes to the list
            }
        }
        return safeNode; // Return the list of safe nodes
    }
}
