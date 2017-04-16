import unittest

class TestCodersOfTheCaribbean_1(unittest.TestCase):
    def test_distance(self):
        a = Point(0,0)
        b = Point(3,4)

        self.assertTrue(5.0 == distance(a,b))


if __name__ == '__main__':
    unittest.main()