class Solution {
  
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    // Merge lists in pairs until only one sorted list remains.
    while (lists.length > 1) {
      List<ListNode> mergedLists = new ArrayList<>();

      for (int i = 0; i < lists.length; i += 2) {
        ListNode l1 = lists[i];

        // If the number of lists is odd, carry the last list forward.
        ListNode l2 = (i + 1 < lists.length) ? lists[i + 1] : null;

        mergedLists.add(mergeList(l1, l2));
      }

      lists = mergedLists.toArray(new ListNode[0]);
    }

    return lists[0];
  }

  private ListNode mergeList(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
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

    // Attach the remaining nodes directly.
    curr.next = (l1 != null) ? l1 : l2;

    return dummy.next;
  }

  // Time: O(N log k), where N is the total number of nodes and k is the number of lists.
  // Space: O(k) for the temporary list arrays; O(1) auxiliary space for merging.
}