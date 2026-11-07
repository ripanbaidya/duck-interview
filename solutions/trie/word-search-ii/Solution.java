class Solution {

  private static class Node {
    Node[] next = new Node[26];
    String word; // set only at the end of a word
    int kids;
  }

  public List<String> findWords(char[][] board, String[] words) {
    Node root = new Node();
    for (String word : words) {
      Node node = root;
      for (char ch : word.toCharArray()) {
        int idx = ch - 'a';
        if (node.next[idx] == null) {
          node.next[idx] = new Node();
          node.kids++;
        }
        node = node.next[idx];
      }
      node.word = word;
    }

    List<String> res = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        dfs(board, i, j, root, res);
      }
    }
    return res;
  }

  private void dfs(char[][] board, int i, int j, Node parent, List<String> res) {
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;

    char ch = board[i][j];
    if (ch == '#') return;

    int idx = ch - 'a';
    Node node = parent.next[idx];
    if (node == null) return;

    if (node.word != null) {
      res.add(node.word);
      node.word = null; // each word is reported once
    }

    board[i][j] = '#';
    dfs(board, i + 1, j, node, res);
    dfs(board, i - 1, j, node, res);
    dfs(board, i, j + 1, node, res);
    dfs(board, i, j - 1, node, res);
    board[i][j] = ch;

    // Prune: a node with no children and no word is dead weight for later searches
    if (node.kids == 0) {
      parent.next[idx] = null;
      parent.kids--;
    }
  }

  // Time: O(m * n * 3^L)
  // Space: O(total characters in words)
}