import dearpygui.dearpygui as dpg

dpg.create_context()


def button_callback(sender, app_data, user_data):
    print(f"sender is: {sender}")
    print(f"app_data is: {app_data}")
    print(f"user_data is: {user_data}")


with dpg.window(label="Tutorial"):
    # user data set when button is created
    dpg.add_button(
        label="Print to Terminal", callback=button_callback, user_data="Some␣Data"
    )

    # user data and callback set any time after button has been created
    # user data can be any Python object, not just String
    dpg.add_button(label="Print to Terminal 2", tag="btn")
    dpg.set_item_callback("btn", button_callback)
    dpg.set_item_user_data(
        "btn", {"p1": "Some Extra User Data", "p2": "Some other extra data"}
    )

dpg.create_viewport(title="Custom Title", width=800, height=600)
dpg.setup_dearpygui()
dpg.show_viewport()
dpg.start_dearpygui()
dpg.destroy_context()
