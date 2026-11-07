class Solution {

  public int openLock(String[] deadends, String target) {
    Set<String> dead = new HashSet<>(Arrays.asList(deadends));
    if (target.equals("0000")) 
      return 0;
    if (dead.contains("0000") || dead.contains(target)) 
      return -1;

    Queue<String> queue = new ArrayDeque<>();
    Set<String> visited = new HashSet<>();
    queue.offer("0000");
    visited.add("0000");

    int moves = 0;
    while (!queue.isEmpty()) {
      moves++;
      for (int i = queue.size(); i > 0; i--) {
        String lock = queue.poll();

        for (int j = 0; j < 4; j++) {
          for (int d = -1; d <= 1; d += 2) {
            char[] chars = lock.toCharArray();
            int digit = (chars[j] - '0' + d + 10) % 10;
            chars[j] = (char) ('0' + digit);
            String next = new String(chars);

            if (!dead.contains(next) && !visited.contains(next)) {
              if (next.equals(target)) return moves;
              visited.add(next);
              queue.offer(next);
            }
          }
        }
      }
    }
    return -1;
  }

  // Time: O(10^4 * 4 * 2 * 4) - There are at most 10,000 lock states. Each state generates 8 neighbors,
  //  and each neighbor requires building/hashing a 4-character string, giving a small constant
  //  factor: 10^4 * 8 * 4
  // Space: O(10^4) - The dead, visited, and queue structures hold at most all possible states.
}