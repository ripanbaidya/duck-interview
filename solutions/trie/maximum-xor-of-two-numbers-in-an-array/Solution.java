class Solution {

  private static class Node {
    Node[] next = new Node[2];
  }

  public int findMaximumXOR(int[] nums) {
    Node root = new Node();

    // Values are non-negative and below 2^31, so bits 30 to 0 are enough
    for (int num : nums) {
      Node node = root;
      for (int bit = 30; bit >= 0; bit--) {
        int b = (num >> bit) & 1;
        if (node.next[b] == null) node.next[b] = new Node();
        node = node.next[b];
      }
    }

    int max = 0;
    for (int num : nums) {
      Node node = root;
      int xor = 0;
      for (int bit = 30; bit >= 0; bit--) {
        int b = (num >> bit) & 1;

        // Greedy: a higher bit that differs beats any gain from lower bits
        if (node.next[1 - b] != null) {
          xor |= 1 << bit;
          node = node.next[1 - b];
        } else {
          node = node.next[b];
        }
      }
      max = Math.max(max, xor);
    }
    return max;
  }

  // Time: O(31 * n)
  // Space: O(31 * n)
}