## Problem

### 802. Find Eventual Safe States

There is a directed graph of n nodes with each node labeled from `0` to `n - 1`. The graph is represented by a **0-indexed** 2D integer array `graph` where `graph[i]` is an integer array of nodes adjacent to node `i`, meaning there is an edge from node `i` to each node in `graph[i]`.

A node is a **terminal node** if there are no outgoing edges. A node is a **safe node** if _every possible path_ starting from that node leads to a **terminal node** (or another safe node).

Return _an array containing all the **safe nodes** of the graph_. The answer should be sorted in _ascending_ order.

**Example 1:**\
![Illustration of graph](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/03/17/picture1.png "Illustration of graph")

> **Input:** graph = [[1,2],[2,3],[5],[0],[5],[],[]]\
> **Output:** [2,4,5,6]\
> **Explanation:** The given graph is shown above.\
> Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.\
> Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

**Example 2:**

> **Input:** graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]\
> **Output:** [4]\
> **Explanation:**\
> Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.

**Constraints:**

> `n == graph.length`
> `1 <= n <= 104`
> `0 <= graph[i].length <= n`
> `0 <= graph[i][j] <= n - 1`
> `graph[i]` is sorted in a strictly increasing order.
> The graph may contain self-loops.
> The number of edges in the graph will be in the range `[1, 4 * 10^4]`.

## Explanation

To solve the problem, we must first consider when a node is safe or unsafe. If we begin at any node and proceed along any path from that node, we will eventually reach either a terminal node or enter a cycle and continue to loop in it without ever reaching a terminal node.

If there is no path from the node that enters a cycle, we will always be able to reach a terminal node. As a result, such a node is a safe node and should be added to our answer array.

> The problem is reduced to finding the nodes that do not have any paths that lead to a cycle.

Intuitively, we can realize that a node is safe if all of its outgoing edges are to nodes that are also safe. This is due to the fact that if no neighbor leads to a cycle, no path from the node can either.

We know the terminal nodes are safe. As a result, nodes that solely have outgoing edges to terminal nodes are eventually safe nodes. Then we may check the nodes that have just outgoing edges to safe nodes again and keep updating until no new safe node is discovered.

The question is, how do we efficiently traverse from terminal nodes to nodes that only have outgoing edges to terminal nodes? We can reverse the edges of the graph to create a new graph with reversed edges. After we have visited all of the terminal nodes, we can use this new graph to go to the nodes that have edges to the terminal nodes in the original graph by using the reverse edges that we added.

Let's put this new graph to use now. A node is a safe node if all of its incoming edges come from previously identified safe nodes in the graph. If we erase the edges outgoing from the safe node and discover a node with no incoming edges, it is a new safe node. This gives us hints for thinking about Kahn's method, which does a topological sort by removing the edges in the exact way we want.

A topological sort or topological ordering of a directed graph is a linear ordering of its vertices such that for every directed edge `u -> v` from vertex `u` to vertex `v`, u` comes before `v` in the ordering.

In a directed acyclic graph, we can use Kahn's algorithm to get the topological ordering. Kahn’s algorithm works by keeping track of the number of incoming edges into each node (indegree). It works by repeatedly visiting the nodes with an indegree of zero and deleting all the edges associated with it leading to a decrement of indegree for the nodes whose incoming edges are deleted. This process continues until no elements with zero indegree can be found.

The advantage of using Kahn's technique is that it also aids in the discovery of graph cycles. The Kahn's method does not visit any node in a cycle. As a result, nodes with outgoing edges from nodes in the cycle (in this reversed graph) will never be visited and so will never be marked safe. Nodes with outgoing edges from these unsafe nodes will never be visited as well, and so on. Basically, every node in the original network that has a path to the cycle will never be visited by Kahn's algorithm, which is exactly what we want.

Let's perform Kahn's algorithm on a directed graph having a cycle. Here's a visual step-by-step representation of how it would work:

![img](https://leetcode.com/problems/find-eventual-safe-states/Figures/802/802-1.png)

We can see that if there is a cycle, the indegree of nodes in the cycle cannot be set to `0` due to cyclic dependency. We are unable to visit the cycle's nodes. We are also unable to visit any node with an incoming edge from any node in the cycle. Similarly, realize that any node with an incoming edge from nodes `3` or `5` would not have been visited as well.

**Algorithm**

1. Create an integer `n` equal to the length of `graph` to get the number of nodes in the given graph.
2. Create an array `indegree` of length `n` where `indegree[x]` stores the number of edges entering node `x`.
3. We create an adjacency list `adj` in which `adj[x]` contains all the nodes with an incoming edge from node `x`, i.e., neighbors of node `x`. We create this adjacency list by iterating over `graph` and adding the **reverse edges**. For a node `i` which originally has outgoing edges to nodes in `graph[i]`, we push `i` into `adj[node]` to add a reverse edge from `node` to `i`.
4. Initialize a queue of integers `q` and start a BFS algorithm moving from the leaf nodes to the parent nodes.
5. Begin the BFS traversal by pushing all of the leaf nodes (`indegree` equal to `0`) in the queue.
6. Create a boolean array `safe` of size `n` to track the safe nodes in the graph.
7. While the queue is not empty;
    - Dequeue the first `node` from the queue.
    - Mark `node` as safe.
    - For each `neighbor` (nodes that have an incoming edge from `node`) of node, we decrement `indegree[neighbor]` by `1` to delete the `node -> neighbor` edge.
    - If `indegree[neighbor] == 0`, it means that `neighbor` behaves as a leaf node, so we push `neighbor` in the queue.
8. Create an answer array `safeNodes` of size `n`. Iterate over all the nodes from `0` to `n - 1` and add all the safe nodes in `safeNodes`.
9. Return `safeNodes`.

> Daily Challenge 24-01-2025