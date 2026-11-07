class Solution {
  
  public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
    // Find the node before index a.
    ListNode prev = list1;
    for (int i = 0; i < a - 1; i++) {
      prev = prev.next;
    }

    // Find the node after index b.
    ListNode next = prev.next;
    for (int i = a; i <= b; i++) {
      next = next.next;
    }

    // Find the tail of list2.
    ListNode tail = list2;
    while (tail.next != null) {
      tail = tail.next;
    }

    // Connect list1 -> list2 -> remaining part of list1.
    prev.next = list2;
    tail.next = next;

    return list1;
  }

  // Time: O(n + m)
  // Space: O(1)
}