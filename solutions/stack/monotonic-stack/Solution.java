/**
 * Time: O(n) for each method.
 * Space: O(n) for each method.
 */
class Solution {
  
  // Next Greater Element(NGE)
  public ArrayList<Integer> nextGreaterElement(int[] arr) {
    int n = arr.length;
    ArrayList<Integer> nge = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();

    // Initialize all positions with -1 for elements with no greater element.
    for (int i = 0; i < n; i++) {
      nge.add(-1);
    }

    for (int i = 0; i < n; i++) {
      // The current element is the next greater element for smaller elements
      // whose indices are still waiting at the top of the stack.
      while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
        int idx = stack.pop();
        nge.set(idx, arr[i]);
      }

      // Store the index to find its next greater element later.
      stack.push(i);
    }

    return nge;
  }

  // Next Smaller Element(NSE)
  public ArrayList<Integer> nextSmallerElement(int[] arr) {
    int n = arr.length;
    ArrayList<Integer> nse = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();

    // Initialize all positions with -1 for elements with no smaller element.
    for (int i = 0; i < n; i++) {
      nse.add(-1);
    }

    for (int i = 0; i < n; i++) {
      // The current element is the next smaller element for larger elements
      // whose indices are still waiting at the top of the stack.
      while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
        int idx = stack.pop();
        nse.set(idx, arr[i]);
      }

      // Store the index to find its next smaller element later.
      stack.push(i);
    }

    return nse;
  }

  // Previous Greater Element(PGE)
  public ArrayList<Integer> previousGreaterElement(int[] arr) {
    int n = arr.length;
    ArrayList<Integer> pge = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      // Remove smaller or equal elements to find the nearest previous greater element.
      while (!stack.isEmpty() && stack.peek() <= arr[i]) {
        stack.pop();
      }

      // If no greater element remains, store -1.
      pge.add(stack.isEmpty() ? -1 : stack.peek());

      // Keep the current element as a candidate for future elements.
      stack.push(arr[i]);
    }

    return pge;
  }

  // Previous Smaller Element(PSE)
  public static ArrayList<Integer> previousSmallerElement(int[] arr) {
    int n = arr.length;
    ArrayList<Integer> pse = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      // Remove elements greater than or equal to the current element.
      while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
        stack.pop();
      }

      // The top element is the nearest previous smaller element, or -1 if none exists.
      pse.add(stack.isEmpty() ? -1 : arr[stack.peek()]);

      // Store the current index for future comparisons.
      stack.push(i);
    }

    return pse;
  }


}