class Node {
  int key, value;
  Node prev, next;

  Node(int key, int value) {
    this.key = key;
    this.value = value;
  }
}

class LRUCache {

  private final int capacity;
  private final Map<Integer, Node> map;
  private final Node head, tail;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.map = new HashMap<>();
    
    this.head = new Node(0, 0);
    this.tail = new Node(0, 0);
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    if (!map.containsKey(key)) return -1;

    Node node = map.get(key);
    // Accessed item becomes the most recently used
    remove(node);
    insertToFront(node);
    return node.value;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.value = value;
      remove(node);
      insertToFront(node);
    } else {
      // Evict the least recently used item (stored just before tail) when full
      if (map.size() == capacity) {
        Node lru = tail.prev;
        remove(lru);
        map.remove(lru.key);
      }

      Node node = new Node(key, value);
      insertToFront(node);
      map.put(key, node);
    }
  }

  private void remove(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void insertToFront(Node node) {
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }

  // Time: O(1) for get and put
  // Space: O(capacity)
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */