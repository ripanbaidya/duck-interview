class Solution {
  
  public ListNode reverseBetween(ListNode head, int left, int right) {
    if (head == null || left == right) {
      return head;
    }

    // Dummy node handles cases where the reversal starts directly at the head
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode prev = dummy;
    ListNode curr = head;
    int pos = 1;

    // Advance to the node just before the start of the reversal
    while (pos < left) {
      prev = curr;
      curr = curr.next;
      pos++;
    }

    ListNode leftPrev = prev;
    ListNode start = curr;

    // Advance through the sublist to be reversed
    while (pos < right) {
      prev = curr;
      curr = curr.next;
      pos++;
    }

    ListNode rightNext = curr.next;
    curr.next = null; // Detach the sublist

    // Reverse the sublist and reconnect the endpoints
    leftPrev.next = reverse(start);
    start.next = rightNext;

    return dummy.next;
  }

  private ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }

  // Time: O(N)
  // Space: O(1)
}