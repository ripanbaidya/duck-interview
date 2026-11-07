class Solution {
  
  public Node flatten(Node head) {
    if (head == null) {
      return null;
    }

    Node curr = head;

    while (curr != null) {
      if (curr.child == null) {
        curr = curr.next;
        continue;
      }

      // Find the tail of the child list.
      Node tail = curr.child;

      while (tail.next != null) {
        tail = tail.next;
      }

      // Connect the child list to the remaining list.
      tail.next = curr.next;

      if (curr.next != null) {
        curr.next.prev = tail;
      }

      curr.next = curr.child;
      curr.child.prev = curr;
      curr.child = null;
    }

    return head;
  }

  // Time: O(N^2) worst case, where N is the total number of nodes.
  // Space: O(1)
}
