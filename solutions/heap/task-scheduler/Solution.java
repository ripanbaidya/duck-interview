class Solution {

  public int leastInterval(char[] tasks, int n) {
    Map<Character, Integer> freq = new HashMap<>();
    Map<Character, Integer> nextValid = new HashMap<>();

    for (char task : tasks) {
      freq.put(task, freq.getOrDefault(task, 0) + 1);
      nextValid.put(task, 0);
    }

    int time = 0;
    int remaining = tasks.length;

    while (remaining > 0) {
      Character chosen = null;

      for (char task : freq.keySet()) {
        if (freq.get(task) == 0 || time < nextValid.get(task)) {
          continue;
        }

        if (chosen == null || freq.get(task) > freq.get(chosen)) {
          chosen = task;
        }
      }

      if (chosen != null) {
        freq.put(chosen, freq.get(chosen) - 1);
        nextValid.put(chosen, time + n + 1);
        remaining--;
      }

      time++;
    }

    return time;
  }

  // Time: O(T * K) where T is total CPU time and K is the number of unique tasks
  // Space: O(K) for storing frequencies and cooldown timers
}