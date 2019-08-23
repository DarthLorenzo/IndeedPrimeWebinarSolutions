def isPalindrome(text):
    for i in range(int(len(text)/2)):
        if(text[i] != text[-1 * (i+1)]):
            return False
    return True

def isWordPalindrome(text):
    lowercaseText = text.lower()
    letterOnlyLetters = [char for char in lowercaseText if 'a' < char and char < 'z']
    return isPalindrome(letterOnlyLetters)