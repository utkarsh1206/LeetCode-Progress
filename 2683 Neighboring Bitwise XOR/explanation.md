## Problem

### 2683. Neighboring Bitwise XOR

A **0-indexed** array `derived` with length `n` is derived by computing the **bitwise XOR** (⊕) of adjacent values in a **binary array** `original` of length `n`.

Specifically, for each index `i` in the range `[0, n - 1]`:

* If `i = n - 1`, then `derived[i] = original[i] ⊕ original[0]`.
* Otherwise, `derived[i] = original[i] ⊕ original[i + 1]`.
Given an array `derived`, your task is to determine whether there exists a **valid binary array** `original` that could have formed `derived`.

*Return **true** if such an array exists or **false** otherwise.*

* A binary array is an array containing only **0's** and **1's**

**Example 1:**

> **Input:** derived = [1,1,0]
> **Output:** true
> **Explanation:** A valid original array that gives derived is [0,1,0].
> derived[0] = original[0] ⊕ original[1] = 0 ⊕ 1 = 1
> derived[1] = original[1] ⊕ original[2] = 1 ⊕ 0 = 1
> derived[2] = original[2] ⊕ original[0] = 0 ⊕ 0 = 0

**Example 2:**

> **Input:** derived = [1,1]
> **Output:** true
> **Explanation:** A valid original array that gives derived is [0,1].
> derived[0] = original[0] ⊕ original[1] = 1
> derived[1] = original[1] ⊕ original[0] = 1

**Example 3:**

> **Input:** derived = [1,0]
> **Output:** false
> **Explanation:** There is no valid original array that gives derived.

**Constraints:**

* `n == derived.length`
* `1 <= n <= 105`
* The values in `derived` are either **0's** or **1's**

## Explanation

Let us consider our `original` to be `[a, b, c]`
In this case to satisfy the given problem, our `derived` should then be `[(a ⊕ b), (b ⊕ c), (c ⊕ a)]`

Now, XOR is both commutative and associative. This means the order in which we apply XOR doesn’t affect the result.
* `a ⊕ a = 0` for any integer `a`.
* `a ⊕ 0 = a` for any integer `a`.

We can see that in our `derived` (which satisfies the problem,) each element occurs twice. On taking the XOR of each element of `derived`, we get `0`.

> `(a ⊕ b) ⊕ (b ⊕ c) ⊕ (c ⊕ a)`
> `(a ⊕ a) ⊕ (b ⊕ b) ⊕ (c ⊕ c)`
> `(0) ⊕ (0) ⊕ (0)`
> `0`

Therefore if the XOR of the `derived` is 0, then there exists and `original`, and vice-versa.

The core idea is that if the XOR of all elements in the `derived` array is zero, then a valid binary `original` array exists. This is because:
* If the XOR of all elements is zero, the system of equations defined by the XOR relations of adjacent `original` values can be solved in a way that the `original` array is valid.
* If the XOR of all elements is non-zero, it's impossible to find such an `original` array, and we return `false`.

We initialize a variable `result` to 0. This variable will be used to store the cumulative XOR of all elements in the `derived` array.

The `for` loop iterates over each element in the `derived` array, and for each element `derived[i]`, it applies the XOR operation on `result`:
* `result ^= derived[i]` means that `result` will be XORed with `derived[i]`.

After the loop finishes, we check if the XOR of all elements in the `derived` array (`result`) is equal to `0`. If `result == 0`, it means that a valid original array exists, and we return `true`. Otherwise, we return `false`.

> Daily Challange 17-01-2025