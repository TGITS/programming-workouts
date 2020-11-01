forname = ('Jon', 'Arya', 'Jaimy')
family_name = ('Snow', 'Stark', 'Lannister')
print(list(zip(forname,family_name)))
print(list(zip(*list(zip(forname,family_name)))))