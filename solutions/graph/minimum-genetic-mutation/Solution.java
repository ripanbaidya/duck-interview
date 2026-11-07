class Solution {
  
  // Use BFS to find the minimum mutations by exploring all valid 
  // 1-character changes from the gene bank level by level.
  public int minMutation(String startGene, String endGene, String[] bank) {
    if (startGene.equals(endGene)) {
      return 0;
    }

    Set<String> dict = new HashSet<>(Arrays.asList(bank));
    if (!dict.contains(endGene)) {
      return -1;
    }

    char[] choices = {'A', 'C', 'G', 'T'};
    Queue<String> q = new ArrayDeque<>();
    q.offer(startGene);
    dict.remove(startGene);

    int mutations = 0;
    while (!q.isEmpty()) {
      mutations++;
      int size = q.size();
      for (int i = 0; i < size; i++) {
        String curr = q.poll();
        char[] chars = curr.toCharArray();

        for (int j = 0; j < chars.length; j++) {
          char orig = chars[j];
          for (char c : choices) {
            if (c == orig) {
              continue;
            }

            chars[j] = c;
            String next = new String(chars);

            if (dict.contains(next)) {
              if (next.equals(endGene)) {
                return mutations;
              }
              q.offer(next);
              dict.remove(next);
            }
          }
          chars[j] = orig;
        }
      }
    }

    return -1;
  }

  // Time: O(N * L) where N is the number of strings in the bank and L is the gene length (8)
  // Space: O(N * L) for the bank set and BFS queue
}