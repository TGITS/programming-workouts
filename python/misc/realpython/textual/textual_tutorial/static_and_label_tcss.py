from textual.app import App
from textual.widgets import Label, Static


class StaticAndLabelApp(App):
    CSS_PATH = "static_and_label.tcss"

    def compose(self):
        yield Static(
            "I am a [bold red]Static[/bold red] widgets!",
        )
        yield Label(
            "I am a [yellow italic]Label[/yellow italic] widget with an id!",
            id="label_id",
        )
        yield Label(
            "I am a [yellow italic]Label[/yellow italic] widget with a CSS class!",
            classes="label_class",
        )


if __name__ == "__main__":
    app = StaticAndLabelApp()
    app.run()
