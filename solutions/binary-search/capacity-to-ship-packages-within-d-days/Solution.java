class Solution {
  
  public int shipWithinDays(int[] weights, int days) {
    int low = 0;
    int high = 0;

    for (int w : weights) {
      low = Math.max(low, w);
      high += w;
    }

    int ans = high;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (canShip(weights, days, mid)) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return ans;
  }

  private boolean canShip(int[] weights, int days, int cap) {
    int currWeight = 0;
    int daysUsed = 1;

    for (int w : weights) {
      if (currWeight + w > cap) {
        currWeight = 0;
        daysUsed++;
      }
      currWeight += w;
    }

    return daysUsed <= days;
  }

  // Time: O(n log(sum - max))
  // Space: O(1)
}