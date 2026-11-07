class Solution {
  
  public Node copyRandomList(Node head) {
    Map<Node, Node> oldToCopy = new HashMap<>();
    oldToCopy.put(null, null);

    Node curr = head;

    // Create a copy of every node and store the mapping.
    while (curr != null) {
      oldToCopy.put(curr, new Node(curr.val));
      curr = curr.next;
    }

    // Connect next and random pointers using the mapping.
    curr = head;

    while (curr != null) {
      Node copy = oldToCopy.get(curr);

      copy.next = oldToCopy.get(curr.next);
      copy.random = oldToCopy.get(curr.random);

      curr = curr.next;
    }

    return oldToCopy.get(head);
  }

  // Time: O(n)
  // Space: O(n)
}