class Solution {
  
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
      return head;
    }

    int len = 1;
    ListNode tail = head;

    // Find the length and the last node.
    while (tail.next != null) {
      len++;
      tail = tail.next;
    }

    k %= len;

    if (k == 0) {
      return head;
    }

    // Connect the list into a circular linked list.
    tail.next = head;

    // Find the new tail at position (len - k).
    int steps = len - k;
    ListNode newTail = head;

    for (int i = 1; i < steps; i++) {
      newTail = newTail.next;
    }

    ListNode newHead = newTail.next;

    // Break the circular linked list.
    newTail.next = null;

    return newHead;
  }

  // Time: O(n)
  // Space: O(1)
}