class Solution {
  
  public int totalFruit(int[] fruits) {
    int maxLen = 0;
    int left = 0;
    Map<Integer, Integer> count = new HashMap<>();

    for (int right = 0; right < fruits.length; right++) {
      count.merge(fruits[right], 1, Integer::sum);

      while (count.size() > 2) {
        count.merge(fruits[left], -1, Integer::sum);
        count.remove(fruits[left], 0);
        left++;
      }

      maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
  }

  // Time: O(n)
  // Space: O(1), at most 3 distinct fruit types in the map
}