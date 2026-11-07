class Solution {
  public int numRescueBoats(int[] people, int limit) {
    Arrays.sort(people);

    int boatCount = 0;
    int left = 0, right = people.length - 1;

    while (left <= right) {
      int combinedWeight = people[left] + people[right];

      // Pair the lightest and heaviest people if they fit in one boat.
      if (combinedWeight <= limit) {
        left++;
      }

      // The heaviest person always gets a boat in this iteration.
      right--;
      boatCount++;
    }

    return boatCount;
  }
}

// Time: O(n log n), 
// Space: O(1), auxiliary space, excluding the sorting implementation's stack space.