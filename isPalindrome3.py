"""
    A solution to this problem: https://leetcode.com/problems/palindrome-partitioning/
"""
class PrimePalindrome:

    @staticmethod
    def find_palindrome_partitions(text):
        palindrome_mapping = PrimePalindrome.find_palindromes(text)
        return PrimePalindrome.contstruct_partitions(text, palindrome_mapping, 0, [])

    @staticmethod
    def find_palindromes(text):
        # dictionary mapping characters to each position that character appears in the string
        letter_mapping = {}
        # return value mapping starting positions of palindromes to each ending position palindrome
        # ex for "indeed" => {0: [0], 1: [1], 2: [2, 5], 3: [3, 4], 4: [4], 5: [5]}
        palindrome_mapping = {}
        for current_position, letter in enumerate(text):
            # All of the other places this letter has been so far
            previous_position_list = letter_mapping.get(letter, [])
            for previous_position in previous_position_list:
                if current_position - previous_position == 1:
                    # is a two character palindrome
                    palindrome_mapping[previous_position].append(current_position)
                elif current_position - 1 in palindrome_mapping[previous_position + 1]:
                    # if there is a palindrome between the two matching characters
                    palindrome_mapping[previous_position].append(current_position)
            # There is a single character palindrome at this position
            palindrome_mapping[current_position] = [current_position]
            # Add the current letter and position to the letter mapping
            letter_mapping[letter] = previous_position_list + [current_position]

        return palindrome_mapping

    @staticmethod
    def contstruct_partitions(text, palindrome_mapping, starting_position, palindromes_so_far):
        if starting_position == len(text):
            # if we have navigated to the end of the string, return the palindromes we used to get here
            return [palindromes_so_far] # Wrap list of strings in list

        partition_list = []

        # Iterate over each palindrome that starts at the current position
        for ending_position in palindrome_mapping[starting_position]:
            # Add that palindrome to list of palindromes we've used to get here
            new_palindromes_so_far = palindromes_so_far + [text[starting_position:ending_position+1]]
            # Add the rest of the palindromes that come after here
            partition_list = partition_list + PrimePalindrome.contstruct_partitions(text, palindrome_mapping, ending_position+1, new_palindromes_so_far)

        return partition_list