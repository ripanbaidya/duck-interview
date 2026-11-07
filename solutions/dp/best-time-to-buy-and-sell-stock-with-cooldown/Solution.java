class Solution {

  public int maxProfit(int[] prices) {
    // hold: own a stock, sold: just sold today, rest: no stock and free to buy
    int hold = -prices[0], sold = 0, rest = 0;

    for (int i = 1; i < prices.length; i++) {
      int prevSold = sold;
      sold = hold + prices[i];

      // Buying is only allowed from rest, because a sale forces one cooldown day
      hold = Math.max(hold, rest - prices[i]);
      rest = Math.max(rest, prevSold);
    }
    return Math.max(sold, rest);
  }

  // Time: O(n)
  // Space: O(1)
}