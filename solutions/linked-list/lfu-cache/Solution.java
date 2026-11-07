class Node {
  int val, freq;

  Node(int val, int freq) {
    this.val = val;
    this.freq = freq;
  }
}

class LFUCache {
  
  private final int capacity;
  private int minFreq;
  // key: original key, value: frequency and original value.
  private final Map<Integer, Node> cache;
  // key: frequency, value: All keys that have the same frequency.
  private final Map<Integer, LinkedHashSet<Integer>> freqMap;

  public LFUCache(int capacity) {
    this.capacity = capacity;
    this.minFreq = 0;
    this.cache = new HashMap<>();
    this.freqMap = new HashMap<>();
  }

  public int get(int key) {
    if (!cache.containsKey(key)) return -1;

    Node node = cache.get(key);
    incrementFreq(key, node);
    return node.val;
  }

  public void put(int key, int value) {
    if (capacity <= 0) return;

    if (cache.containsKey(key)) {
      Node node = cache.get(key);
      node.val = value;
      incrementFreq(key, node);
      return;
    }

    // Evict the least frequently used item (and oldest if tie) when full
    if (cache.size() == capacity) {
      LinkedHashSet<Integer> minKeys = freqMap.get(minFreq);
      int evictKey = minKeys.iterator().next();
      minKeys.remove(evictKey);
      cache.remove(evictKey);
      if (minKeys.isEmpty()) {
        freqMap.remove(minFreq);
      }
    }

    Node node = new Node(value, 1);
    cache.put(key, node);
    freqMap.putIfAbsent(1, new LinkedHashSet<>());
    freqMap.get(1).add(key);
    
    // New insertions always reset the minimum frequency to 1
    minFreq = 1;
  }

  private void incrementFreq(int key, Node node) {
    int freq = node.freq;
    LinkedHashSet<Integer> keys = freqMap.get(freq);
    keys.remove(key);

    if (keys.isEmpty()) {
      freqMap.remove(freq);
      if (minFreq == freq) {
        minFreq++;
      }
    }

    node.freq++;
    freqMap.putIfAbsent(node.freq, new LinkedHashSet<>());
    freqMap.get(node.freq).add(key);
  }

  /*
  Time complexity: O(1), as required by the question.
  Since we only have basic HashMap/(Linked)HashSet operations. For details,
  Our utility function insert puts the key- value pair into the cache, queries and possibly
  puts an empty LinedHashSet in the frequencies, then queries frequencies again and adds
  a key into the associated value which is a LinkedHashSet. All the operations are based 
  on the hash calculating for simple type (int or Integer) and the time complexity is constant.
  For each get operation, in the worst case, we query the frequencies and remove a key from the 
  associated value which is a LinkedHashSet and call insert function once. All the operations have 
  the constant time complexity based on the hash calculating for simple type.
  For each put operation, in the simple case we just insert the new key-value pair into the cache
  and call get function once. In the worst case, we query the frequencies to get the associated value, 
  namely all the keys with the same frequencies which is a LinkedHashSet. And then we get the first 
  key from the LinkedHashSet, remove it from both cache and frequencies. All the operations have the
  constant time complexity based on the hash calculating for simple type.

  Space complexity: O(N).
  We save all the key-value pairs as well as all the keys with frequencies in the 2 HashMaps 
  (plus a LinkedHashSet), so there are at most $min(N, capacity) keys and values at any given time.
  */
}