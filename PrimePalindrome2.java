import java.lang.StringBuilder

/**
 * A solution to this problem: https://leetcode.com/problems/valid-palindrome/
 */
class PrimePalindrome2 {
    public boolean isWordPalindrome(String text) {
        String lowercaseText = text.toLowerCase();
        StringBuilder letterOnlyString = new StringBuilder();

        for (char letter : lowercaseText.toCharArray()) {
            if ('a' <= letter && letter <= 'z') {
                letterOnlyString.append(letter);
            }
        }

        return isPalindrome(letterOnlyString.toString());
    }
    
    public boolean isPalindrome(String text) {
        int firstIndex = 0;
        int secondIndex = text.length() - 1;

        while (secondIndex > firstIndex) {
            char firstChar = text.charAt(firstIndex);
            char secondChar = text.charAt(secondIndex);

            if (firstChar != secondChar) {
                return false;
            }
            ++firstIndex;
            --secondIndex;
        }
        return true;
    }
}