class Solution {
  
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode mid = findMiddle(head);
    ListNode rightHead = mid.next;
    mid.next = null;

    ListNode leftSorted = sortList(head);
    ListNode rightSorted = sortList(rightHead);

    return merge(leftSorted, rightSorted);
  }

  private ListNode findMiddle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head.next;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  private ListNode merge(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(-1);
    ListNode curr = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        curr.next = l1;
        l1 = l1.next;
      } else {
        curr.next = l2;
        l2 = l2.next;
      }
      curr = curr.next;
    }

    curr.next = (l1 != null) ? l1 : l2;

    return dummy.next;
  }

  // Time: O(N log N)
  // Space: O(log N) due to the recursive call stack
}