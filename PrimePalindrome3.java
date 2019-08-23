import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class PrimePalindrome3 {
    public List<List<String>> partition(String text) {
        Map<Integer, List<Integer>> palindromeMapping = findPalindromes(text);
        return constructPartitions(text, palindromeMapping, 0, new ArrayList<>());
    }
    
    private Map<Integer, List<Integer>> findPalindromes(String text) {
        // map of characters to each position that character appears in the string
        Map<Character, List<Integer>> letterMapping = new HashMap<>();
        // map of starting positions of palindromes to each ending position palindrome
        // ex for "indeed" => {0: [0], 1: [1], 2: [2, 5], 3: [3, 4], 4: [4], 5: [5]}
        Map<Integer, List<Integer>> palindromeMapping = new HashMap<>();
        
        for (int currentPosition= 0; currentPosition < text.length(); currentPosition++) {
            char letter = text.charAt(currentPosition);
            // All of the other places this letter has been so far
            List<Integer> previousPositionList = letterMapping.getOrDefault(letter, new ArrayList<>());

            for(int previousPosition : previousPositionList) {
                if (currentPosition - previousPosition == 1) {
                    // is a two character palindrome
                    palindromeMapping.get(previousPosition).add(currentPosition);
                } else if (palindromeMapping.get(previousPosition + 1).contains(currentPosition - 1)) {
                    // if there is a palindrome between the two matching characters
                    palindromeMapping.get(previousPosition).add(currentPosition);
                }
            }
            // There is a single character palindrome at this position
            palindromeMapping.put(currentPosition, new ArrayList<>(Arrays.asList(currentPosition)));
            // Add the current letter and position to the letter mapping
            previousPositionList.add(currentPosition);
            letterMapping.put(letter, previousPositionList);
        }
        return palindromeMapping;
    }

    private List<List<String>> constructPartitions(String text,
                                                   Map<Integer, List<Integer>> palindromeMapping,
                                                   int startingPosition,            // Where to start adding more palindromes
                                                   List<String> palindromesSoFar) { // palindromes we made on the way to startingPosition
        
        // if we have navigated to the end of the string, return the palindromes we used to get here
        if (startingPosition == text.length()) {
            return Arrays.asList(palindromesSoFar); // Wrap List<String> in List<> 
        }

        List<List<String>> partitionList = new ArrayList<>();

        // Iterate over each palindrome that starts at the current position
        for (int endingPosition : palindromeMapping.get(startingPosition)) {
            // Add that palindrome to list of palindromes we've used to get here
            List<String> newPalindromesSoFar = new ArrayList<>(palindromesSoFar);
            newPalindromesSoFar.add(text.substring(startingPosition, endingPosition + 1));
            // Add the rest of the palindromes that come after here
            partitionList.addAll(constructPartitions(text, palindromeMapping, endingPosition + 1, newPalindromesSoFar));
        }
        return partitionList;
    }
}