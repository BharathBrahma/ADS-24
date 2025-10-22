## Char Digit to number within a string

``` java
int num = 0;
while (j < m && Character.isDigit(abbr.charAt(j))) {
    num = num * 10 + (abbr.charAt(j) - '0');
    j++;
}
```

## Palindrome `expandFromCenter` approach

``` java
    caller func() {
        // Try every odd center
        for (int i = 0; i < n; i++) {
            if (expandFromCenter(s, i - 1, i + 1)) return true; // odd
        }
        // Try every even center
        for (int i = 0; i < n - 1; i++) {
            if (expandFromCenter(s, i, i + 1)) return true;     // even
        }
    }

    private static boolean expandFromCenter(String s, int l, int r) {
        int n = s.length();
        while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        // If we expanded to cover the entire string, it's a palindrome
        return l < 0 && r >= n;
    }
```

## Length of a linked list

``` java
for (ListNode cur = head; cur != null; cur = cur.next) len++;
```

## Find the center/middle of the linked list

``` java
 private ListNode endOfFirstHalf(ListNode head) {
        ListNode slow = head, fast = head;
        // when fast reaches end, slow is at the end of first half
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
```

## Reverse the linked list recursively

- Use a `STACK` to add and reverse it as a brute force approach
- Recursive approach
  ```java
        public static Node reverseLinkedList(Node head) {
        // Base case:
        // If the linked list is empty or has only one node,
        // return the head as it is already reversed.
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursive step:
        // Reverse the linked list starting
        // from the second node (head.next).
        Node newHead = reverseLinkedList(head.next);
        
        // Save a reference to the node following
        // the current 'head' node.
        Node front = head.next;
        
        // Make the 'front' node point to the current
        // 'head' node in the reversed order.
        front.next = head;
        
        // Break the link from the current 'head' node
        // to the 'front' node to avoid cycles.
        head.next = null;
        
        // Return the 'newHead,' which is the new
        // head of the reversed linked list.
        return newHead;
    }
  ```

## Intervals

### Canonical Merge

``` java
 Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] cur = intervals[0]; // current merged interval [start, end]
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (next[0] <= cur[1]) {
                // overlap: extend current end
                cur[1] = Math.max(cur[1], next[1]);
            } else {
                // no overlap: push current, move to next
                merged.add(cur);
                cur = next;
            }
        }
        merged.add(cur)
```

### Interview Scheduling
``` java
int eraseOverlapIntervals(int[][] a) {
    Arrays.sort(a, (x,y) -> Integer.compare(x[1], y[1]));
    int kept = 0, end = Integer.MIN_VALUE;
    for (int[] it : a) {
        if (it[0] >= end) { kept++; end = it[1]; }
    }
    return a.length - kept; // removals
}
```
 
### Meeting rooms (No of min rooms)

``` java
int minMeetingRooms(int[][] mtgs) {
    Arrays.sort(mtgs, (a,b) -> Integer.compare(a[0], b[0]));
    PriorityQueue<Integer> pq = new PriorityQueue<>(); // end times
    for (int[] m : mtgs) {
        if (!pq.isEmpty() && pq.peek() <= m[0]) pq.poll();
        pq.offer(m[1]);
    }
    return pq.size();
}
```

### Interval Intersection (two sorted lists)

``` java
int[][] intervalIntersection(int[][] A, int[][] B) {
    List<int[]> out = new ArrayList<>();
    int i=0, j=0;
    while (i<A.length && j<B.length) {
        int lo = Math.max(A[i][0], B[j][0]);
        int hi = Math.min(A[i][1], B[j][1]);
        if (lo <= hi) out.add(new int[]{lo, hi});
        if (A[i][1] < B[j][1]) i++; else j++;
    }
    return out.toArray(new int[out.size()][]);
}
```

### Classic insert interval

``` java
int[][] insert(int[][] a, int[] nw) {
    List<int[]> res = new ArrayList<>();
    int i=0, n=a.length;
    while (i<n && a[i][1] < nw[0]) res.add(a[i++]);
    while (i<n && a[i][0] <= nw[1]) { nw[0]=Math.min(nw[0],a[i][0]); nw[1]=Math.max(nw[1],a[i][1]); i++; }
    res.add(nw);
    while (i<n) res.add(a[i++]);
    return res.toArray(new int[res.size()][]);
}


```
