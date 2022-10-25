import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class VowelsDeterminerTest {
    VowelsDeterminer determiner = new VowelsDeterminer();

    @Test
    public void testCalculateAverageNumberOfVowelsWithDifferentTypesOfWord() {
        List<String> input = Arrays.asList("elephant", "Loves", "WATERMELON!");
        String expectedString = """
                {{[a, e], 8}->3.0},\s
                {{[e, o], 5}->2.0},\s
                {{[a, e, o], 10}->4.0}""";
        assertEquals(determiner.calculateAverageNumberOfVowels(input),expectedString);
    }

    @Test
    public void testCalculateAverageNumberOfVowelsWithEmptyInput() {
        List<String> input = List.of();
        String expectedString = "";
        assertEquals(determiner.calculateAverageNumberOfVowels(input),expectedString);
    }
}