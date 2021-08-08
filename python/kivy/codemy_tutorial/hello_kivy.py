import kivy
from kivy.app import App
from kivy.uix.label import Label

class HelloKivyApp(App):

    def build(self):
        return Label(text="Hello Kivy", font_size=72)

if __name__ == '__main__':
    HelloKivyApp().run()