# Question 1 & 2, TP3
eleve=['Ponten','Pierre',3,209]
for elem in eleve:
    print(elem)

print()    

for i in range(0,len(eleve)):
    print(i,eleve[i])

print() 

for i,e in enumerate(eleve):
    print(i,e)
    
print() 

#Ex1 TP3
for i in range(1,20,2):
    print(i)

print()

# n impair peut s'écrire n = 2*k+1 avec k entier
for k in range(0,10):
    print(2*k+1)
