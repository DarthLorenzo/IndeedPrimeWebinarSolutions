"""
    A solution to this problem: https://leetcode.com/problems/valid-palindrome/
"""
def is_palindrome(text):
    # compare each pair of letters in the string
    for i in range(int(len(text)/2)):
        # If the pair doesn't match, it's not a palindrome
        if(text[i] != text[-1 * (i+1)]):
            return False
    # if reach here then text is a palindrome
    return True

def is_word_palindrome(text):
    lowercase_text = text.lower()
    letter_only_letters = [char for char in lowercase_text if 'a' < char and char < 'z']
    return is_palindrome(letter_only_letters)