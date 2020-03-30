def reverse(text):
    """
        Return the input string reverse.
        Recursive solution.
    """
    #The empty String translates to False in a boolean context in Python
    if text: 
        return reverse(text[1:]) + text[0]
    else:
        return text 

# An iterative solution probably more pythonic than the recursive one
# def reverse(text):
#     """
#         Return the input string reverse.
#         Iterative solution.
#     """
#     reversed = ""
#     for c in text:
#         reversed = c + reversed
#     return reversed    