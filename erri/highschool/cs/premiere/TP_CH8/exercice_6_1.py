list=[[1,2,3,4],[5,6,7,8],[9,10,11,12]]

for i in range(3):
    for j in range(3):
            print('{:>8d}'.format(list[i][j]), end="")
    print('{:>8d}'.format(list[i][3]))