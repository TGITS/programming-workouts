import unittest
import sys
from rugby_score import Decomposition
from rugby_score import decompose

class TestRugbyScore(unittest.TestCase):

    def test_decompose_0(self):
        score = 0
        expected_decomposition = [ ]
        self.assertEqual(decompose(score), expected_decomposition)

    def test_decompose_3(self):
        score = 3
        expected_decomposition = [ Decomposition(penalities=1) ]
        self.assertEqual(decompose(score), expected_decomposition)

    def test_decompose_6(self):
        score = 6
        expected_decomposition = [ Decomposition(penalities=2) ]
        self.assertEqual(decompose(score), expected_decomposition)

    def test_decompose_12(self):
        score = 12
        expected_decomposition = [ Decomposition(penalities=4), Decomposition(tries=1,transformations=1) ]
        decomposition = decompose(score)
        print("Expected : {}".format(expected_decomposition),file=sys.stderr)
        print("Calculated : {}".format(decomposition),file=sys.stderr)
        self.assertEqual(expected_decomposition, decomposition)

    def test_decompose_15(self):
        score = 15
        expected_decomposition = [ Decomposition(penalities=5), Decomposition(tries=1,transformations=1,penalities=1), Decomposition(tries=3) ]
        decomposition = decompose(score)
        print("Expected : {}".format(expected_decomposition),file=sys.stderr)
        print("Calculated : {}".format(decomposition),file=sys.stderr)
        self.assertEqual(expected_decomposition, decomposition)

if __name__ == '__main__':
    unittest.main()
