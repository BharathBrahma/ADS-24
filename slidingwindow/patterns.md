# Coding Patterns Reference Guide

This guide summarizes essential algorithmic patterns for solving medium-difficulty LeetCode problems commonly seen in Meta interviews.

---

## 1. Prefix Sum
**When to use:**
- Problems involving subarray sums, cumulative counts, or range queries.
- Often combined with a hash map to track previous sums or remainders.

**Key Idea:**
Pre-compute cumulative sums so that any subarray sum can be obtained in O(1).

**Example Problems:**
- Continuous Subarray Sum (LC 523)
- Product of Array Except Self (LC 238)

---

## 2. Two Pointers
**When to use:**
- Working with sorted arrays or strings.
- Finding pairs, moving towards the center, or shrinking/growing a window.

**Key Idea:**
Use two indices to scan the data structure from different directions.

**Example Problems:**
- Next Permutation (LC 31)
- Container With Most Water (LC 11)
- 3Sum / 3Sum Closest (LC 15 / LC 16)

---

## 3. Sliding Window
**When to use:**
- Problems requiring information about a contiguous subarray or substring.
- Optimize over all subarrays of a fixed or variable length.

**Key Idea:**
Move the window boundaries instead of recomputing from scratch.

**Example Problems:**
- Longest Substring Without Repeating Characters (LC 3)
- Minimum Window Substring (LC 76)
- Palindromic Substrings (LC 647)

---

## 4. Sorting & Interval Merging
**When to use:**
- Overlapping intervals or events on a timeline.

**Key Idea:**
Sort by start time, then merge as you scan.

**Example Problems:**
- Merge Intervals (LC 56)
- Insert Interval (LC 57)

---

## 5. Binary Search (Classic & Modified)
**When to use:**
- Sorted arrays or monotonic conditions.
- Rotated sorted arrays or “search space reduction” problems.

**Key Idea:**
At each step, decide which half of the space can be discarded.

**Example Problems:**
- Search in Rotated Sorted Array (LC 33)
- Pow(x, n) (LC 50)

---

## 6. Heap / Priority Queue
**When to use:**
- Need to repeatedly fetch the largest/smallest element.

**Key Idea:**
Maintain a heap of size k for efficient top-k computations.

**Example Problems:**
- Kth Largest Element in an Array (LC 215)
- Top K Frequent Elements (LC 347)

---

## 7. DFS / BFS
**When to use:**
- Tree traversals, graph connectivity, island counting, shortest paths.

**Key Idea:**
DFS explores as deep as possible before backtracking;  
BFS explores level by level.

**Example Problems:**
- Clone Graph (LC 133)
- Number of Islands (LC 200)
- Binary Tree Level Order Traversal (LC 102)

---

## 8. Backtracking
**When to use:**
- Explore all combinations/permutations with pruning.

**Key Idea:**
Build the solution incrementally, backtrack when constraints fail.

**Example Problems:**
- Subsets (LC 78)
- Subsets II (LC 90)
- Word Search (LC 79)

---

## 9. Dynamic Programming
**When to use:**
- Optimal substructure and overlapping subproblems.

**Key Idea:**
Define state & recurrence, solve bottom-up or memoized top-down.

**Example Problems:**
- Decode Ways (LC 91)
- Palindromic Substrings (LC 647)
