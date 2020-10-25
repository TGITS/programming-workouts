lower_case_houses = ["tyrell", "stark",
                     "lannister", "tarly", "baratheon", "targaryen"]
print('Houses in lower case :', lower_case_houses)
capitalized_houses = [(s, s.capitalize()) for s in lower_case_houses]
print(capitalized_houses)
capitalize_houses_starting_by_t = [
    s.capitalize() for s in lower_case_houses if s[0] == 't']
print(capitalize_houses_starting_by_t)
fornames = ["Samwell", "Jon", "Brandon", "Tyrion",
            "Cersei", "Daenerys", "Sansa", "Arya"]
names = ["Snow", "Stark", "Lannister", "Tarly", "Targaryen"]
got_names = ["{} {}".format(forname,name) for forname in fornames for name in names]
print(got_names)
