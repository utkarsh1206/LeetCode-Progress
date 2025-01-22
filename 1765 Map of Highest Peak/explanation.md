## Problem

### 1765. Map of Highest Peak

You are given an integer matrix `isWater` of size `m x n` that represents a map of **land** and **water** cells.

* If `isWater[i][j] == 0`, cell `(i, j)` is a **land** cell.
* If `isWater[i][j] == 1`, cell `(i, j)` is a **water** cell.

You must assign each cell a height in a way that follows these rules:

* The height of each cell must be non-negative.
* If the cell is a **water** cell, its height must be `0`.
* Any two adjacent cells must have an absolute height difference of **at most** `1`. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).

Find an assignment of heights such that the maximum height in the matrix is **maximized**.

Return _an integer matrix `height` of size `m x n` where `height[i][j]` is cell `(i, j)`'s height. If there are multiple solutions, return **any** of them_.

**Example 1:**\
![alt text](https://assets.leetcode.com/uploads/2021/01/10/screenshot-2021-01-11-at-82045-am.png)
> **Input:** isWater = [[0,1],[0,0]]\
> **Output:** [[1,0],[2,1]]\
> **Explanation:** The image shows the assigned heights of each cell.\
> The blue cell is the water cell, and the green cells are the land cells.

**Example 2:**\
![Grid-2](https://assets.leetcode.com/uploads/2021/01/10/screenshot-2021-01-11-at-82050-am.png)
> **Input:** isWater = [[0,0,1],[1,0,0],[0,0,0]]\
> **Output:** [[1,1,0],[0,1,1],[1,2,2]]\
> **Explanation:** A height of 2 is the maximum possible height of any assignment.\
> Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.

**Constraints:**

* `m == isWater.length`
* `n == isWater[i].length`
* `1 <= m, n <= 1000`
* `isWater[i][j]` is `0` or `1`.
* There is at least one water cell

## Explanation

The method in which I went, uses **BFS** to store the maximum height for each cell.
First I initialized a `direction` array, which has the paths in which the algorithm can move.
Then I declared two `m x n` arrays named `result` (which should have been _height_) and `visited`. The second one will be used to mark if the cell has been visited or not.

I also initialized a _PriorityQueue_ which stores three values `currentHeight`, `currentRow` and `currentCol`.

Now I iterated through the complete `isWater` matrix, and when the cell was _water_ I stored its corresponding cell as `0` in the `result`. While also adding its cordinates to the p-queue. And also marked those cells as visited. Now if the cell was _land_, I stored its corresponding cell as `1` because that will be the minimum height of land.

Next I went through the `queue`, and polled the values in ascending order.

Took the cordinates of the neighboring cells from the help of `direction`. If the `newCell` was valid, then checked if its height is smaller or equal to the height that we got from the queue.
If `true` then marked the `result` for the `newCell` as the sum of `currentHeight` and `neighborHeight`.

Added this new alue to the `queue`, and also marked this cell as visited.

Returned the `result` matrix.

> Daily Challenge 22-01-2025