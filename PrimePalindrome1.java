class PrimePalindrome1 {
    public boolean isPalindrome(String text) {
        // keep track of positions we are comparing in the text
        int firstIndex = 0;
        int secondIndex = text.length() - 1;

        // compare each pair of letters in the string
        while (secondIndex > firstIndex) {
            // get the letters we want to check
            char firstChar = text.charAt(firstIndex);
            char secondChar = text.charAt(secondIndex);

            // If the pair doesn't match, it's not a palindrome
            if (firstChar != secondChar) {
                return false;
            }
            // go to the next 2 letters
            ++firstIndex;
            --secondIndex;
        }
        // if reach here then text is a palindrome
        return true;
    }
}