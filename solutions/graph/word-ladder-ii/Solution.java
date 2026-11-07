class Solution {

  private List<List<String>> paths;
  private Map<String, Set<String>> preds;

  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    paths = new ArrayList<>();
    Set<String> dict = new HashSet<>(wordList);
    if (!dict.contains(endWord)) 
      return paths;

    dict.remove(beginWord);
    Map<String, Integer> dist = new HashMap<>();
    dist.put(beginWord, 0);
    preds = new HashMap<>();

    Queue<String> queue = new ArrayDeque<>();
    queue.offer(beginWord);

    int level = 0;
    boolean found = false;
    
    while (!queue.isEmpty() && !found) {
      level++;
    
      for (int i = queue.size(); i > 0; i--) {
        String word = queue.poll();
        char[] chars = word.toCharArray();

        for (int j = 0; j < chars.length; j++) {
          char orig = chars[j];
    
          for (char c = 'a'; c <= 'z'; c++) {
            chars[j] = c;
            String next = new String(chars);

            // Another shortest path reaches next at this level.
            if (dist.getOrDefault(next, -1) == level) {
              preds.get(next).add(word);
            }
            if (!dict.contains(next)) 
              continue;

            preds.computeIfAbsent(next, x -> new HashSet<>()).add(word);
            dict.remove(next);
            dist.put(next, level);
            queue.offer(next);
            if (next.equals(endWord)) 
              found = true;
          }
          chars[j] = orig;
        }
      }
    }

    if (found) {
      Deque<String> path = new ArrayDeque<>();
      path.add(endWord);
      buildPaths(path, beginWord, endWord);
    }
    
    return paths;
  }

  private void buildPaths(Deque<String> path, String beginWord, String word) {
    if (word.equals(beginWord)) {
      paths.add(new ArrayList<>(path));
      return;
    }
    for (String prev : preds.get(word)) {
      path.addFirst(prev);
      buildPaths(path, beginWord, prev);
      path.removeFirst();
    }
  }

  // Time: O(N * L^2 * 26 + P * L)
  // Space: O(N * L + P * L)
}