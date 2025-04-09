import dearpygui.dearpygui as dpg

dpg.create_context()


def button_callback(sender, app_data, user_data):
    print(f"sender is: {sender}")
    print(f"app_data is: {app_data}")
    print(f"user_data is: {user_data}")


with dpg.window(label="Tutorial", tag="Primary Window"):
    # user data and callback set when button is created
    dpg.add_button(label="Apply", callback=button_callback, user_data="Example data")

    # user data and callback set any time after button has been created
    btn = dpg.add_button(label="Apply 2")
    dpg.set_item_callback(btn, button_callback)
    dpg.set_item_user_data(btn, "Some Extra User Data")

dpg.create_viewport(title="Callback examples", width=500, height=300)
dpg.setup_dearpygui()
dpg.show_viewport()
dpg.set_primary_window("Primary Window", True)
dpg.start_dearpygui()
dpg.destroy_context()
