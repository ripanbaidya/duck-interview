class Trie {

  private static class Node {
    Node[] next = new Node[26];
    boolean end;
  }

  private Node root = new Node();

  public void insert(String word) {
    Node node = root;
    for (char ch : word.toCharArray()) {
      int idx = ch - 'a';
      if (node.next[idx] == null) node.next[idx] = new Node();
      node = node.next[idx];
    }
    node.end = true;
  }

  public boolean search(String word) {
    Node node = find(word);
    return node != null && node.end;
  }

  public boolean startsWith(String prefix) {
    return find(prefix) != null;
  }

  // Shared walk: a prefix exists if the path exists, a word also needs end = true
  private Node find(String str) {
    Node node = root;
    for (char ch : str.toCharArray()) {
      node = node.next[ch - 'a'];
      if (node == null) return null;
    }
    return node;
  }

  // Time: O(L) per operation
  // Space: O(total characters inserted)
}