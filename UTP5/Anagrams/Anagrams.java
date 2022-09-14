/**
 *
 *  @author Kamiński Patryk S18610
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Anagrams {

    private List <String> wordsList;
    private List <List> lists;

    public Anagrams(String allWords) {

        wordsList = new ArrayList <String>();
        File file = new File(allWords);
        BufferedReader bufferedReader;
        String tempLine;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((tempLine = bufferedReader.readLine()) != null) {
                String[] words = tempLine.split(" ");
                for (int i = 0; i < words.length; i++) {
                    wordsList.add(words[i]);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Anagrams constructor FileNotFoundException");
        } catch (IOException exx) {
            System.err.println("Anagrams constructor IOException");
        }
    }

    public List<List> getSortedByAnQty() {

        lists = new ArrayList<List>();
        List <String> l = new ArrayList<String>();

        for (int i = 0; i < wordsList.size(); i++) {
            if (!l.contains(wordsList.get(i))) {
                List<String> tmp = new ArrayList<String>();
                for (int j = 0; j < wordsList.size(); j++) {
                    if (czyAnagram(wordsList.get(i), wordsList.get(j))) {
                        l.add(wordsList.get(j));
                        tmp.add(wordsList.get(j));
                    }
                }
                lists.add(tmp);
            }
        }

        lists.sort((w1, w2) -> w2.size() - w1.size());
        return lists;
    }

    public String getAnagramsFor(String next) {

        for (int i = 0; i < lists.size(); i++) {
            List<String> tmp = new ArrayList<String>(lists.get(i));
            for (int j = 0; j < tmp.size(); j++) {
                if(tmp.get(j).equals(next)) {
                    tmp.remove(j);
                    return next + ": " + tmp;
                }
            }
        }
        return "";
    }

    public boolean czyAnagram(String str1, String str2) {
        char[] word1 = str1.replaceAll("[\\s]", "").toCharArray();
        char[] word2 = str2.replaceAll("[\\s]", "").toCharArray();
        Arrays.sort(word1);
        Arrays.sort(word2);
        return Arrays.equals(word1, word2);
    }

}