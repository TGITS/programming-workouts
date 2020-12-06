import fizzbuzz


def test_est_divisible_par_3():
    assert True == fizzbuzz.est_divisible_par_3(3)
    assert True == fizzbuzz.est_divisible_par_3(15)
    assert False == fizzbuzz.est_divisible_par_3(5)
    assert False == fizzbuzz.est_divisible_par_3(25)


def test_est_divisible_par_5():
    assert True == fizzbuzz.est_divisible_par_5(100)
    assert True == fizzbuzz.est_divisible_par_5(25)
    assert False == fizzbuzz.est_divisible_par_5(8)
    assert False == fizzbuzz.est_divisible_par_5(14)


def test_est_divisible_par_15():
    assert True == fizzbuzz.est_divisible_par_15(75)
    assert True == fizzbuzz.est_divisible_par_15(90)
    assert False == fizzbuzz.est_divisible_par_15(25)
    assert False == fizzbuzz.est_divisible_par_15(33)


def test_fizz():
    assert "Fizz" == fizzbuzz.fizzbuzz(3)
    assert "14" == fizzbuzz.fizzbuzz(14)


def test_buzz():
    assert "Buzz" == fizzbuzz.fizzbuzz(35)
    assert "28" == fizzbuzz.fizzbuzz(28)


def test_fizzbuzz():
    assert "FizzBuzz" == fizzbuzz.fizzbuzz(15)
    assert "31" == fizzbuzz.fizzbuzz(31)
