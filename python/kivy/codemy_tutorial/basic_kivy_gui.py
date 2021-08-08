import kivy
from kivy.app import App
from kivy.uix.label import Label
from kivy.uix.gridlayout import GridLayout
from kivy.uix.textinput import TextInput
from kivy.uix.button import Button

class AppLayout(GridLayout):
    def __init__(self, **kwargs):
        super(AppLayout,self).__init__(**kwargs)

        self.cols = 2

        self.add_widget(Label(text="Name :"))
        self.name = TextInput(multiline=False)
        self.add_widget(self.name)

        self.add_widget(Label(text="Favorite pizza :"))
        self.pizza = TextInput(multiline=False)
        self.add_widget(self.pizza)

        self.add_widget(Label(text="Favorite color :"))
        self.color = TextInput(multiline=False)
        self.add_widget(self.color)

        self.submit = Button(text="Submit", font_size=32)
        self.submit.bind(on_press=self.press)
        self.add_widget(self.submit)

        self.display = Label(text="<change on submit>")
        self.add_widget(self.display)
    
    def press(self,instance):
        self.display.text = f'Hello {self.name.text} ! \nYour favorite pizza is {self.pizza.text} and \nyour favorite color is {self.color.text}'


class HelloKivyApp(App):

    def build(self):
        return AppLayout()

if __name__ == '__main__':
    HelloKivyApp().run()