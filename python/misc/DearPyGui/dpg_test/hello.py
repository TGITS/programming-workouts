import dearpygui.dearpygui as dpg

def main():
    print("Hello from dpg-test!")
    dpg.create_context()
    dpg.create_viewport(title="Test DPG", width=800, height=300)

    with dpg.window(label="Example window"):
        dpg.add_text("Hello from dpg-test!")
        dpg.add_button(label="Save")
        dpg.add_input_text(label="string", default_value="Quick brown fox")
        dpg.add_slider_float(label="float", default_value=0.273, max_value=1)

    dpg.setup_dearpygui()
    dpg.show_viewport()
    dpg.start_dearpygui()
    dpg.destroy_context()


if __name__ == "__main__":
    main()
