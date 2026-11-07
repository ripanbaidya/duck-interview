class Node {
  String url;
  Node prev, next;

  Node(String url) {
    this.url = url;
  }
}

class BrowserHistory {
  
  private Node curr;

  public BrowserHistory(String homepage) {
    curr = new Node(homepage);
  }

  public void visit(String url) {
    Node node = new Node(url);
    curr.next = node;
    node.prev = curr;
    curr = node;
  }

  public String back(int steps) {
    while (curr.prev != null && steps-- > 0) {
      curr = curr.prev;
    }
    return curr.url;
  }

  public String forward(int steps) {
    while (curr.next != null && steps-- > 0) {
      curr = curr.next;
    }
    return curr.url;
  }
}