class Solution {
  
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] flight : flights) {
      int from = flight[0], to = flight[1], price = flight[2];
      adj.get(from).add(new int[]{to, price});
    }
    
    int[] minCost = new int[n];
    Arrays.fill(minCost, (int) 1e9);
    minCost[src] = 0;

    Queue<int[]> que = new LinkedList<>();
    que.offer(new int[]{src, 0});
    
    int stops = 0;

    // BFS level-order traversal where each level represents the number of stops
    while (!que.isEmpty() && stops <= k) {
      int size = que.size();

      for (int i = 0; i < size; i++) {
        int[] top = que.poll();
        int curr = top[0];
        int cost = top[1]; 

        for (int[] nei : adj.get(curr)) {
          int next = nei[0];
          int price = nei[1];

          if (minCost[next] > price + cost) {
            minCost[next] = price + cost;
            que.offer(new int[]{next, minCost[next]});
          }
        }
      }
      stops++;
    }

    return minCost[dst] == (int) 1e9 ? -1 : minCost[dst];
  }

  // Time: O(K * (V + E)) where V is vertices, E is edges, and K is the stop limit
  // Space: O(V + E) for the adjacency list and queue storage
}