import string
import secrets


class Cipher:
    def __init__(self, key=None):
        self.alphabet = string.ascii_lowercase
        self.alphabet_size = len(self.alphabet)
        default_random_key_size = 100
        self.key = (
            key
            if key
            else "".join(
                secrets.choice(self.alphabet) for _ in range(default_random_key_size)
            )
        )

    def key_value_at(self, i):
        return self.key[i % len(self.key)]

    def relative_key_value_position(self, i):
        return ord(self.key_value_at(i)) - self.start_of_alphabet_position()

    def start_of_alphabet_position(self):
        return ord(self.alphabet[0])

    def relative_char_position(self, c):
        return ord(c) - self.start_of_alphabet_position()

    def encode_char_at_index(self, i, c):
        return chr(
            (self.relative_char_position(c) + self.relative_key_value_position(i))
            % self.alphabet_size
            + self.start_of_alphabet_position()
        )

    def encode(self, text):
        return "".join(
            self.encode_char_at_index(i, c) for i, c in enumerate(text.lower())
        )

    def decode_char_at_index(self, i, c):
        return chr(
            (
                # Could I have been written : ( self.get_relative_char_position(c) - self.get_relative_key_element_position(i))
                # But can be simplified to this expression
                (ord(c) - ord(self.key_value_at(i))) % self.alphabet_size
                + self.start_of_alphabet_position()
            )
        )

    def decode(self, text):
        return "".join(
            self.decode_char_at_index(i, c) for i, c in enumerate(text.lower())
        )
