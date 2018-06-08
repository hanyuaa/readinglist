package com.wordsfilter;

import com.wordsfilter.result.FilteredResult;
import com.wordsfilter.result.Word;
import com.wordsfilter.search.tree.Node;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsFilterUtil {
    private static Node tree = new Node();
    private static Node positiveTree;
    private static Pattern p;

    public WordsFilterUtil() {
    }

    private static void insertWord(Node tree, String word, double level) {
        word = word.toLowerCase();
        Node node = tree;

        for (int i = 0; i < word.length(); ++i) {
            node = node.addChar(word.charAt(i));
        }

        node.setEnd(true);
        node.setLevel(level);
        node.setWord(word);
    }

    private static boolean isPunctuationChar(String c) {
        Matcher m = p.matcher(c);
        return m.find();
    }

    private static WordsFilterUtil.PunctuationOrHtmlFilteredResult filterPunctuation(String originalString) {
        StringBuilder filteredString = new StringBuilder();
        ArrayList<Integer> charOffsets = new ArrayList<>();

        for (int i = 0; i < originalString.length(); ++i) {
            String c = String.valueOf(originalString.charAt(i));
            if (!isPunctuationChar(c)) {
                filteredString.append(c);
                charOffsets.add(i);
            }
        }

        WordsFilterUtil.PunctuationOrHtmlFilteredResult result = new WordsFilterUtil.PunctuationOrHtmlFilteredResult();
        result.setOriginalString(originalString);
        result.setFilteredString(filteredString);
        result.setCharOffsets(charOffsets);
        return result;
    }

    private static WordsFilterUtil.PunctuationOrHtmlFilteredResult filterPunctuationAndHtml(String originalString) {
        StringBuilder filteredString = new StringBuilder();
        ArrayList<Integer> charOffsets = new ArrayList<>();
        int i = 0;

        for (boolean var4 = false; i < originalString.length(); ++i) {
            String c = String.valueOf(originalString.charAt(i));
            if (originalString.charAt(i) != 60) {
                if (!isPunctuationChar(c)) {
                    filteredString.append(c);
                    charOffsets.add(i);
                }
            } else {
                int k;
                for (k = i + 1; k < originalString.length(); ++k) {
                    if (originalString.charAt(k) == 60) {
                        k = i;
                        break;
                    }

                    if (originalString.charAt(k) == 62) {
                        break;
                    }
                }

                i = k;
            }
        }

        WordsFilterUtil.PunctuationOrHtmlFilteredResult result = new WordsFilterUtil.PunctuationOrHtmlFilteredResult();
        result.setOriginalString(originalString);
        result.setFilteredString(filteredString);
        result.setCharOffsets(charOffsets);
        return result;
    }

    private static FilteredResult filter(WordsFilterUtil.PunctuationOrHtmlFilteredResult pohResult, char replacement) {
        StringBuilder sentence = pohResult.getFilteredString();
        StringBuilder sb = new StringBuilder(pohResult.getOriginalString());
        ArrayList<Integer> charOffsets = pohResult.getCharOffsets();
        List<Word> positiveWords = simpleFilter2DictFindWords(sentence, positiveTree);
        List<Word> sensitiveWords = simpleFilter2DictFindWords(sentence, tree);
        Iterator sIt = sensitiveWords.iterator();

        while (true) {
            while (sIt.hasNext()) {
                Word sWord = (Word) sIt.next();

                int i;
                Word pWord;
                for (i = 0; i < positiveWords.size(); ++i) {
                    pWord = positiveWords.get(i);
                    if (pWord.getEndPos() >= sWord.getStartPos()) {
                        break;
                    }
                }

                while (i < positiveWords.size()) {
                    pWord = positiveWords.get(i);
                    if (pWord.getStartPos() > sWord.getEndPos()) {
                        break;
                    }

                    if (pWord.getStartPos() < sWord.getStartPos() && pWord.getEndPos() >= sWord.getStartPos() && pWord.getLevel() > sWord.getLevel()) {
                        sIt.remove();
                        break;
                    }

                    if (pWord.getStartPos() <= sWord.getEndPos() && pWord.getEndPos() > sWord.getEndPos() && pWord.getLevel() > sWord.getLevel()) {
                        sIt.remove();
                        break;
                    }

                    if (pWord.getStartPos() <= sWord.getStartPos() && pWord.getEndPos() >= sWord.getEndPos() && pWord.getLevel() > sWord.getLevel()) {
                        sIt.remove();
                        break;
                    }

                    ++i;
                }
            }

            Double maxLevel = 0.0D;
            StringBuilder badWords = new StringBuilder();
            Iterator var16 = sensitiveWords.iterator();

            while (var16.hasNext()) {
                Word word = (Word) var16.next();
                badWords.append(word.getWord()).append(",");
                if (word.getLevel() > maxLevel) {
                    maxLevel = word.getLevel();
                }
            }

            StringBuilder goodWords = new StringBuilder();
            Iterator var18 = positiveWords.iterator();

            Word word;
            while (var18.hasNext()) {
                word = (Word) var18.next();
                goodWords.append(word.getWord()).append(",");
            }

            var18 = sensitiveWords.iterator();

            while (var18.hasNext()) {
                word = (Word) var18.next();

                for (int i = 0; i < word.getPos().length; ++i) {
                    sb.replace((charOffsets.get(word.getPos()[i])), (charOffsets.get(word.getPos()[i])) + 1, "" + replacement);
                }
            }

            FilteredResult result = new FilteredResult();
            result.setBadWords(badWords.toString());
            result.setGoodWords(goodWords.toString());
            result.setFilteredContent(sb.toString());
            result.setOriginalContent(pohResult.getOriginalString());
            result.setLevel(maxLevel);
            result.setHasSensiviWords(!sensitiveWords.isEmpty());
            return result;
        }
    }

    private static FilteredResult simpleFilter(String sentence, char replacement) {
        StringBuilder sb = new StringBuilder(sentence);
        List<Word> positiveWords = simpleFilter2DictFindWords(sb, positiveTree);
        List<Word> sensitiveWords = simpleFilter2DictFindWords(sb, tree);
        Iterator sIt = sensitiveWords.iterator();

        while (true) {
            while (sIt.hasNext()) {
                Word sWord = (Word) sIt.next();

                int i;
                Word pWord;
                for (i = 0; i < positiveWords.size(); ++i) {
                    pWord = positiveWords.get(i);
                    if (pWord.getEndPos() >= sWord.getStartPos()) {
                        break;
                    }
                }

                while (i < positiveWords.size()) {
                    pWord = positiveWords.get(i);
                    if (pWord.getStartPos() > sWord.getEndPos()) {
                        break;
                    }

                    if (pWord.getStartPos() < sWord.getStartPos() && pWord.getEndPos() >= sWord.getStartPos() && pWord.getLevel() > sWord.getLevel()) {
                        sIt.remove();
                        break;
                    }

                    if (pWord.getStartPos() <= sWord.getEndPos() && pWord.getEndPos() > sWord.getEndPos() && pWord.getLevel() > sWord.getLevel()) {
                        sIt.remove();
                        break;
                    }

                    if (pWord.getStartPos() <= sWord.getStartPos() && pWord.getEndPos() >= sWord.getEndPos() && pWord.getLevel() > sWord.getLevel()) {
                        sIt.remove();
                        break;
                    }

                    ++i;
                }
            }

            Double maxLevel = 0.0D;
            StringBuilder badWords = new StringBuilder();
            Iterator var14 = sensitiveWords.iterator();

            while (var14.hasNext()) {
                Word word = (Word) var14.next();
                badWords.append(word.getWord()).append(",");
                if (word.getLevel() > maxLevel) {
                    maxLevel = word.getLevel();
                }
            }

            StringBuilder goodWords = new StringBuilder();
            Iterator var16 = positiveWords.iterator();

            Word word;
            while (var16.hasNext()) {
                word = (Word) var16.next();
                goodWords.append(word.getWord()).append(",");
            }

            var16 = sensitiveWords.iterator();

            while (var16.hasNext()) {
                word = (Word) var16.next();

                for (int i = 0; i < word.getPos().length; ++i) {
                    sb.replace(word.getPos()[i], word.getPos()[i] + 1, "" + replacement);
                }
            }

            FilteredResult result = new FilteredResult();
            result.setBadWords(badWords.toString());
            result.setGoodWords(goodWords.toString());
            result.setFilteredContent(sb.toString());
            result.setOriginalContent(sentence);
            result.setLevel(maxLevel);
            result.setHasSensiviWords(!sensitiveWords.isEmpty());
            return result;
        }
    }

    private static List<Word> simpleFilter2DictFindWords(StringBuilder sentence, Node dictTree) {
        List<Word> foundWords = new LinkedList<>();
        /*int start = false;
        int end = false;*/

        for (int i = 0; i < sentence.length(); ++i) {
            int start = i;
            int end = i;
            Node node = dictTree;
            Node lastFoundNode = null;

            for (int j = i; j < sentence.length(); ++j) {
                node = node.findChar(toLowerCase(sentence.charAt(j)));
                if (node == null) {
                    break;
                }

                if (node.isEnd()) {
                    end = j;
                    lastFoundNode = node;
                }
            }

            if (end > i) {
                int[] pos = new int[end - i + 1];

                for (int j = 0; j < pos.length; ++j) {
                    pos[j] = start + j;
                }

                Word word = new Word();
                word.setPos(pos);
                word.setStartPos(start);
                word.setEndPos(end);
                word.setLevel(lastFoundNode.getLevel());
                word.setWord(lastFoundNode.getWord());
                foundWords.add(word);
            }
        }

        return foundWords;
    }

    public static FilteredResult filterTextWithPunctuation(String originalString, char replacement) {
        return filter(filterPunctuation(originalString), replacement);
    }

    public static FilteredResult filterHtml(String originalString, char replacement) {
        return filter(filterPunctuationAndHtml(originalString), replacement);
    }

    private static char toLowerCase(char c) {
        return c >= 65 && c <= 90 ? (char) (c + 32) : c;
    }

    static {
        InputStream is = WordsFilterUtil.class.getResourceAsStream("/sensitive-words.dict");
        if (is == null) {
            is = WordsFilterUtil.class.getClassLoader().getResourceAsStream("/sensitive-words.dict");
        }

        try {
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");
            Properties prop = new Properties();
            prop.load(reader);
            Enumeration en = prop.propertyNames();

            while (en.hasMoreElements()) {
                String word = (String) en.nextElement();
                insertWord(tree, word, Double.valueOf(prop.getProperty(word)));
            }
        } catch (UnsupportedEncodingException var39) {
            var39.printStackTrace();
        } catch (IOException var40) {
            var40.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException var34) {
                    var34.printStackTrace();
                }
            }

        }

        String regex = "[\\pP\\pZ\\pS\\pM\\pC]";
        p = Pattern.compile(regex, 2);
        positiveTree = new Node();
        is = WordsFilterUtil.class.getResourceAsStream("/positive-words.dict");
        if (is == null) {
            is = WordsFilterUtil.class.getClassLoader().getResourceAsStream("/positive-words.dict");
        }

        try {
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");
            Properties prop = new Properties();
            prop.load(reader);
            Enumeration en = prop.propertyNames();

            while (en.hasMoreElements()) {
                String word = (String) en.nextElement();
                insertWord(positiveTree, word, Double.valueOf(prop.getProperty(word)));
            }
        } catch (UnsupportedEncodingException var36) {
            var36.printStackTrace();
        } catch (IOException var37) {
            var37.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException var35) {
                    var35.printStackTrace();
                }
            }
        }
    }

    private static class PunctuationOrHtmlFilteredResult {
        private String originalString;
        private StringBuilder filteredString;
        private ArrayList<Integer> charOffsets;

        private PunctuationOrHtmlFilteredResult() {
        }

        public String getOriginalString() {
            return this.originalString;
        }

        public void setOriginalString(String originalString) {
            this.originalString = originalString;
        }

        public StringBuilder getFilteredString() {
            return this.filteredString;
        }

        public void setFilteredString(StringBuilder filteredString) {
            this.filteredString = filteredString;
        }

        public ArrayList<Integer> getCharOffsets() {
            return this.charOffsets;
        }

        public void setCharOffsets(ArrayList<Integer> charOffsets) {
            this.charOffsets = charOffsets;
        }
    }
}
