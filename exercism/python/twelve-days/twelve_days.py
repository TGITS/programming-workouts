def recite_verse(verse_number):
    """Function that the verse from the twelve days of Christmas specified by its number"""
    
    verse_start = "On the {} day of Christmas my true love gave to me: "
    days = ["first", "second", "third", "fourth", "fifth", "sixth",
            "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"]
    gifts = [
        "a Partridge in a Pear Tree.",
        "two Turtle Doves,",
        "three French Hens, ",
        "four Calling Birds, ",
        "five Gold Rings, ",
        "six Geese-a-Laying, ",
        "seven Swans-a-Swimming, ",
        "eight Maids-a-Milking, ",
        "nine Ladies Dancing, ",
        "ten Lords-a-Leaping, ",
        "eleven Pipers Piping, ",
        "twelve Drummers Drumming, "
    ]

    verse = verse_start.format(days[verse_number-1])

    if verse_number == 1:
        verse = verse + gifts[0]
    else:
        for i in range(verse_number-1, 0, -1):
            verse = verse + gifts[i]
        verse = verse + " and " + gifts[0]

    return verse


def recite(start_verse, end_verse):
    """Function that gives as a list the verses from the twelve days of Christmas between start_verse and end_verse"""
    return [recite_verse(i) for i in range(start_verse, end_verse+1)]
