import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class IOService {
    final static String inputFileName = "src/main/resources/input.txt";
    private final static String outputFileName = "src/main/resources/output.txt";

    List<String> readWords() throws IOException {
        FileReader reader = new FileReader(inputFileName);
        StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
        List<String> tokens = new ArrayList<>();

        int currentToken = streamTokenizer.nextToken();
        while (currentToken != StreamTokenizer.TT_EOF) {

            if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
                tokens.add(streamTokenizer.sval);
            }
            currentToken = streamTokenizer.nextToken();
        }
        reader.close();
        System.out.println(tokens);
        return tokens;
    }

    void writeOutput(String listToWrite) throws IOException {
        FileWriter writer = new FileWriter(outputFileName);
        writer.write(listToWrite);
        writer.write("\n");
        writer.close();
    }
}
