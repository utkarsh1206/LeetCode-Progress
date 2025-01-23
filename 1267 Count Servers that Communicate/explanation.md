## Problem

### 1267. Count Servers that Communicate

You are given a map of a server center, represented as a `m * n` integer matrix `grid`, where `1` means that on that cell there is a server and `0` means that it is no server. Two servers are said to communicate if they are on the _same row or on the same column_.

Return the number of servers that communicate with any other server.

**Example 1:**\
![Grid 1](https://assets.leetcode.com/uploads/2019/11/14/untitled-diagram-6.jpg)

> **Input:** grid = [[1,0],[0,1]]\
> **Output:** 0\
> **Explanation:** No servers can communicate with others.

**Example 2:**\
![Grid 2](https://assets.leetcode.com/uploads/2019/11/13/untitled-diagram-4.jpg)

> **Input:** grid = [[1,0],[1,1]]\
> **Output:** 3\
> **Explanation:** All three servers can communicate with at least one other server.

**Example 3:**\
![Grid 3](https://assets.leetcode.com/uploads/2019/11/14/untitled-diagram-1-3.jpg)

> **Input:** grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]\
> **Output:** 4\
> **Explanation:** The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.

**Constraints:**

* `m == grid.length`
* `n == grid[i].length`
* `1 <= m <= 250`
* `1 <= n <= 250`
* `grid[i][j] == 0 or 1`

## Explanation

To solve this problem, I initially considered several different approaches that seemed feasible in theory but were challenging to implement in code.

After seeking some assistance, I devised a solution.

First, I needed to count the number of `servers` in each `row` and `col` and store those values. I used arrays `rowCount` and `colCount` for this task, although a HashMap could also have been used.

I iterated over the entire `grid`, and whenever a node/server was found, I incremented the `count` of that particular `row` and `col`.

With these values in hand, I made a second pass over the grid. When a server was found, I checked the `rowCount` and `colCount` for their respective indices. If the count was `greater than 1`, I incremented the value of `totalCount`.

Finally, I returned `totalCount` as my response.

> Daily Challenge 23-01-2025