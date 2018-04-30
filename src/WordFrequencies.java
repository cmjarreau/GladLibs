import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    // Create two private variables. One is called myWords and should
    // be an ArrayList of type String to store unique words from a file,
    // and one is called myFreqs and should be an ArrayList of type
    // Integer. The kth position in myFreqs should represent the number
    // of times the kth word in myWords occurs in the file.

    // stores unique words in a file
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    public void findUnique() {
        // This method should first clear both myWords and myFreqs, using the .clear() method.
        myWords.clear();
        myFreqs.clear();

        // Then it selects a file and then iterates over every word in the file,
        // putting the unique words found into myWords
        FileResource file = new FileResource();
        for (String word: file.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            // For each word in the kth position of myWords, it puts the count of how many
            // times that word occurs from the selected file into the kth position of myFreqs,
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }

    //Write the method findIndexOfMax that has no parameters
    public int findIndexOfMax() {
        // This method returns an int that is the index location of the largest value in myFreqs.
        // If there is a tie, then return the first such value.
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > maxValue) {
                maxValue = myFreqs.get(i);
                maxIndex = myFreqs.indexOf(maxValue);
                System.out.println("max value: " + maxValue + " at index: " + maxIndex + " and word is: " + myWords.get(maxIndex));
            }
        }
        return maxIndex;
    }

    // Write a void tester method that has no parameters.
    public void tester() {
        // This method should call findUnique.
        findUnique();
        // Then print out the number of unique words,
        System.out.println("number of unique words: " + myWords.size());
        // for each unique word, print the frequency of each word and the word, as was demonstrated in the lesson.
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }

        int maxIndex = findIndexOfMax();

        System.out.println("The word that occurs most often " +
                "and its count are: " + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
}
