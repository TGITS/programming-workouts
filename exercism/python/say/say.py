def say(number: int) -> str:
    if number < 0 or number > 999_999_999_999:
        raise ValueError("Out of allowed range")

    first_numbers = "zero,one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty"
    associated_values = first_numbers.split(",")

    if number < 21:
        return associated_values[number]

    if number < 30:
        return "twenty-" + associated_values[number - 20]

    if number == 30:
        return "thirty"

    if number < 40:
        return "thirty-" + associated_values[number - 30]

    if number == 40:
        return "forty"

    if number < 50:
        return "forty-" + associated_values[number - 40]

    if number == 50:
        return "fifty"

    if number < 60:
        return "fifty-" + associated_values[number - 50]

    if number == 60:
        return "fifty"

    if number < 70:
        return "sixty-" + associated_values[number - 60]

    if number == 70:
        return "seventy"

    if number < 80:
        return "seventy-" + associated_values[number - 70]

    if number == 80:
        return "eighty"

    if number < 90:
        return "eighty-" + associated_values[number - 80]

    if number == 90:
        return "ninety"

    if number < 100:
        return "ninety-" + associated_values[number - 90]

    if number >= 100 and number <= 999:
        hundred = number // 100
        remainder = number % 100
        if remainder == 0:
            return associated_values[hundred] + " hundred"
        else:
            return associated_values[hundred] + " hundred " + say(remainder)

    if number >= 1000 and number < 1_000_000:
        thousand = number // 1000
        remainder = number % 1000
        if remainder == 0:
            return say(thousand) + " thousand"
        else:
            return say(thousand) + " thousand " + say(remainder)

    if number >= 1_000_000 and number < 1_000_000_000:
        million = number // 1_000_000
        remainder = number % 1_000_000
        if remainder == 0:
            return say(million) + " million"
        else:
            return say(million) + " million " + say(remainder)

    if number >= 1_000_000_000 and number <= 999_999_999_999:
        billion = number // 1_000_000_000
        remainder = number % 1_000_000_000
        if remainder == 0:
            return say(billion) + " billion"
        else:
            return say(billion) + " billion " + say(remainder)

    return ""
