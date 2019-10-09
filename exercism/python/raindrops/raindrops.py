textByFactors = {
    3 : "Pling",
    5 : "Plang",
    7 : "Plong"
}

def convert(number):
    result = []
    
    for k,v in textByFactors.items():
        if number % k == 0:
            result.append(v)
       
    return "".join(result) if result else str(number)