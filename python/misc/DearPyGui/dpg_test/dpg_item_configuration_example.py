import dearpygui.dearpygui as dpg

dpg.create_context()

button_tag = dpg.generate_uuid()


def callback():
    print(dpg.get_item_label(button_tag))
    dpg.configure_item(button_tag, enabled=False, label="Cannot be pressed now")
    print(dpg.get_item_label(button_tag))


with dpg.window(width=500, height=300):
    dpg.add_button(enabled=True, label="Press me", tag=button_tag, callback=callback)


dpg.create_viewport(title="Custom Title", width=800, height=600)
dpg.setup_dearpygui()
dpg.show_viewport()
dpg.start_dearpygui()
dpg.destroy_context()
