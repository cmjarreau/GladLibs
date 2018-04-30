import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> charactersList;
    private ArrayList<Integer> counts;

    public CharactersInPlay() {
        charactersList = new ArrayList<>();
        counts = new ArrayList<>();
    }

    public void update(String person) {
        // This method should update the two ArrayLists, adding the character’s name if
        // it is not already there, and counting this line as one speaking part for this person.
        int index = charactersList.indexOf(person);
        if (index == -1) {
            charactersList.add(person);
            counts.add(1);
        } else {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }

    public void findAllCharacters() {
        charactersList.clear();
        counts.clear();

        FileResource file = new FileResource();
        for (String line : file.lines()) {
            // For each line, if there is a period on the line, extract the possible name of the speaking part

            // if line contains a period (it could be a speaking person)
            if (line.contains(Character.toString('.'))) {
                // extract the speaking part
                String name = line.substring(0, line.indexOf('.'));
                update(name);
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        // int parameters named num1 and num2, where you can assume num1 should be less than or equal to num2
        // This method should print out the names of all those characters that have
        // exactly number speaking parts, where number is greater than or equal
        // to num1 and less than or equal to num2

        for (int i = 0; i < charactersList.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(charactersList.get(i) + "\t" + counts.get(i));
            }
        }
    }

    public void tester() {
        findAllCharacters();
        charactersWithNumParts(10, 15);
    }
}
