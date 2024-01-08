package main;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AnagramReader anagramReader = new AnagramReader();

        anagramReader.readAllWords();
        anagramReader.findAllAnagrams();
        anagramReader.printAllAnagramGroups();
        System.out.println(anagramReader.getNumberOfAnagramGroups());
    }
}