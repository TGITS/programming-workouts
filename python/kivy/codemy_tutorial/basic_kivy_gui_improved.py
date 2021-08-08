import kivy
from kivy.app import App
from kivy.uix.label import Label
from kivy.uix.gridlayout import GridLayout
from kivy.uix.textinput import TextInput
from kivy.uix.button import Button


class AppLayout(GridLayout):
    def __init__(self, **kwargs):
        super(AppLayout,self).__init__(**kwargs)

        self.cols = 1

        self.top_grid = GridLayout()
        self.top_grid.cols = 2

        self.top_grid.add_widget(Label(text="Name :", font_size=24))
        self.name = TextInput(multiline=False)
        self.top_grid.add_widget(self.name)

        self.top_grid.add_widget(Label(text="Favorite pizza :", font_size=24))
        self.pizza = TextInput(multiline=False)
        self.top_grid.add_widget(self.pizza)

        self.top_grid.add_widget(Label(text="Favorite color :", font_size=24))
        self.color = TextInput(multiline=False)
        self.top_grid.add_widget(self.color)

        self.add_widget(self.top_grid)

        self.submit = Button(text="Submit", font_size=24)
        self.submit.bind(on_press=self.press)
        self.add_widget(self.submit)

        self.clear = Button(text="Clear", font_size=24)
        self.clear.bind(on_press=self.reset)
        self.add_widget(self.clear)

        self.display = Label(text="<change on submit>", font_size=24)
        self.add_widget(self.display)
    
    def press(self,instance):
        self.display.text = f'Hello {self.name.text} ! \nYour favorite pizza is {self.pizza.text} and \nyour favorite color is {self.color.text}'

    def reset(self,instance):
        self.display.text = "<change on submit>"
        self.name.text = ""
        self.pizza.text = ""
        self.color.text = ""


class HelloKivyApp(App):

    def build(self):
        return AppLayout()

if __name__ == '__main__':
    HelloKivyApp().run()