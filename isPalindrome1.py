def isPalindrome(text):
    # compare each pair of letters in the string
    for i in range(int(len(text)/2)):
        # If the pair doesn't match, it's not a palindrome
        if(text[i] != text[-1 * (i+1)]):
            return False
    # if reach here then text is a palindrome
    return True