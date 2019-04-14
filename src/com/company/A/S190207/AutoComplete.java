package com.company.A.S190207;

import java.util.*;

class TrieNode {
    TrieNode[] children;
    String word;

    public TrieNode(String word) {
        children = new TrieNode[26];
        this.word = word;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode(null);
    }

    public void insertWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'A'] == null) {
                cur.children[c - 'A'] = new TrieNode(null);
                System.out.println(c);
            }
            cur = cur.children[c - 'A'];
        }
        cur.word = word;
    }
}

public class AutoComplete {
    Trie trie;

    public AutoComplete(List<String> words) {
        trie = new Trie();
        buildTrie(words);
    }

    private void buildTrie(List<String> words) {
        for (String word : words) {
            trie.insertWord(word);
        }
    }

    public List<String> findWordsWithPrefix(String prefix) {
        List<String> wordsWithPrefix = new LinkedList<>();
        TrieNode cur = trie.root;
        for (char c : prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return wordsWithPrefix;
            }
            cur = cur.children[c - 'A'];
        }
        findWordsWithPrefix(wordsWithPrefix, cur);
        return wordsWithPrefix;
    }

    private void findWordsWithPrefix(List<String> wordsWithPrefix, TrieNode root) {
        if (root == null) {
            return;
        }
        if (root.word != null) {
            wordsWithPrefix.add(root.word);
        }
        for (TrieNode child : root.children) {
            findWordsWithPrefix(wordsWithPrefix, child);
        }
    }
}
