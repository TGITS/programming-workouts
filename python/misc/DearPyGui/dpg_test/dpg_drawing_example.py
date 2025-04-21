import dearpygui.dearpygui as dpg

dpg.create_context()

# creating font and back viewport drawlists
with dpg.viewport_drawlist():
    dpg.draw_circle((100, 100), 25, color=(255, 255, 255, 255))

dpg.add_viewport_drawlist(front=False, tag="viewport_back")
dpg.draw_circle((200, 200), 25, color=(255, 255, 255, 255), parent="viewport_back")

with dpg.window(label="Tutorial", width=300, height=300):
    dpg.add_text("Move the window over the drawings to see the effects.", wrap=300)
    dpg.draw_circle((100, 100), 25, color=(255, 255, 255, 255))

dpg.create_viewport(title="Custom Title", width=800, height=600)
dpg.setup_dearpygui()
dpg.show_viewport()
dpg.start_dearpygui()
dpg.destroy_context()
