# Linked List

A Linked List is a linear data structure where elements (called **Nodes**) are not stored at contiguous memory locations. Instead, each node contains two things:

1. Data: The actual value.
2. Pointer/Reference: A memory reference pointing to the next node in the sequence.

![](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYcne38avWS80hztRfyofcXMnP4ARpp2q0C_91l8bAoCH6UQESRpvUxfM&s=10)

As shown above, each node holds its value alongside the address of the next node (e.g., node containing `15` holds address `3600`). The entry point is the **Head**, and the chain ends when a node points to **NULL**.

**Why Linked List when we already have Arrays?**

| Feature                                  | Array / ArrayList                              | Linked List                                      |
| ---------------------------------------- | ---------------------------------------------- | ------------------------------------------------ |
| **Memory Allocation**                    | Contiguous block in memory                     | Non-contiguous (nodes can live anywhere in heap) |
| **Access Time**                          | **$O(1)$** via index (e.g., `arr[3]`)          | **$O(N)$** must traverse from head node by node  |
| **Insertion/Deletion at Head**           | **$O(N)$** (requires shifting all elements)    | **$O(1)$** (just re-link pointers)               |
| **Insertion/Deletion at Given Position** | **$O(N)$** (shifting elements)                 | **$O(1)$** after reaching the node               |
| **Resizing**                             | Costly (allocating a larger array and copying) | Dynamic (grows and shrinks naturally at runtime) |

## Implementation

** Linked List**

```java
class Node {
  int val;
  Node next;

  Node(int val) {
    this.val = val;
    this.next = null;
  }

  Node(int val, Node next) {
    this.val = val;
    this.next = next;
  }
}

public class LinkedList {
  
  private Node head;
  private int size;

  public LinkedList() {
    this.head = null;
    this.size = 0;
  }

  public void addFirst(int val) {
    Node newNode = new Node(val);
    newNode.next = head;
    head = newNode;
    size++;
  }

  public void addLast(int val) {
    if (head == null) {
      addFirst(val);
      return;
    }

    Node curr = head;
    while (curr.next != null) {
      curr = curr.next;
    }

    curr.next = new Node(val);
    size++;
  }

  public void addAt(int index, int val) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }

    if (index == 0) {
      addFirst(val);
      return;
    }

    if (index == size) {
      addLast(val);
      return;
    }

    Node curr = head;
    for (int i = 0; i < index - 1; i++) {
      curr = curr.next;
    }

    curr.next = new Node(val, curr.next);
    size++;
  }

  public int deleteFirst() {
    if (head == null) {
      throw new IllegalStateException();
    }

    int removedVal = head.val;
    head = head.next;
    size--;
    return removedVal;
  }

  public int deleteLast() {
    if (head == null) {
      throw new IllegalStateException();
    }

    if (size == 1) {
      return deleteFirst();
    }

    Node curr = head;
    while (curr.next.next != null) {
      curr = curr.next;
    }

    int removedVal = curr.next.val;
    curr.next = null;
    size--;
    return removedVal;
  }

  public int deleteAt(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    if (index == 0) {
      return deleteFirst();
    }

    if (index == size - 1) {
      return deleteLast();
    }

    Node curr = head;
    for (int i = 0; i < index - 1; i++) {
      curr = curr.next;
    }

    int removedVal = curr.next.val;
    curr.next = curr.next.next;
    size--;
    return removedVal;
  }

  public int getLength() {
    return this.size;
  }

}
```

**Complexity Analysis:**

- Time: O(1) for addFirst/deleteFirst/getLength; O(N) for indexed and last operations
- Space: O(1) auxiliary space for all operations

---

**Doubly Linked List**

In a Doubly Linked List (DLL), each node contains references to both its next node (`next`) and its previous node (`prev`).

![](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxS1iIJZdO2eYWidGAjJutj9AsE17VOBqDwZO4w-Zb8L7YB3oRj8Pg-ek&s=10)

Notice how maintaining a `tail` reference alongside `head` turns `addLast` and `deleteLast` into $O(1)$ operations because we don't have to traverse the entire list to find the last node.

