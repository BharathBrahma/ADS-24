# Sliding Window Cheat Sheet (Java) — Striver-Style

This is a concise interview-ready reference with **fixed-size** and **variable-size** templates, plus four classic LeetCode mediums with optimal reasoning and Java code.

---

## 1) Mental Model (Quick)
- Maintain a **contiguous window** `[l..r]` and update it in **O(1)** per move.
- **Fixed-size**: keep size exactly `k` (or at most `k`), update metric as you slide.
- **Variable-size**: grow `r`; while a **constraint** is violated, shrink `l` until the window is valid again.
- Track the best answer whenever the window is **valid**.

---

## 2) Templates (Java-ish Pseudocode)

### A. Fixed-size window (size = k)
```text
l = 0
for r in [0..n-1]:
  include s[r] in metric/freq
  if (r - l + 1) > k:
    exclude s[l]; l++
  if (r - l + 1) == k:
    update answer using current metric
```

Common metrics: sum, max, min, count of zeros/ones, etc.

### B. Variable-size window (maintain a constraint)
```text
l = 0
for r in [0..n-1]:
  include s[r] in freq/map
  while (window is invalid):
    exclude s[l] from freq/map; l++
  update answer  // window is valid here
```

---

## 3) Classic Problems (with Optimal Java)

### 3.1 LC 3 — Longest Substring Without Repeating Characters
**Goal:** Max length of substring with all **unique** chars.  
**Key idea:** If `s[r]` repeats **inside** window, jump `l` to **just after** its previous position.

**Java**
```java
int lengthOfLongestSubstring(String s) {
    int[] last = new int[256];
    Arrays.fill(last, -1);
    int l = 0, best = 0;
    for (int r = 0; r < s.length(); r++) {
        char c = s.charAt(r);
        if (last[c] >= l) l = last[c] + 1;  // shrink just enough
        last[c] = r;
        best = Math.max(best, r - l + 1);
    }
    return best;
}
```
**Time:** O(n), **Space:** O(Σ) (256 for ASCII).

---

### 3.2 LC 340 — Longest Substring with **At Most K Distinct** Characters
**Window valid if:** `map.size() ≤ k`.  
**Shrink while:** `map.size() > k`.

**Java**
```java
int longestKDistinct(String s, int k) {
    Map<Character, Integer> freq = new HashMap<>();
    int l = 0, best = 0;
    for (int r = 0; r < s.length(); r++) {
        char c = s.charAt(r);
        freq.put(c, freq.getOrDefault(c, 0) + 1);

        while (freq.size() > k) {
            char d = s.charAt(l++);
            int cnt = freq.get(d) - 1;
            if (cnt == 0) freq.remove(d);
            else freq.put(d, cnt);
        }
        best = Math.max(best, r - l + 1);
    }
    return best;
}
```
**Time:** O(n), **Space:** O(k).

---

### 3.3 LC 424 — Longest Repeating Character Replacement
**You may replace ≤ `k` chars** so the substring becomes a single repeated letter.  
**Validity inequality:** `(windowLen) - (maxFreq) ≤ k`.  
Keep `maxFreq` as the **max count of one char** in window; **do not** recompute on shrink.

**Java**
```java
int characterReplacement(String s, int k) {
    int[] freq = new int[26];
    int l = 0, best = 0, maxFreq = 0;

    for (int r = 0; r < s.length(); r++) {
        int idx = s.charAt(r) - 'A';
        freq[idx]++;
        maxFreq = Math.max(maxFreq, freq[idx]);

        while ((r - l + 1) - maxFreq > k) {
            freq[s.charAt(l) - 'A']--;
            l++;
        }
        best = Math.max(best, r - l + 1);
    }
    return best;
}
```
**Time:** O(n), **Space:** O(1) (26 letters).

---

### 3.4 LC 904 — Fruit Into Baskets (Longest Subarray with ≤ 2 Distinct)
**Valid while:** `map.size() ≤ 2` (two fruit types).  
On shrink: decrement left fruit; **remove key when count hits 0**.

**Java**
```java
int totalFruit(int[] fruits) {
    Map<Integer, Integer> freq = new HashMap<>();
    int l = 0, best = 0;

    for (int r = 0; r < fruits.length; r++) {
        int c = fruits[r];
        freq.put(c, freq.getOrDefault(c, 0) + 1);

        while (freq.size() > 2) {
            int d = fruits[l++];
            int cnt = freq.get(d) - 1;
            if (cnt == 0) freq.remove(d);
            else freq.put(d, cnt);
        }
        best = Math.max(best, r - l + 1);
    }
    return best;
}
```
**Time:** O(n), **Space:** O(1) (bounded by ≤3 keys during shrink).

---

## 4) O(1) Update Rules (Interview Reminders)
- **Grow (r++):** `freq[c]++`; update any scalar (e.g., `maxFreq = max(maxFreq, freq[c])`).
- **Shrink (l++):** `freq[d]--`; if it hits `0`, **remove key** (for map problems).
- **Record answer** only when window is **valid**.
- For duplicates (LC 3): **jump** `l = last[c] + 1` if the previous `c` lies inside the window.

---

## 5) Quick Practice Prompts
- “Walk me through LC 3 on input `'abba'` showing each `(l, r, last[c], best)` update.”
- “Dry-run LC 424 on `'AABABBA', k=1` and explain why we don’t recompute `maxFreq` on shrink.”
- “Prove LC 340’s correctness by stating and maintaining the invariant `map.size() ≤ k`.”
- “Explain how LC 904 reduces to ‘longest subarray with ≤ 2 distinct’ and why each index enters/exits once.”

---

### Final Tip
Keep one template per **constraint** in your head:
- **`map.size() ≤ k`** (K distinct-type problems).
- **`(windowLen - maxFreq) ≤ k`** (replacement problems).
- **`lastSeen` jump** for pure “no repeat”.

With these, most medium sliding-window questions fall quickly in interviews.
