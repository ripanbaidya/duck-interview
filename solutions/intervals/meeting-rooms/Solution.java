/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
  public boolean canAttendMeetings(List<Interval> intervals) {
    intervals.sort(Comparator.comparingInt(a -> a.start));
    if (intervals.size() <= 1)
      return true;
    for (int i = 1; i < intervals.size(); i++) {
      Interval a = intervals.get(i - 1);
      Interval b = intervals.get(i);
      if (b.start < a.end)
        return false;
    }

    return true;
  }
}
