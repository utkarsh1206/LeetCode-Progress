## Problem

### 2661. First Completely Painted Row or Column

You are given a **0-indexed** integer array `arr`, and an `m x n` integer **matrix** `mat`. `arr` and `mat` both contain **all** the integers in the range `[1, m * n]`.

Go through each index `i` in `arr` starting from index `0` and paint the cell in `mat` containing the integer `arr[i]`.

Return _the smallest index `i` at which either a row or a column will be completely painted in `mat`_.

**Example 1:**\
![alt text](https://assets.leetcode.com/uploads/2023/01/18/grid1.jpg "image explanation for example 1")

> **Input:** arr = [1,3,4,2], mat = [[1,4],[2,3]]\
> **Output:** 2\
> **Explanation:** The moves are shown in order, and both the first row and second column of the matrix become fully painted at arr[2].

**Example 2:**\
![alt text](https://assets.leetcode.com/uploads/2023/01/18/grid2.jpg "image explanation for example 2")

> **Input:** arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]\
> **Output:** 3;\
> **Explanation:** The second column becomes fully painted at arr[3].

**Constraints:**

* `m == mat.length`
* `n = mat[i].length`
* `arr.length == m * n`
* `1 <= m, n <= 105`
* `1 <= m * n <= 105`
* `1 <= arr[i], mat[r][c] <= m * n`
* All the integers of `arr` are unique.
* All the integers of `mat` are unique.

## Explanation

I approached the problem by first storing all the `row`, `col` pair for a particular value in a `valuePositions` hashMap.
For eg. if the value `6` in `mat` is stored in `[1, 2]` position, then I stored in the `valuePositions` as `{6 => {1, 2}}`

I then initialized two other hashMaps `rowCount` and `colCount`. These two will be used as _frequency arrays_, and will store the count of times the particular `row` or `column` has been 'painted'.

For each index in `arr`, I checked the value of `arr[i]` in the `valuePositions` map, from where I got the `row` and `column`.

If and when the frequency of a particular `row` or `col` was equal to the length of `col` and length of `row` respectively, I returned the value of `i` which is the index of the element in `arr` when a `row` or `col` was fully painted.

> Daily Challenge 20-01-2025