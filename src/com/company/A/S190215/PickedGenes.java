package com.company.A.S190215;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PickedGenes {
    /* --------------------------------------- 2 --------------------------------------- */
    // String getGenes(); 获得下一个DNA序列内容
    // String[] 里面的每个短DNA序列是不是在长DNA序列里面

    public List<String> getAppearingDNASeries(String[] DNASeriesArray) {
        Trie trie = new Trie();
        for (String DNASeries : DNASeriesArray) {
            trie.insertDNASeries(DNASeries);
        }
        List<String> appearingDNASeries = new LinkedList<>();
        List<TrieNode> pointers = new ArrayList<>();
        while (hasNextGenes()) {
            char gene = getGenes();
            // 每次有新的streaming data 先加个pointer进去
            TrieNode newPointer = trie.root;
            pointers.add(newPointer);
            // 对于list里面每个pointer, 看看能不能往下走一步
            for (int i = 0; i < pointers.size(); i++) {
                TrieNode curPointer = pointers.get(i);
                // 能走： 走一步，如果是一个series，加到result里
                if (curPointer.children[gene - 'A'] != null) {
                    curPointer = curPointer.children[gene - 'A'];
                    System.out.println(gene + " "  + curPointer.DNASeries);
                    if (curPointer.DNASeries != null) {
                        appearingDNASeries.add(curPointer.DNASeries);
                    }
                    pointers.set(i, curPointer);
                    // 不能走： 直接从list里面remove
                } else {
                    pointers.remove(i--); // i - 1 因为删掉了一个
                }

            }
            System.out.println();
        }

        return appearingDNASeries;
    }

    String mock_stream_value = "AGTTTCCCAA";
    StringCharacterIterator iterator = new StringCharacterIterator( mock_stream_value);

    private boolean hasNextGenes() {
        return iterator.getEndIndex()!=iterator.getIndex();
    }
    private char getGenes() {
        char c  = iterator.current();
        iterator.next();
        return c;
    }

    public static void main(String[] args) {
        PickedGenes test = new PickedGenes();
        System.out.println(test.getAppearingDNASeries(new String[]{"AG","CAA","ACA"}));
    }
}

class TrieNode {
    public TrieNode[] children = new TrieNode[26]; // A G T C;
    public String DNASeries = null;

    TrieNode(String DNASeries) {
        this.DNASeries = DNASeries;
    }
    TrieNode(){
    }
}

class Trie {
    public TrieNode root;// could be set to final referring to this root specifically.
    Trie(){
        this.root  = new TrieNode();
    }

    public void insertDNASeries(String DNASeries) {
        TrieNode cur = root;
        for (char c : DNASeries.toCharArray()) {
            if (cur.children[c - 'A'] == null) {
                cur.children[c - 'A'] = new TrieNode();
                System.out.println(c);
            }
            cur = cur.children[c - 'A'];
        }
        cur.DNASeries = DNASeries;
    }
}