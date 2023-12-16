import json
import pathlib
import logging


input_file_path = pathlib.Path("types.json")
output_file_path = pathlib.Path("types.txt")

with input_file_path.open(mode="r",  encoding="utf8")  as input_file:
    with output_file_path.open(mode="w", encoding="utf8") as output_file:
        types_data = json.load(input_file)
        #output_file.write("[")
        types = []
        for row in types_data:
            logging.info("raw line : %s", row)
            logging.info("type name in english : %s", row["english"])
            output_file.write(f'{row["english"]}\n')
            #types.append(f'"{row["english"]}"')
        #output_file.write(",".join(types))
        #output_file.write("]")
