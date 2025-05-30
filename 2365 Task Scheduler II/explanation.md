## Problem

### 2365. Task Scheduler II

###### Medium

You are given a **0-indexed array** of positive integers `tasks`, representing tasks that need to be completed **in order**, where `tasks[i]` represents the type of the i^th^ task.

You are also given a positive integer `space`, which represents the **minimum** number of days that must pass **after** the completion of a task before another task of the **same** type can be performed.

Each day, until all tasks have been completed, you must either:

* Complete the next task from `tasks`, or
* Take a break.

Return *the **minimum** number of days needed to complete all tasks.*

**Example 1:**

> **Input**: tasks = [1,2,1,2,3,1], space = 3\
> **Output**: 9\
> **Explanation**:\
> One way to complete all tasks in 9 days is as follows:\
> Day 1: Complete the 0th task.\
> Day 2: Complete the 1st task.\
> Day 3: Take a break.\
> Day 4: Take a break.\
> Day 5: Complete the 2nd task.\
> Day 6: Complete the 3rd task.\
> Day 7: Take a break.\
> Day 8: Complete the 4th task.\
> Day 9: Complete the 5th task.\
> It can be shown that the tasks cannot be completed in less than 9 days.

**Example 2:**

> **Input**: tasks = [5,8,8,5], space = 2\
> **Output**: 6\
> **Explanation**:\
> One way to complete all tasks in 6 days is as follows:\
> Day 1: Complete the 0th task.\
> Day 2: Complete the 1st task.\
> Day 3: Take a break.\
> Day 4: Take a break.\
> Day 5: Complete the 2nd task.\
> Day 6: Complete the 3rd task.\
> It can be shown that the tasks cannot be completed in less than 6 days.
 
**Constraints:**

* 1 <= tasks.length <= 105
* 1 <= tasks[i] <= 109
* 1 <= space <= tasks.length

## Explanation

The goal of this problem is to schedule tasks in the order they're given, but with a rule: we need to leave at least `space` days between any two executions of the same type of task. On any day, we can either do a task or take a break. The objective is to find the **minimum number of days** required to complete all the tasks.

Let’s walk through the approach taken in the code:

----

**Basic Idea**

We go through each task one by one and simulate the days as we go. For every task:

* If it’s a **new** task type (one that hasn’t occurred before), we can execute it **immediately**.

* If the task has been done **before**, we need to make sure we wait at least `space` days from the **last time** we did that same task. If we haven’t waited enough days, we simulate "taking a break" until we reach the valid day.

To track when we last did a certain task, we use a `HashMap`. The key is the task type, and the value is the `last day` it was executed.

----

**Step-by-Step Breakdown**

1. Initialize variables:
    * `count` keeps track of the current day (starts from 0 and goes up with each task or break).
    * `hm` (the HashMap) maps each task type to the **last day** it was executed.

2. Loop through each task:
    * If the task hasn’t been seen before:
        * Increment the day count (`count++`).
        * Store this task and its execution day in the map.
    * If the task has been seen before:
        * Retrieve the last day it was executed from the map.
        * Calculate the **next valid day** we can do this task again: `last + space + 1`.
        * If the current day (`count`) is **before** this valid day, we "wait" until that day by updating `count = expected`.
        * Otherwise, if we’re already past that day, just proceed with the task (`count++`).
        * Update the task’s last execution day in the map.

3. **Finally**, return the total days (`count`), which represents the minimum days needed to complete all tasks.

----

**Why This Works**

This approach mimics the scheduling process day by day. By using the map to remember when we last did each task, we ensure that the spacing condition is always respected. We only take breaks when we’re forced to by the space rule — otherwise, we keep moving forward with the tasks.

The `count` variable is key here: it acts like a clock, ticking forward either because we did a task or because we had to wait.

**Time and Space Complexity**

* **Time Complexity**: `O(n)`, where n is the number of tasks. We go through the list once and do constant-time operations for each task.

* **Space Complexity**: `O(k)`, where k is the number of `unique task types`. That’s the size of the map we maintain.

**Summary**

This solution is efficient, simple, and clean. It solves the problem by simulating days and making sure we always respect the gap (`space`) between repeating task types, without overcomplicating the logic.

> Solved on 30-05-2025