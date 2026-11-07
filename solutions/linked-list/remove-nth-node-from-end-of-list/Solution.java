class Solution {
  
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode slow = dummy;
    ListNode fast = dummy;

    // Advance fast pointer by n + 1 steps to maintain the required gap
    for (int i = 0; i <= n; i++) {
      fast = fast.next;
    }

    // Move both pointers until fast reaches the end
    while (fast != null) {
      slow = slow.next;
      fast = fast.next;
    }

    // Remove the target node
    slow.next = slow.next.next;

    return dummy.next;
  }

  // Time: O(N)
  // Space: O(1)
}