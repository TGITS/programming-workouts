# README

## Notes
 
* initialisation du projet avec uv
  * `uv init img_conversion`
* renommage du fichier `hello.py` en `convert_script.py`
* Ajouter la dépendance `Pillow` : `uv add pillow`
* https://www.pexels.com/fr-fr/photo/nature-oiseau-voler-animal-6482723/
  * pexels-photo-6482723.webp
* https://pillow.readthedocs.io/en/stable/handbook/tutorial.html
* `uv init dpg_test`
* `uv pip install --trusted-host pypi.org --trusted-host pypi.python.org --trusted-host files.pythonhosted.org dearpygui`
 
## DearPyGuy
 
The main script must always:
 
* Create the context `create_context`
* Create the viewport `create_viewport`
* Setup dearpygui `setup_dearpygui`
* Show the viewport `show_viewport`
* Start dearpygui `start_dearpygui`
* Clean up the context `destroy_context`