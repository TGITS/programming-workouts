# README

* Adapted from the Real Python Tutorial : ["Python Textual: Build Beautiful UIs in the Terminal"](https://realpython.com/python-textual/)
  * The only difference with the original tutorial, is the use of uv
* `uv init --python 3.13 textual_tutorial`
* `uv add pytest ruff textual-dev --dev`
* `uv add textual`
* `uv add textual-dev --dev`
* `uv run textual run --dev .\static_and_label_tcss.py`
* `uv run textual serve .\static_and_label_tcss.py`
* `uv run python .\horizontal_scroll.py`
* `uv run python .\layouts.py`