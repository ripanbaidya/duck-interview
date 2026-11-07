class Solution {

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> dict = new HashSet<>(wordList);
    if (!dict.contains(endWord)) 
      return 0;

    Queue<String> queue = new ArrayDeque<>();
    queue.offer(beginWord);
    dict.remove(beginWord);

    int steps = 1;

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        String word = queue.poll();
        if (word.equals(endWord))
          return steps;

        char[] chars = word.toCharArray();
        for (int j = 0; j < chars.length; j++) {
          char orig = chars[j];
      
          for (char c = 'a'; c <= 'z'; c++) {
            chars[j] = c;
            String next = new String(chars);
            
            if (dict.contains(next)) {
              queue.offer(next);
              dict.remove(next);
            }
          }
          chars[j] = orig;
        }
      }
      steps++;
    }

    return 0;
  }

  // Time: O(N * L * 26) - For each of the N words in the dictionary, we try L positions × 26 letters, 
  //  and each string build/lookup costs O(L). This is the standard bound for Word Ladder BFS.
  // Space: O(N * L) - The dictionary set stores up to N words of length L, and the queue can hold up
  //  to N words in the worst case.
}