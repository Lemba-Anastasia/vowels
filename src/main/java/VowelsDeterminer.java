import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Locale;
import java.util.List;

import java.util.stream.Collectors;

public class VowelsDeterminer {
    private final Set<String> vowels = new HashSet<>(Arrays.asList("a", "e", "i", "o", "u"));
    private final List<OutputItemStructure> itemStructures = new ArrayList<>();

    String calculateAverageNumberOfVowels(List<String> inputWords) {
        List<String> setOfCharactersOfWord;
        Set<String> vowelsInOneWord;//internal intersection from set of vowels and each word
        for (String word : inputWords) {
            //here we convert the word to lowercase, and delete special symbols
            word = word.toLowerCase(Locale.ROOT).replaceAll("[^a-zA-Z]", "");

            OutputItemStructure itemStructure = new OutputItemStructure();
            setOfCharactersOfWord = Arrays.stream(word.split("")).collect(Collectors.toList());
            vowelsInOneWord = new HashSet<>(setOfCharactersOfWord);
            vowelsInOneWord.retainAll(vowels);

            itemStructure.setVowelsInOneWord(vowelsInOneWord);
            itemStructure.setWordLength(word.length());
            //adding new output structure or update they parameters if exist
            if (itemStructures.size() == 0) {
                calculateParametersForAveraging(setOfCharactersOfWord, itemStructure);
                itemStructures.add(itemStructure);
            } else {
                for (OutputItemStructure i : itemStructures) {
                    if (i.equals(itemStructure)) {
                        calculateParametersForAveraging(setOfCharactersOfWord, i);
                    } else {
                        calculateParametersForAveraging(setOfCharactersOfWord, itemStructure);
                        itemStructures.add(itemStructure);
                    }
                    break;
                }
            }
        }
        //printing output
        for (OutputItemStructure each :
                itemStructures) {
            System.out.println(each);
        }


        return itemStructures.stream().map(OutputItemStructure::toString).collect(Collectors.joining(", \n"));
    }

    //calculate how many vowels the word have
    private void calculateParametersForAveraging(List<String> setOfCharactersOfWord, OutputItemStructure i) {
        int addingAmount = 0;
        for (String literal : setOfCharactersOfWord) {
            if (vowels.contains(literal)) addingAmount++;
        }
        i.addFrequency(addingAmount);
    }

    static class OutputItemStructure {
        Set<String> vowelsInOneWord;
        int wordLength;
        int frequencyOfOccurrence = 0;
        int frequencyOfOccurrencePerWords = 0;

        @Override
        public String toString() {
            float averageFrequency = (float) frequencyOfOccurrence / frequencyOfOccurrencePerWords;
            return "{{" + vowelsInOneWord + ", " + wordLength + "}->" + averageFrequency + "}";
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof OutputItemStructure) {
                OutputItemStructure comparedObject = (OutputItemStructure) obj;
                return this.wordLength == comparedObject.getWordLength() &&
                        this.vowelsInOneWord.equals(comparedObject.getVowelsInOneWord());
            } else {
                return false;
            }
        }

        void addFrequency(int addingAmount) {
            frequencyOfOccurrence = frequencyOfOccurrence + addingAmount;
            frequencyOfOccurrencePerWords++;
        }

        public void setWordLength(int wordLength) {
            this.wordLength = wordLength;
        }

        public void setVowelsInOneWord(Set<String> vowelsInOneWord) {
            this.vowelsInOneWord = vowelsInOneWord;
        }

        public int getWordLength() {
            return wordLength;
        }

        public Set<String> getVowelsInOneWord() {
            return vowelsInOneWord;
        }
    }
}
