class Solution {
  
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode slow = head;
    ListNode fast = head.next;

    while (fast != null) {
      if (fast.val != slow.val) {
        slow.next = fast;
        slow = fast;
      }
      fast = fast.next;
    }
    // Terminate the list to remove any trailing duplicates
    slow.next = null;

    return head;
  }

  // Time: O(N)
  // Space: O(1)
}