results = []

for i in range(1,46):
    if i%15 == 0:
        results.append('FizzBuzz')
    elif i%5 == 0:
        results.append('Fizz')
    elif i%3 == 0:
        results.append('Buzz')
    else:
        results.append(str(i))

print(','.join(results))
