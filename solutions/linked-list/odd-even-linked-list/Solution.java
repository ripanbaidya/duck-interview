class Solution {
  
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode odd = head;
    ListNode even = head.next;
    ListNode evenHead = even;

    // Separate odd and even indexed nodes while preserving their order.
    while (even != null && even.next != null) {
      odd.next = even.next;
      odd = odd.next;

      even.next = odd.next;
      even = even.next;
    }

    // Append the even-indexed list after the odd-indexed list.
    odd.next = evenHead;

    return head;
  }

  // Time: O(n)
  // Space: O(1)
}