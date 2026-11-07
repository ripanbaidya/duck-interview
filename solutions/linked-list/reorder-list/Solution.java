class Solution {

  public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }

    // Find the middle of the list.
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // Reverse the second half.
    ListNode second = slow.next;
    slow.next = null;

    ListNode revHead = reverseList(second);

    // Merge both halves alternately.
    ListNode curr = head;

    while (revHead != null) {
      ListNode currNext = curr.next;
      ListNode revNext = revHead.next;

      curr.next = revHead;
      revHead.next = currNext;

      curr = currNext;
      revHead = revNext;
    }
  }

  private ListNode reverseList(ListNode head) {
    ListNode prev = null;

    while (head != null) {
      ListNode next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }

    return prev;
  }

  // Time: O(n)
  // Space: O(1)
}