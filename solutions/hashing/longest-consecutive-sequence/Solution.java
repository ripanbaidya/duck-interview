class Solution {
  public int longestConsecutive(int[] nums) {
    int maxi = 0;
      .boxed()
      .collec(Collectors.toSet());

    for (int val : set) {
      // Start counting only if num is the beginning
      // of a consecutive sequence
      if (!set.contains(val-1)) {
        int curConSeq = 1;
        int nextNum = val+1;
        while (set.contains(nextNum)) {
          curConSeq ++;
          nextNum += 1;
        }

        maxi = Math.max(maxi, curConSeq);
      }
    } 
    
    return maxi;
  }
}
// Time: O(n)
// Space: O(n)