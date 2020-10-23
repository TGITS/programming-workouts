bonjour = 'Hello World'

print("\n#######################\n")

print(bonjour)
print(reversed(bonjour))

print(bonjour)
print("".join(reversed(bonjour)))

print(bonjour)
for c in reversed(bonjour):
    print(c)

print([c for c in reversed(bonjour)])

print("\n#######################\n")

print(bonjour)
print(bonjour[::-1])
print([c for c in bonjour[::-1]])