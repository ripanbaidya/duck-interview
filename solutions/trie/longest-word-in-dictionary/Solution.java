class Solution {

  private static class Node {
    Node[] next = new Node[26];
    String word;
  }

  private String best = "";

  public String longestWord(String[] words) {
    Node root = new Node();
    for (String word : words) {
      Node node = root;
      for (char ch : word.toCharArray()) {
        int idx = ch - 'a';
        if (node.next[idx] == null) node.next[idx] = new Node();
        node = node.next[idx];
      }
      node.word = word;
    }

    dfs(root);
    return best;
  }

  private void dfs(Node node) {
    // Children are visited a to z, so among equal lengths the smallest word comes first
    for (Node child : node.next) {
      // Go deeper only if this prefix is itself a word in the list
      if (child == null || child.word == null) continue;

      // Strictly longer only, so a tie keeps the lexicographically smaller word
      if (child.word.length() > best.length()) best = child.word;
      dfs(child);
    }
  }

  // Time: O(total characters)
  // Space: O(total characters)
}