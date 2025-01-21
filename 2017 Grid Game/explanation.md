## Problem

### 2017. Grid Game

You are given a **0-indexed** 2D array `grid` of size `2 x n`, where `grid[r][c]` represents the number of points at position `(r, c)` on the matrix. Two robots are playing a game on this matrix.

Both robots initially start at `(0, 0)` and want to reach `(1, n-1)`. Each robot may only move to the **right** (`(r, c)` to `(r, c + 1)`) or **down** (`(r, c)` to `(r + 1, c)`).

At the start of the game, the **first** robot moves from `(0, 0)` to `(1, n-1)`, collecting all the points from the cells on its path. For all cells `(r, c)` traversed on the path, `grid[r][c]` is set to `0`. Then, the **second** robot moves from `(0, 0)` to `(1, n-1)`, collecting the points on its path. Note that their paths may intersect with one another.

The **first** robot wants to **minimize** the number of points collected by the **second** robot. In contrast, the **second** robot wants to **maximize** the number of points it collects. If both robots play **optimally**, return _the **number of points** collected by the **second** robot_.

**Example 1:**\
![alt text](https://assets.leetcode.com/uploads/2021/09/08/a1.png)

> **Input:** grid = [[2,5,4],[1,5,1]]\
> **Output:** 4\
> **Explanation:** The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.\
> The cells visited by the first robot are set to 0.\
> The second robot will collect 0 + 0 + 4 + 0 = 4 points.

**Example 2:**\
![alt text](https://assets.leetcode.com/uploads/2021/09/08/a2.png)

> **Input:** grid = [[3,3,1],[8,5,2]]\
> **Output:** 4\
> **Explanation:** The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.\
> The cells visited by the first robot are set to 0.\
> The second robot will collect 0 + 3 + 1 + 0 = 4 points.

**Example 3:**\
![alt text](https://assets.leetcode.com/uploads/2021/09/08/a3.png)

> **Input:** grid = [[1,3,1,15],[1,3,3,1]]\
> **Output:** 7\
> **Explanation:** The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.\
> The cells visited by the first robot are set to 0.\
> The second robot will collect 0 + 1 + 3 + 3 + 0 = 7 points.

**Constraints:**

* `grid.length == 2`
* `n == grid[r].length`
* `1 <= n <= 5 * 104`
* `1 <= grid[r][c] <= 105`

## Explanation

In this problem I noticed one thing, that because the robots can move only in two directions, i.e. _down_ and _right_, the robots will switch rows only once. So the values after first robot will be of the type

> x | 0 1 2 3 4 5 6 7\
> --|----------------\
> 1 | 0 0 0 0 V V V V\
> 2 | V V V 0 0 0 0 0

So if I calculate the minimum of the maximum sum of the possible values, I'll get my answer.

For this problem my first approach was to iterate over each column at index `i`. Take values `prefix1` and `prefix2` for each pass.
Then I ran a loop for `j = i + 1` to `j = n`. Which gave me the sum of all the `v`s in `row1` and stored it as `prefix1`.
And another loop for `j = 0` to `j = i`. Which gave me the sum of all the `v`s in `row2`, and stored the sum as `prefix2`.
Now for each loop `i` i took the `maximum` of `prefix1`, and `prefix2`. Because blue robot has to score the _maximum_ possible.
After that I compared it with the overall `minimum`. Because the objective for the red robot is to make the blue robot score the minimum.
And this `minimum` is the required output.

But the above code gave me **TLE Error**, as the time complexity was `O(n^2)`, and constraints were too big.

So to reduce the time complexity, I went with the same overall logic, but instead of running nested loop, I tried to solve it in single for loops.

First I calculated the total sum of the `grid[0]`, and stored it in `totalRow1`.
Then I took two variable named `pointsRow1` and `pointsRow2`, which will be used to calculate the sum of values.

Now the `score` of `row1` will be `totalRow1 - (sum of values till i-th index in row 1)`.
And the `score` of `row2` will be `(sum of values till i-th index in row 2 - grid[1][i])`

Again I took the maximum of `scoreRow1`, and `scoreRow2`. And compared it with the overall `minimum`.
Making it the required output.

> Daily Challenge 21-01-2025