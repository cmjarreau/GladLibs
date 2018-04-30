import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CodonCount {

    private HashMap<String, Integer> codonCount;

    public CodonCount() {
        codonCount = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        // This method will build a new map of codons mapped to their counts from
        // the string dna with the reading frame with the position start (a value of 0, 1, or 2).

        // You will call this method several times, so make sure your map is empty before building it.
        codonCount.clear();

        // example DNA = “CGTTCAAGTTCAA”.
        // 1st = CGT TCA AGT TCA
        // 2nd = GTT CAA GTT CAA
        // 3rd = TTC AAG TTC

        for (int i = start; i < dna.length()-2; i += 3) {
            //System.out.println(dna.substring(i, i+3));
            String currentCodon = dna.substring(i, i+3);

            //System.out.println("\t" + currentCodon);
            if (codonCount.keySet().contains(currentCodon)) {
                codonCount.put(currentCodon, (codonCount.get(currentCodon) + 1));
            } else {
                codonCount.put(currentCodon, 1);
            }

        }
    }

    public String getMostCommonCodon() {
        int max = 0;
        String maxCodon = "";
        for (String codon: codonCount.keySet()) {
            int currentVal = codonCount.get(codon);
            if (currentVal > max) {
                max = currentVal;
                maxCodon = codon;
            }
        }
        return maxCodon;
    }

    public void printCodonCounts(int start, int end) {
        System.out.println(codonCount.keySet().size());
        for (String codon: codonCount.keySet()) {
            if(codonCount.get(codon) > start && codonCount.get(codon) < end) {
                System.out.println(codon + "\t" + codonCount.get(codon));
            }
        }
    }

    public void tester() {
        FileResource file = new FileResource();
        String fileContents = file.asString().toUpperCase().trim();

        buildCodonMap(0, fileContents);
        for (String codon: codonCount.keySet()) {
            System.out.println(codon + " " + codonCount.get(codon));
        }

        String maxCodon = getMostCommonCodon();
        System.out.println("most codon: " + maxCodon);

        printCodonCounts(6,8);

        System.out.println("------------------------");
    }
}
