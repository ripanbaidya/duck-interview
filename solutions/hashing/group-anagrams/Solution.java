class Solution {
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for (String str : strs) {
      int[] freq = new int[26];

      for (char c : str.toCharArray()) {
        freq[c-'a']++;
      }

      StringBuilder sb = new StringBuilder();
      for (int val : freq) {
        sb.append('#').append(val);
      }

      // if two words are anagram they must have the same key
      String key = sb.toString();
      map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
    }
    
    return new ArrayList<>(map.values());
  }
}