import string
import secrets


class Cipher:
    def __init__(self, key=None):
        self.alphabet = string.ascii_lowercase
        self.alphabet_size = len(self.alphabet)
        default_random_key_size = 100
        self.key = key if key else ''.join(
            secrets.choice(self.alphabet) for _ in range(default_random_key_size))

    def get_key_element(self, i):
        return self.key[i % len(self.key)]

    def encode_char_at_index(self, i, c):
        return chr(((ord(c) +
                     ord(self.get_key_element(i)) - ord(self.alphabet[0])) - ord(self.alphabet[0])) % self.alphabet_size + ord(self.alphabet[0]))

    def encode(self, text):
        encoded = ""
        for i, c in enumerate(text.lower()):
            encoded += self.encode_char_at_index(i,c)
        return encoded

    def decode(self, text):
        decoded = ""
        for i, c in enumerate(text.lower()):
            decoded += chr(((ord(c) -
                             ord(self.get_key_element(i))) % self.alphabet_size + ord(self.alphabet[0])))
        return decoded
