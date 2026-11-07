class Twitter {
  private int time;
  private Map<Integer, Set<Integer>> follows;
  private Map<Integer, List<int[]>> tweets;

  public Twitter() {
    time = 0;
    follows = new HashMap<>();
    tweets = new HashMap<>();
  }

  public void postTweet(int userId, int tweetId) {
    tweets.putIfAbsent(userId, new ArrayList<>());
    tweets.get(userId).add(new int[] { time++, tweetId });
  }

  public List<Integer> getNewsFeed(int userId) {
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

    if (tweets.containsKey(userId)) {
      List<int[]> userTweets = tweets.get(userId);
      int index = userTweets.size() - 1;
      maxHeap.offer(new int[] { userTweets.get(index)[0], userTweets.get(index)[1], userId, index });
    }

    Set<Integer> followees = follows.get(userId);
    if (followees != null) {
      for (int fId : followees) {
        if (tweets.containsKey(fId)) {
          List<int[]> fTweets = tweets.get(fId);
          int index = fTweets.size() - 1;
          maxHeap.offer(new int[] { fTweets.get(index)[0], fTweets.get(index)[1], fId, index });
        }
      }
    }

    List<Integer> res = new ArrayList<>();
    while (!maxHeap.isEmpty() && res.size() < 10) {
      int[] curr = maxHeap.poll();
      res.add(curr[1]);

      int uId = curr[2];
      int index = curr[3] - 1;
      if (index >= 0) {
        List<int[]> list = tweets.get(uId);
        maxHeap.offer(new int[] { list.get(index)[0], list.get(index)[1], uId, index });
      }
    }

    return res;
  }

  public void follow(int followerId, int followeeId) {
    if (followerId != followeeId) {
      follows.putIfAbsent(followerId, new HashSet<>());
      follows.get(followerId).add(followeeId);
    }
  }

  public void unfollow(int followerId, int followeeId) {
    if (follows.containsKey(followerId)) {
      follows.get(followerId).remove(followeeId);
    }
  }

  // Time: postTweet O(1), getNewsFeed O(F log F) where F is the number of followees
  // Space: O(T + F) where T is total tweets and F is total follow relationships
}

class Solution {
}