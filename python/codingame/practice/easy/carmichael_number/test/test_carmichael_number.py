import unittest
import sys
import carmichael_number


class TestCarmichaelNumber(unittest.TestCase):
    def test_is_prime_number(self):
        prime_numbers = [
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
            67, 71, 73, 79, 83, 89, 97
        ]
        for number in prime_numbers:
            self.assertTrue(carmichael_number.is_prime(number))

    def test_a_prime_is_not_a_carmichael_number(self):
        prime_numbers = [
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
            67, 71, 73, 79, 83, 89, 97
        ]
        for number in prime_numbers:
            self.assertFalse(carmichael_number.is_carmichael_number(number))

    def test_is_a_carmichael_number(self):
        carmichael_numbers = [
            561, 1105, 1729, 2465, 2821, 6601, 8911, 41041, 62745, 63973,
            75361, 101101, 126217, 172081, 188461, 278545, 340561, 825265,
            321197185, 5394826801, 232250619601, 9746347772161
        ]
        for number in carmichael_numbers:
            self.assertTrue(carmichael_number.is_carmichael_number(number))

    def test_poulet_number_not_carmichael_number(self):
        poulet_numbers = [
            341, 645, 1387, 1905, 2047, 2701, 3277, 4033, 4369, 4371, 4681,
            5461, 7957, 8321, 8481, 10261, 11305, 12801, 13741, 13747, 13981,
            14491, 15709, 16705, 18705, 18721, 19951, 23001, 23377, 25761,
            30121, 30889, 31417, 31609, 31621, 33153, 34945, 35333, 39865
        ]
        for number in poulet_numbers:
            self.assertFalse(carmichael_number.is_carmichael_number(number))


if __name__ == '__main__':
    unittest.main()
