class Solution {

  // Optimal
  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }

    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    ListNode secondHalf = reverse(slow);
    ListNode curr1 = head;
    ListNode curr2 = secondHalf;

    while (curr2 != null) {
      if (curr1.val != curr2.val) {
        return false;
      }
      curr1 = curr1.next;
      curr2 = curr2.next;
    }

    return true;
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