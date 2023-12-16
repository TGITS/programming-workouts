from tri import tri,minimum

def test_tri():
    tableau=[10,1,7,9,8]
    tableau_attendu=[1,7,8,9,10]
    assert tableau_attendu == tri(tableau)

def test_min():
    tableau=[10,1,7,9,8]
    minimum_attendu=1
    assert minimum_attendu==minimum(tableau)