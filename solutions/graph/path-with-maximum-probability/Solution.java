class Pair {
  int node;
  double prob;

  Pair(int node, double prob) {
    this.node = node;
    this.prob = prob;
  }
}

class Solution {

  // Use Dijkstra's algorithm with a max-heap to find the  path with 
  // the maximum success probability between the start and end nodes.
  public double maxProbability(int n, int[][] edges, double[] succProb, int startNode, int endNode) {
    List<List<Pair>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0];
      int v = edges[i][1];
      double p = succProb[i];
      adj.get(u).add(new Pair(v, p));
      adj.get(v).add(new Pair(u, p));
    }

    double[] maxProb = new double[n];
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
    pq.offer(new Pair(startNode, 1.0));

    while (!pq.isEmpty()) {
      Pair curr = pq.poll();
      int node = curr.node;
      double prob = curr.prob;

      if (node == endNode) {
        return prob;
      }

      if (prob < maxProb[node]) {
        continue;
      }

      for (Pair next : adj.get(node)) {
        int nextNode = next.node;
        double nextProb = next.prob;

        if (maxProb[nextNode] < prob * nextProb) {
          maxProb[nextNode] = prob * nextProb;
          pq.offer(new Pair(nextNode, maxProb[nextNode]));
        }
      }
    }

    return 0.0;
  }

  // Time: O(E log V) where E is the number of edges and V is the number of nodes
  // Space: O(V + E) for the adjacency list and priority queue
}