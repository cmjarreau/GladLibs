public class Runner {
    public static void main(String[] args) {
        //section1();

        //section2();

        section3();
    }

    public static void section1() {
        //WordFrequencies wf = new WordFrequencies();
        //wf.tester();

        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }

    public static void section2() {
        GladLib gl = new GladLib();
        gl.makeStory();
    }

    public static void section3() {
        //CodonCount cc = new CodonCount();
        //cc.tester();

        //WordsInFiles wif = new WordsInFiles();
        //wif.tester();

        GladLibMap glm = new GladLibMap();
        glm.makeStory();
    }
}
