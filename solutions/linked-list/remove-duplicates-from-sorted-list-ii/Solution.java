class Solution {
  
  public ListNode deleteDuplicates(ListNode head) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode curr = head;

    while (curr != null) {
      // If a duplicate sequence is found, advance to the last duplicate
      if (curr.next != null && curr.val == curr.next.val) {
        while (curr.next != null && curr.val == curr.next.val) {
          curr = curr.next;
        }
        prev.next = curr.next;
      } else {
        prev = prev.next;
      }
      curr = curr.next;
    }

    return dummy.next;
  }

  // Time: O(N)
  // Space: O(1)
}