class Solution {
  
  public ListNode swapPairs(ListNode head) {
    return reverseKGroup(head, 2);
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1) {
      return head;
    }

    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode prevGroupTail = dummy;
    ListNode curr = head;

    while (curr != null) {
      ListNode kth = getKthNode(curr, k);
      // If fewer than k nodes remain, leave them in place
      if (kth == null) {
        break;
      }

      ListNode nextGroupHead = kth.next;
      kth.next = null;

      ListNode reversedHead = reverse(curr);
      prevGroupTail.next = reversedHead;
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