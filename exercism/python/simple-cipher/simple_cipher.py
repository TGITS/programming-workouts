import string
import secrets


class Cipher:
    def __init__(self, key=None):
        self.alphabet = string.ascii_lowercase
        self.key = key if key else ''.join(
            secrets.choice(self.alphabet) for _ in range(100))

    def encode(self, text):
        encoded = ""
        for i, c in enumerate(text.lower()):
            encoded += chr(((ord(c) +
                             ord(self.key[i % len(self.key)]) - ord('a')) - ord('a')) % 26 + ord('a'))
        return encoded

    def decode(self, text):
        decoded = ""
        for i, c in enumerate(text.lower()):
            decoded += chr(((ord(c) -
                             ord(self.key[i] % len(self.key)) - ord('a')) % 26 + ord('a')))
        return decoded
