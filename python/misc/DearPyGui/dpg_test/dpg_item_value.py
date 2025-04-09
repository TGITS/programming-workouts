import dearpygui.dearpygui as dpg


def print_value(sender):
    print(dpg.get_value(sender))


def create_gui():
    dpg.create_context()

    with dpg.window(width=300):
        ## Text Inputs
        input_text1 = dpg.add_input_text()

        # The value for input_text2 wil have a starting value
        # of "This is a default value!"
        input_text2 = dpg.add_input_text(
            label="InputText2",
            default_value="This is a default value!",
            callback=print_value,
        )

        ## Sliders
        slider_float1 = dpg.add_slider_float()

        # The slider for slider_float2 will have a starting value of 50.0
        slider_float2 = dpg.add_slider_float(
            label="SliderFloat2", default_value=50.0, callback=print_value
        )

        dpg.set_item_callback(input_text1, print_value)
        dpg.set_item_callback(slider_float1, print_value)

        print(dpg.get_value(input_text1))
        print(dpg.get_value(input_text2))
        print(dpg.get_value(slider_float1))
        print(dpg.get_value(slider_float2))

    dpg.create_viewport(title="Item Value Examples", width=800, height=600)
    dpg.setup_dearpygui()
    dpg.show_viewport()
    dpg.start_dearpygui()
    dpg.destroy_context()


if __name__ == "__main__":
    create_gui()