```java
class Node {
  int val;
  Node prev;
  Node next;

  Node(int val) {
    this.val = val;
  }
}

public class DoublyLinkedList {
  
  private Node head;
  private Node tail;
  private int size;

  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public void addFirst(int val) {
    Node newNode = new Node(val);
    if (head == null) {
      head = tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
    size++;
  }

  public void addLast(int val) {
    if (head == null) {
      addFirst(val);
      return;
    }
    Node newNode = new Node(val);
    tail.next = newNode;
    newNode.prev = tail;
    tail = newNode;
    size++;
  }

  public void addAt(int index, int val) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    if (index == 0) {
      addFirst(val);
      return;
    }
    if (index == size) {
      addLast(val);
      return;
    }

    Node curr = getNodeAt(index);
    Node prevNode = curr.prev;
    Node newNode = new Node(val);

    newNode.next = curr;
    newNode.prev = prevNode;
    prevNode.next = newNode;
    curr.prev = newNode;
    size++;
  }

  public int deleteFirst() {
    if (head == null) {
      throw new IllegalStateException();
    }
    int removedVal = head.val;
    if (head == tail) {
      head = tail = null;
    } else {
      head = head.next;
      head.prev = null;
    }
    size--;
    return removedVal;
  }

  public int deleteLast() {
    if (tail == null) {
      throw new IllegalStateException();
    }
    if (head == tail) {
      return deleteFirst();
    }
    int removedVal = tail.val;
    tail = tail.prev;
    tail.next = null;
    size--;
    return removedVal;
  }

  public int deleteAt(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    if (index == 0) return deleteFirst();
    if (index == size - 1) return deleteLast();

    Node target = getNodeAt(index);
    int removedVal = target.val;

    target.prev.next = target.next;
    target.next.prev = target.prev;
    size--;
    return removedVal;
  }

  // Optimize traversal by starting from the closer end of the list
  private Node getNodeAt(int index) {
    Node curr;
    if (index < size / 2) {
      curr = head;
      for (int i = 0; i < index; i++) {
        curr = curr.next;
      }
    } else {
      curr = tail;
      for (int i = size - 1; i > index; i--) {
        curr = curr.prev;
      }
    }
    return curr;
  }

  public int getLength() {
    return size;
  }
}
```

**Complexity Analysis:**

- Time: O(1) for addFirst, addLast, deleteFirst, deleteLast; O(N) for indexed operations
- Space: O(1) auxiliary space

---

**Circular Linked List**

A Circular Linked List is a variation of a linked list where the last node points back to the first node (or head) instead of pointing to NULL. This forms a continuous, closed loop.

There are two main types:

- Circular Singly Linked List: Last node points to head.
- Circular Doubly Linked List: Last node's next points to head, and head's prev points to the last node.

```java
class Node {
  int val;
  Node next;

  Node(int val) {
    this.val = val;
  }
}

public class CircularLinkedList {
  
  private Node tail;
  private int size;

  public CircularLinkedList() {
    this.tail = null;
    this.size = 0;
  }

  public void addFirst(int val) {
    Node newNode = new Node(val);
    if (tail == null) {
      newNode.next = newNode;
      tail = newNode;
    } else {
      newNode.next = tail.next;
      tail.next = newNode;
    }
    size++;
  }

  public void addLast(int val) {
    if (tail == null) {
      addFirst(val);
      return;
    }
    Node newNode = new Node(val);
    newNode.next = tail.next;
    tail.next = newNode;
    tail = newNode;
    size++;
  }

  public int deleteFirst() {
    if (tail == null) {
      throw new IllegalStateException();
    }

    Node head = tail.next;
    int removedVal = head.val;

    if (tail == head) {
      tail = null;
    } else {
      tail.next = head.next;
    }

    size--;
    return removedVal;
  }

  public int deleteLast() {
    if (tail == null) {
      throw new IllegalStateException();
    }

    Node head = tail.next;
    if (tail == head) {
      return deleteFirst();
    }

    Node curr = head;
    while (curr.next != tail) {
      curr = curr.next;
    }

    int removedVal = tail.val;
    curr.next = head;
    tail = curr;

    size--;
    return removedVal;
  }

  public int getLength() {
    return size;
  }
}
```

**Complexity Analysis:**

- Time: O(1) for addFirst, addLast, and deleteFirst; O(N) for deleteLast
- Space: O(1) auxiliary space


