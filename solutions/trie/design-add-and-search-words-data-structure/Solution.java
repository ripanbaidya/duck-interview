class WordDictionary {

  private static class Node {
    Node[] next = new Node[26];
    boolean end;
  }

  private Node root = new Node();

  public void addWord(String word) {
    Node node = root;
    for (char ch : word.toCharArray()) {
      int idx = ch - 'a';
      if (node.next[idx] == null) node.next[idx] = new Node();
      node = node.next[idx];
    }
    node.end = true;
  }

  public boolean search(String word) {
    return dfs(word, 0, root);
  }

  private boolean dfs(String word, int idx, Node node) {
    if (idx == word.length()) return node.end;

    char ch = word.charAt(idx);
    if (ch == '.') {
      // Wildcard: try every child
      for (Node child : node.next) {
        if (child != null && dfs(word, idx + 1, child)) return true;
      }
      return false;
    }

    Node child = node.next[ch - 'a'];
    return child != null && dfs(word, idx + 1, child);
  }

  // Time: add O(L), search O(L) without dots, O(26^d * L) with d dots (d <= 2 here)
  // Space: O(total characters inserted)
}