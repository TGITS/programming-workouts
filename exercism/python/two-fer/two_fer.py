def two_fer(name=None):
    sentence = "One for {}, one for me."
    if not name :
        return sentence.format("you")
    else:
        return sentence.format(name)
