import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;

    private ArrayList<String> usedWords;
    private int wordsReplaced;

    private Random myRandom;

    private ArrayList<String> consideredList;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source){
        myMap = new HashMap<>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (int i = 0; i < categories.length; i++) {
            ArrayList<String> categoryList = new ArrayList<>();
            categoryList = readIt(source + "/" + categories[i] + ".txt");
            myMap.put(categories[i], categoryList);
        }

        usedWords = new ArrayList<>();
        wordsReplaced = 0;
        consideredList = new ArrayList<>();

    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (!consideredList.contains(label)) {
            consideredList.add(label);
        }

        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (myMap.keySet().contains(label)) {
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);

        String sub = getSubstitute(w.substring(first+1,last));

        while (usedWords.indexOf(sub) != -1) {
            sub = getSubstitute(w.substring(first+1,last));
            wordsReplaced++;
            //System.out.println("sub = " + sub + " " + wordsReplaced);
        }

        usedWords.add(sub);

        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private void totalWordsInMap() {
        int count = 0;
        for (String key: myMap.keySet()) {
            count = count + myMap.get(key).size();
        }
        System.out.println("Total words: " + count);
    }

    private void totalWordsInMap2() {
        int count = 0;
        for (String key: myMap.keySet()) {
            ArrayList<String> words = myMap.get(key);
            count += words.size();
        }
        System.out.println("Total words: " + count);
    }

    private void totalWordsConsidered() {
        int count = 0;
        for (String key: consideredList) {
            if (!key.equals("number")) {
                count = count + myMap.get(key).size();
            }
        }
        System.out.println("Total considered words: " + count);
    }

    public void makeStory(){
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nnumber of words replaced: " + wordsReplaced);
        totalWordsInMap();
        totalWordsInMap2();
        totalWordsConsidered();
    }
}
