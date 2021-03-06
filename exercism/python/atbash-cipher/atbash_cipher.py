alphabet = "abcdefghijklmnopqrstuvwxyz"
key = "zyxwvutsrqponmlkjihgfedcba"


def encode(plain_text):
    ciphered_text = ""
    counter = 0
    for c in plain_text.lower().replace(' ', '').replace(',', "").replace('.', ''):
        if counter != 0 and counter % 5 == 0:
            ciphered_text += ' '
        if c in alphabet:
            ciphered_text += key[alphabet.index(c)]
        else:
            ciphered_text += c
        counter += 1

    return ciphered_text


def decode(ciphered_text):
    plain_text = ""
    for c in ciphered_text.replace(' ', ''):
        if c in key:
            plain_text += alphabet[key.index(c)]
        else:
            plain_text += c

    return plain_text
