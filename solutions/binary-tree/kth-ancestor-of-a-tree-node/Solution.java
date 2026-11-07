class TreeAncestor {

  private int[][] up;
  private int levels;

  public TreeAncestor(int n, int[] parent) {
    levels = 1;
    while ((1 << levels) <= n) levels++;

    // up[j][i] = the 2^j-th ancestor of i, or -1 if none
    up = new int[levels][n];
    up[0] = parent;
    for (int j = 1; j < levels; j++) {
      for (int i = 0; i < n; i++) {
        int mid = up[j - 1][i];
        up[j][i] = mid == -1 ? -1 : up[j - 1][mid];
      }
    }
  }

  public int getKthAncestor(int node, int k) {
    // Jump by each set bit of k
    for (int j = 0; j < levels && node != -1; j++) {
      if (((k >> j) & 1) == 1) node = up[j][node];
    }
    return node;
  }

  // Time: O(n log n) build, O(log n) per query
  // Space: O(n log n)
}

class Solution {
}