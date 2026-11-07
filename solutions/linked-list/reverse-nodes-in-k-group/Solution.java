class Solution {

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1) {
      return head;
    }

    // Dummy node simplifies edge cases when reversing the first k-group
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode prevGroupTail = dummy;
    ListNode curr = head;

    while (curr != null) {
      ListNode kth = getKthNode(curr, k);
      // If fewer than k nodes remain, leave them in their original order
      if (kth == null) {
        break;
      }

      ListNode nextGroupHead = kth.next;
      kth.next = null; // Temporarily isolate the current group

      ListNode reversedHead = reverse(curr);
      prevGroupTail.next = reversedHead;
      // Connect the reversed tail to the rest of the list
      curr.next = nextGroupHead;

      prevGroupTail = curr;
      curr = nextGroupHead;
    }

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

  private ListNode getKthNode(ListNode node, int k) {
    while (node != null && k > 1) {
      node = node.next;
      k--;
    }
    return node;
  }

  // Time: O(N)
  // Space: O(1)
}