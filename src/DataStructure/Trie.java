package DataStructure;

/**
 * @author wuzijian
 * @date 2021/3/13 1:45 下午
 */
class Trie {
    class TrieNode {
        boolean isEnd = true;
        TrieNode[] next = new TrieNode[26];
    }
    TrieNode head;
    /** Initialize your data structure here. */
    public Trie() {
        head = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode p = head;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            while(i < word.length()-1 && p.next[c - 'a'] != null) {
                p = p.next[c - 'a'];
                c = word.charAt(++i);
            }
            p.isEnd = false;
            TrieNode q = new TrieNode();
            p.next[c - 'a'] = q;
            p = q;
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int i = 0;
        TrieNode p = head;
        char c = word.charAt(i);
        while( p.next[c-'a'] != null) {
            p = p.next[c - 'a'];
            if (i == word.length()-1) {
                return true;
            }
            c = word.charAt(++i);
        }
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return search(prefix);
    }

    public static void main(String[] args) {
        /**
         * Your Trie object will be instantiated and called as such:
         * Trie obj = new Trie();
         * obj.insert(word);
         * boolean param_2 = obj.search(word);
         * boolean param_3 = obj.startsWith(prefix);
         */
          Trie obj = new Trie();
          String word = "apple";
          String prefix = "apple";
          obj.insert(word);
          boolean param_2 = obj.search(word);
          boolean param_3 = obj.startsWith(prefix);
          obj.insert("app");
        System.out.println(param_2);
        System.out.println(param_3);
    }
}


