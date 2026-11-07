public class Codec {

  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    write(root, sb);
    return sb.toString();
  }

  // Preorder with null markers is enough to rebuild the exact tree
  private void write(TreeNode node, StringBuilder sb) {
    if (node == null) {
      sb.append("#,");
      return;
    }
    sb.append(node.val).append(",");
    write(node.left, sb);
    write(node.right, sb);
  }

  public TreeNode deserialize(String data) {
    Queue<String> tokens = new ArrayDeque<>(Arrays.asList(data.split(",")));
    return read(tokens);
  }

  private TreeNode read(Queue<String> tokens) {
    String val = tokens.poll();
    if (val.equals("#")) return null;

    TreeNode node = new TreeNode(Integer.parseInt(val));
    node.left = read(tokens);
    node.right = read(tokens);
    return node;
  }

  // Time: O(n)
  // Space: O(n)
}

class Solution {
}