TEXT_BY_FACTORS = {
    3 : "Pling",
    5 : "Plang",
    7 : "Plong"
}

def convert(number):
    result = [v for k,v in sorted(TEXT_BY_FACTORS.items()) if number % k == 0]
    return "".join(result) if result else str(number)