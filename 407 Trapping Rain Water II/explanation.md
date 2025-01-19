## Problem

### 407. Trapping Rain Water II

Given an `m x n` integer matrix `heightMap` representing the height of each unit cell in a 2D elevation map, return __the volume of water it can trap after raining__.

**Example 1:**
![alt text](https://assets.leetcode.com/uploads/2021/04/08/trap1-3d.jpg)

> **Input:** heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]\
> **Output:** 4\
> **Explanation:** After the rain, water is trapped between the blocks.\
> We have two small ponds 1 and 3 units trapped.\
> The total volume of water trapped is 4.\

**Example 2:**
![alt text](https://assets.leetcode.com/uploads/2021/04/08/trap2-3d.jpg)

> **Input:** heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]\
> **Output:** 10

**Constraints:**

* `m == heightMap.length`
* `n == heightMap[i].length`
* `1 <= m, n <= 200`
* `0 <= heightMap[i][j] <= 2 * 104`

## Explanation

I have created a `boolean` grid name `visited` with values false. Also initialized a priority queue of `int[]`, called `boundary` to store the boundary or outer cell heights.
Then pushed the first and last rows and colums to the `boundary` and marked them as visited.

While inside the `boundary` P-Queue, popped the top `int[]` from `boundary` and stored the values to `currentRow`,`currentCol`, `currentHeight`. This will be the cell with the minimum height in the unexplored part of the `boundary`.

Loop through all neighbors of the current cell, with `direction` from `0` to `3`:
* Initialize `neighborRow` to `currentRow + dRow[direction]` and `neighborCol` to `currentCol + dCol[direction]`.
* If the cell `(neighborRow, neighborCol)` is valid, i.e. it is not out of the bounds of the grid and not visited:
	* If the height of the cell, `neighborHeight` is lower than `currentHeight`, add the difference `currentHeight - neighborHeight` to the `totalWaterVolume`.
	* Push the neighboring cell into the `boundary` with its height set to the maximum of its value and `currentHeight`, as the lowest height of the boundary cannot fall below its current value.
	* Mark the neighboring cell as visited.

Return `totalWaterVolume`.

> Daily Challenge 19-01-2025