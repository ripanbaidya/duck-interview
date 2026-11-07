class Solution {
  
  /**
   * Build a character precedence graph by comparing adjacent words.
   * Use Kahn's algorithm (topological sort) to derive the order, handling 
   * the edge case where a longer word incorrectly precedes its own prefix.
   * 
   * https://www.geeksforgeeks.org/problems/alien-dictionary/1
   */
  public String findOrder(String[] words) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < 26; i++) {
      adj.add(new ArrayList<>());
    }

    int[] inDegree = new int[26];
    boolean[] present = new boolean[26];

    for (String word : words) {
      for (char ch : word.toCharArray()) {
        present[ch - 'a'] = true;
      }
    }

    for (int i = 0; i < words.length - 1; i++) {
      String s1 = words[i];
      String s2 = words[i + 1];
      int len = Math.min(s1.length(), s2.length());
      boolean matched = false;

      for (int j = 0; j < len; j++) {
        char c1 = s1.charAt(j);
        char c2 = s2.charAt(j);
        if (c1 != c2) {
          adj.get(c1 - 'a').add(c2 - 'a');
          inDegree[c2 - 'a']++;
          matched = true;
          break;
        }
      }

      // Prefix edge case: a longer word cannot precede its prefix
      if (!matched && s1.length() > s2.length()) {
        return "";
      }
    }

    Queue<Integer> q = new LinkedList<>();
    int totalNodes = 0;
    for (int i = 0; i < 26; i++) {
      if (present[i]) {
        totalNodes++;
        if (inDegree[i] == 0) {
          q.add(i);
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    while (!q.isEmpty()) {
      int curr = q.poll();
      sb.append((char) (curr + 'a'));

      for (int next : adj.get(curr)) {
        inDegree[next]--;
        if (inDegree[next] == 0) {
          q.add(next);
        }
      }
    }

    return sb.length() == totalNodes ? sb.toString() : "";
  }

  // Time: O(N * L + V + E) where N is number of words, L is max word length, 
  //  V is alphabet size, and E is edges
  // Space: O(V + E) for the adjacency list and queue
}