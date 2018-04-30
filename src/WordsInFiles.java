import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.*;

public class WordsInFiles {

    // maps a word to an ArrayList of filenames
    private HashMap<String, ArrayList<String>> wordsToFileMap;


    public WordsInFiles() {
        wordsToFileMap = new HashMap<>();
    }

    private void addWordsFromFile(File file) {
        String filename = file.getName();

        FileResource fr = new FileResource(file);
        String words = fr.asString().trim();
        String[] tmp = words.split("[\\n\\r\\s]+");


        for (int i = 0; i < tmp.length; i++) {

            if (!wordsToFileMap.keySet().contains(tmp[i])) {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(filename);
                wordsToFileMap.put(tmp[i], newList);
            } else {
                if (!wordsToFileMap.get(tmp[i]).contains(filename)) {
                    wordsToFileMap.get(tmp[i]).add(filename);
                }
            }
        }
    }

    private void buildWordFileMap() {
        wordsToFileMap.clear();

        DirectoryResource dr = new DirectoryResource();
        for (File file: dr.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    private int maxNumber() {
        int maxNumber = 0;

        for (String key : wordsToFileMap.keySet()) {
            int currentNumber = wordsToFileMap.get(key).size();
            if (currentNumber > maxNumber) {
                maxNumber = currentNumber;
            }
        }
        return maxNumber;
    }

    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordsList = new ArrayList<>();
        for (String key : wordsToFileMap.keySet()) {
             if (wordsToFileMap.get(key).size() == number) {
                 wordsList.add(key);
             }
        }
        return wordsList;
    }

    private void printFilesIn(String word) {
        ArrayList<String> list = new ArrayList<>();
        list = wordsToFileMap.get(word);

        if (list != null) {
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
    }

    public void tester() {
        buildWordFileMap();

        //for (String key : wordsToFileMap.keySet()) {
        //    System.out.print(key + "  ");
        //    System.out.println(wordsToFileMap.get(key));
        //}

        int max = maxNumber();
        System.out.println("the greatest number of files a word appears in is: " + max);

        ArrayList<String> wordsList = wordsInNumFiles(4);
        //System.out.println(wordsList);
        System.out.println(wordsList.size());

        System.out.println("laid");
        printFilesIn("laid");

        System.out.println("tree");
        printFilesIn("tree");

    }
}
