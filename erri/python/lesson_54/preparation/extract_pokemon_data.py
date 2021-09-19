import json
import pathlib
import logging

types_input_file_path = pathlib.Path("types.json")
types_output_file_path = pathlib.Path("types.txt")

with types_input_file_path.open(mode="r",  encoding="utf8")  as input_file:
    with types_output_file_path.open(mode="w", encoding="utf8") as output_file:
        pokemons_data = json.load(input_file)
        #output_file.write("[")
        types = []
        for row in pokemons_data:
            logging.info("raw line : %s", row)
            logging.info("type name in english : %s", row["english"])
            output_file.write(f'{row["english"]}\n')
            #types.append(f'"{row["english"]}"')
        #output_file.write(",".join(types))
        #output_file.write("]")


pokedex_input_file_path = pathlib.Path("pokedex.json")
pokedex_output_file_path = pathlib.Path("pokedex.txt")

with pokedex_input_file_path.open(mode="r",  encoding="utf8")  as input_file:
    with pokedex_output_file_path.open(mode="w", encoding="utf8") as output_file:
        pokemons_data = json.load(input_file)
        for row in pokemons_data:
            logging.info("raw line : %s", row)
            logging.info("id  : %s", row["id"])
            logging.info("english name  : %s", row["name"]["english"])
            output_file.write(f'{row["id"]}, {row["name"]["english"]}\n')