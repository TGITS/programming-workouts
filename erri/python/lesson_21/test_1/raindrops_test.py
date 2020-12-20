import raindrops


def test_est_divisible_par_3():
    assert True == raindrops.est_divisible_par_3(3)
    assert True == raindrops.est_divisible_par_3(15)
    assert True == raindrops.est_divisible_par_3(21)
    assert False == raindrops.est_divisible_par_3(5)
    assert False == raindrops.est_divisible_par_3(14)
    assert False == raindrops.est_divisible_par_3(25)
    assert False == raindrops.est_divisible_par_3(35)


def test_est_divisible_par_5():
    assert True == raindrops.est_divisible_par_5(100)
    assert True == raindrops.est_divisible_par_5(25)
    assert True == raindrops.est_divisible_par_5(30)
    assert True == raindrops.est_divisible_par_5(35)
    assert False == raindrops.est_divisible_par_5(8)
    assert False == raindrops.est_divisible_par_5(14)
    assert False == raindrops.est_divisible_par_5(54)


def test_est_divisible_par_7():
    assert True == raindrops.est_divisible_par_7(7)
    assert True == raindrops.est_divisible_par_7(14)
    assert True == raindrops.est_divisible_par_7(21)
    assert True == raindrops.est_divisible_par_7(35)
    assert False == raindrops.est_divisible_par_7(8)
    assert False == raindrops.est_divisible_par_7(15)
    assert False == raindrops.est_divisible_par_7(9)


def test_pling():
    assert "Pling" == raindrops.convertir(3)
    assert "Pling" == raindrops.convertir(9)
    assert "Pling" == raindrops.convertir(27)


def test_plang():
    assert "Plang" == raindrops.convertir(5)
    assert "Plang" == raindrops.convertir(25)


def test_plong():
    assert "Plong" == raindrops.convertir(7)
    assert "Plong" == raindrops.convertir(14)
    assert "Plong" == raindrops.convertir(28)


def test_plingplangplong():
    assert "PlingPlang" == raindrops.convertir(15)
    assert "PlingPlang" == raindrops.convertir(30)
    assert "PlingPlong" == raindrops.convertir(21)
    assert "PlangPlong" == raindrops.convertir(35)
    assert "PlingPlangPlong" == raindrops.convertir(3*5*7)
    assert "PlingPlangPlong" == raindrops.convertir(2*3*5*7*11)


def test_juste_un_nombre():
    assert "2" == raindrops.convertir(2)
    assert "4" == raindrops.convertir(4)
    assert "11" == raindrops.convertir(11)
    assert "13" == raindrops.convertir(13)
