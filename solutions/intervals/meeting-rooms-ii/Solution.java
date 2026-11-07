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
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty())
            return 0;
        
        intervals.sort(Comparator.comparingInt(a -> a.start));

        // Min-heap to store the end times of ongoing meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Interval interval : intervals) {
            if (!minHeap.isEmpty() && interval.start >= minHeap.peek()) {
                minHeap.poll();
            }
               
            minHeap.offer(interval.end);
        }

        return minHeap.size();
    }
}

// Time - O(n log n)
// Space - O(n)