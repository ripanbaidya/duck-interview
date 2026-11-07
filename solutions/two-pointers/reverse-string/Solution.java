class Solution {
  public void reverseString(char[] s) {
    int left = 0, right = s.length - 1;

    while (left < right) {
      // Swap characters from both ends and move toward the center.
      swap(s, left, right);
      left++;
      right--;
    }
  }

  private void swap(char[] s, int i, int j) {
    char temp = s[i];
    s[i] = s[j];
    s[j] = temp;
  }
}

// Time: O(n)
// Space: O(1)