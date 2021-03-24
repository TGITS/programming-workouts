alphabet = "abcdefghijklmnopqrstuvwxyz"
key = "zyxwvutsrqponmlkjihgfedcba"


def encode(plain_text):
    ciphered_text = ""
    counter = 0
    for c in plain_text.lower().replace(' ','').replace(',',"").replace('.',''):
        if c in alphabet:
            ciphered_text += key[alphabet.index(c)]
        else:
            ciphered_text += c
        counter += 1
        if counter % 5 == 0:
            ciphered_text += ' '
    
    return ciphered_text


def decode(ciphered_text):
    return "".join([alphabet[key.index(c)] for c in ciphered_text.replace(' ','')])
