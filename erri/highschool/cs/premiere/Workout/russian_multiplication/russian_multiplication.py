def even(x:int) -> bool: 
    return x%2 == 0

def multiply(a: int, b:int) -> int : 
    x = 0
    y = 0
    product = 0
    if a > b:
        x = b
        y = a
    else:
        x = a
        y = b
    
    while x > 0:
        if even(x):
            x = x / 2
            y = y * 2
        else:
            x = x - 1
            product = product + y
    
    return product

if __name__ == "__main__":
    print("7x25 =", str(multiply(7,25)))
    print("25x7 =", str(multiply(25,7)))
    print("5x5 =", str(multiply(5,5)))
    print("6x100 = ", str(multiply(6,100)))