class Solution {
  public int eraseOverlapIntervals(int[][] intervals) {
    int n = intervals.length;
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

    if (n <= 1) 
      return 0;

    int removals = 0;
    int[] prev = intervals[0];

    // If two intervals overlap, remove one and keep the smaller overlapping range.
    // This helps us avoid as many future overlaps as possible.
    for (int i = 1; i < n; i++) {
      int[] curr = intervals[i];

      if (curr[0] < prev[1]) {
        removals++;
        prev[1] = Math.min(prev[1], curr[1]);
      } else {
        prev = curr;
      }
    }

    return removals;
  }
}