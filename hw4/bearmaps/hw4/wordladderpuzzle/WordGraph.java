package bearmaps.hw4.wordladderpuzzle;

import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;
import edu.princeton.cs.introcs.In;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents the graph of all english words. Word p has an
 * edge to word q if the edit distance between p and q is 1. For example,
 * there is an edge from "horse" to "hose", and "hose" to "horse". There is
 * no edge from "dog" to "deg", because deg isn't a word (at least according to
 * words10000.txt).
 * Created by hug.
 */
public class WordGraph implements AStarGraph<String> {
    private Set<String> words;
    private static final String WORDFILE = "words10000.txt";

    /**
     * Reads the wordfile specified by the wordfile variable.
     */
    private void readWords() {
        words = new HashSet<>();

        In in = new In(WORDFILE);
        while (!in.isEmpty()) {
            String w = in.readString();
            words.add(w);
        }
    }

    /**
     * Computes the edit distance between a and b. From
     * https://rosettacode.org/wiki/Levenshtein_distance.
     */
    private static int editDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                        a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }


    /**
     * Creates a new Word.
     */
    public WordGraph() {
        readWords();
    }

/*    @Override
    public List<WeightedEdge<String>> neighbors(String s) {
        List<WeightedEdge<String>> neighbs = new ArrayList<>();
        for (String w : words) {
            if (editDistance(s, w) == 1) {
                neighbs.add(new WeightedEdge(s, w, 1));
            }
        }
        return neighbs;
    }*/

    /**
     * 不同于查找words文件中的每个单词，
     * 我选择对于每个单词找到其全部editDistance为1的单词，
     * 然后再去words中查找（因为是HashTable，所以会快一些）
     * @param s 单词
     * @return 查找的距离为1的邻居list
     */
    @Override
    public List<WeightedEdge<String>> neighbors(String s) {
        List<WeightedEdge<String>> neighbs = new ArrayList<>();
        //删除
        String sDelete = s.substring(1);
        for(int i = 0; i < s.length(); i++)
        {
            if(words.contains(sDelete))
            {
                neighbs.add(new WeightedEdge<>(s, sDelete, 1));
            }
            sDelete = s.substring(0, i) + s.substring(i + 1);
        }
        //添加,默认都是小写字母
        for(int i = 0; i <= s.length(); i++)
        {
            for(char c = 'a'; c <= 'z'; c++)
            {
                String sAdd = s.substring(0, i) + c + s.substring(i);
                if(words.contains(sAdd))
                {
                    neighbs.add(new WeightedEdge<>(s, sAdd, 1));
                }
            }
        }
        //替换
        for(int i = 0; i < s.length(); i++)
        {
            for(char c = 'a'; c <= 'z'; c++)
            {
                String sSub = s.substring(0, i) + c + s.substring(i + 1);
                if(words.contains(sSub))
                {
                    neighbs.add(new WeightedEdge<>(s, sSub, 1));
                }
            }
        }
        return neighbs;
    }

    @Override
    public double estimatedDistanceToGoal(String s, String goal) {
        return editDistance(s, goal);
    }
}
