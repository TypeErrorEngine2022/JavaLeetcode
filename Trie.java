import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    // first letter of inserted words
    // max size = 26
    private final TrieNode head;

    public Trie() {
        head = new TrieNode('!');
    }

    public void insert(String word) {
        head.addSuccessor(word, 0);
    }

    public boolean search(String word) {
        return head.searchWord(word, 0, false);
    }

    public boolean startsWith(String prefix) {
        return head.searchWord(prefix, 0, true);
    }

    protected static class TrieNode {
        char val;
        // link to next character node
        HashMap<Character, TrieNode> successors;

        boolean isEnd;

        public TrieNode(char val) {
            this.val = val;
            successors = new HashMap<>();
            isEnd = false;
        }

        public void addSuccessor(String word, int successorIndex) {
            // reach the end of word, marked have end
            if (successorIndex > word.length() - 1) {
                isEnd = true;
                return;
            }

            TrieNode nextCharNode;
            char nextCharVal = word.charAt(successorIndex);
            if (successors.containsKey(nextCharVal)) {
                nextCharNode = successors.get(nextCharVal);
            }
            else {
                nextCharNode = new TrieNode(nextCharVal);
                successors.put(nextCharVal, nextCharNode);
            }

            nextCharNode.addSuccessor(word, successorIndex + 1);
        }

        public boolean searchWord(String word, int successorIndex, boolean searchPrefix) {
            if (successorIndex > word.length() - 1) {
                if (searchPrefix) return true;
                // check any stored word ends here
                else return isEnd;
            }

            char nextCharVal = word.charAt(successorIndex);
            if (!successors.containsKey(nextCharVal)) return false;

            TrieNode nextCharNode = successors.get(nextCharVal);
            return nextCharNode.searchWord(word, successorIndex + 1, searchPrefix);
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}
