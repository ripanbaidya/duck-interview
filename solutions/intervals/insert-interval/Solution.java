class Solution {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

    int i = 0, n = intervals.length;
    ArrayList<int[]> res = new ArrayList<>();

    // 1. Add intervals that end before newInterval starts
    while (i < n && newInterval[0] > intervals[i][1]) {
      res.add(intervals[i]);
      i++;
    }

    // 2. Merge all overlapping intervals into newInterval
    while (i < n && newInterval[1] >= intervals[i][0]) {
      newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
      newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
      i++;
    }
    res.add(newInterval);

    // 3. Add the remaining intervals
    while (i < n) {
      res.add(intervals[i]);
      i++;
    }

    return res.toArray(new int[res.size()][]);
  }
}