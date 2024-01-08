package main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class AnagramReader {
    ArrayList<String> listOfWords;
    HashMap<String, HashSet<String>> listOfAnagramGroups;

    public AnagramReader() {
        this.listOfWords = new ArrayList<>();
        this.listOfAnagramGroups = new HashMap<>();
    }

    public void readAllWords() {
        try {
            Scanner scanner = new Scanner(new FileInputStream("english.200MB"), StandardCharsets.UTF_8);
            scanner.useDelimiter("[^a-zA-Z]+");
            while (scanner.hasNext()) {
                String readWord = scanner.next().replaceAll("[^a-zA-Z]+", "").toLowerCase();
                if (readWord.length() >= 3) {
                    this.listOfWords.add(readWord);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void printAllAnagramGroups() {
        for (HashSet<String> anagramGroup : this.listOfAnagramGroups.values()) {
            System.out.println(anagramGroup.toString());
        }
    }

    public void findAllAnagrams() {
        for (String word : this.listOfWords) {
            HashSet<String> rightAnagramGroup = findRightAnagramGroup(word);
            rightAnagramGroup.add(word);
        }
        this.listOfAnagramGroups.values().removeIf(group -> group.size() < 2);
    }

    public int getNumberOfAnagramGroups() {
        return this.listOfAnagramGroups.size();
    }

    public HashSet<String> findRightAnagramGroup(String newWord) {
        char[] stringArray = newWord.toCharArray();
        Arrays.sort(stringArray);
        String sortedWord = new String(stringArray);

        if (this.listOfAnagramGroups.isEmpty()) {
            HashSet<String> newArraylist = new HashSet<>();
            this.listOfAnagramGroups.put(sortedWord, newArraylist);
            return newArraylist;
        }

        HashSet<String> foundAnagramGroup = this.listOfAnagramGroups.get(sortedWord);
        if (foundAnagramGroup == null) {
            HashSet<String> newArraylist = new HashSet<>();
            this.listOfAnagramGroups.put(sortedWord, newArraylist);
            return newArraylist;
        } else {
            return foundAnagramGroup;
        }
    }

    public void readAllWordsStream() {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("english.200MB"));
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }
}
