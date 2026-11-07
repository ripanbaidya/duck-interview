# Monotonic Stack

A **Monotonic Stack** is a standard stack data structure with one special property: its elements are always kept in a specific order (either strictly increasing or strictly decreasing).

When you push a new element onto the stack, you must maintain this order. If the new element violates the order, you pop elements out of the stack until the order is restored, and _then_ you push the new element.

## Types of Monotonic Stacks

There are **two** primary variations of monotonic stacks, determined by the stack's internal order and how elements are processed:

1. Monotonically Increasing Stack:
   - Order (Bottom to Top): Values go from smallest to largest (e.g., `[1, 3, 4, 8]`).
   - Behavior: When a smaller (or equal) element comes in, it pops all larger elements from the stack.
   - Uses: Finding the **Next/Previous Smaller Element**.

2. Monotonically Decreasing Stack:
   - Order (Bottom to Top): Values go from largest to smallest (e.g., `[8, 4, 3, 1]`).
   - Behavior: When a larger (или equal) element comes in, it pops all smaller elements from the stack.
   - Main Use: Finding the **Next/Previous Greater Element**.

---

**Why Do We Use It ?** 

In brute-force algorithms, finding the next greater or smaller element for every element in an array takes `O(n²)` time because you use nested loops.

A **Monotonic Stack** solves these problems in `O(n)` time and `O(n)` space. Every element is pushed into the stack at most once and popped at most once, guaranteeing linear time complexity.

**Cheat Sheet**

| Problem Type               | Traversal                | Stack Order | Pop Condition                | When & How to Record Answer                                    |
| -------------------------- | ------------------------ | ----------- | ---------------------------- | -------------------------------------------------------------- |
| **Next Greater (NGE)**     | Left $\rightarrow$ Right | Decreasing  | `arr[stack.top()] <= arr[i]` | When popping: `result[stack.pop()] = arr[i]`                   |
| **Next Smaller (NSE)**     | Left $\rightarrow$ Right | Increasing  | `arr[stack.top()] >= arr[i]` | When popping: `result[stack.pop()] = arr[i]`                   |
| **Previous Greater (PGE)** | Left $\rightarrow$ Right | Decreasing  | `arr[stack.top()] <= arr[i]` | Before pushing: `result[i] = stack.top()` (if stack not empty) |
| **Previous Smaller (PSE)** | Left $\rightarrow$ Right | Increasing  | `arr[stack.top()] >= arr[i]` | Before pushing: `result[i] = stack.top()` (if stack not empty) |

