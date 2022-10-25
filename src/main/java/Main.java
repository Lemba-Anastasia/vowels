import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        IOService ioService = new IOService();
        VowelsDeterminer determiner = new VowelsDeterminer();
        String output = determiner.calculateAverageNumberOfVowels(ioService.readWords());
        ioService.writeOutput(output);
    }

}