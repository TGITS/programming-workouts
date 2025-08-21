import tkinter as tk


class NumbersCollectorApp(tk.Tk):
    def __init__(
        self,
        max_number_of_moves=28,
        screenName=None,
        baseName=None,
        className="Tk",
        useTk=True,
        sync=False,
        use=None,
    ) -> None:
        super().__init__(screenName, baseName, className, useTk, sync, use)
        self.geometry("520x600")
        self.title("Numbers Collector !")
        self.canvas = tk.Canvas(self, width=520, height=540, bg="yellow")
        self.grid_representation = []
        self.init_grid_representation()
        self.create_grid()
        self.display_values_in_grid()
        self.canvas.pack()
        self.bind("<KeyPress>", self.move_piece)
        self.max_number_of_moves = max_number_of_moves
        self.x_piece = 0
        self.y_piece = 0
        self.number_of_moves = 0
        self.score = 0
        self.piece = self.canvas.create_oval(20, 20, 50, 50, fill="Blue")

    def move_piece(self, evt):
        if evt.keysym == "Down" and self.number_of_moves < self.max_number_of_moves:
            if self.y_piece < 9:  # Pour ne pas sortir du tableau
                self.compute_score(self.piece)
                self.canvas.move(self.piece, 0, 50)
                self.y_piece += 1
                self.number_of_moves += 1
        if evt.keysym == "Left" and self.number_of_moves < self.max_number_of_moves:
            if self.x_piece > 0:  # Pour ne pas sortir du tableau
                self.compute_score(self.piece)
                self.canvas.move(self.piece, -50, 0)
                self.x_piece -= 1
                self.number_of_moves += 1
        if evt.keysym == "Right" and self.number_of_moves < self.max_number_of_moves:
            if self.x_piece < 9:  # Pour ne pas sortir du tableau
                self.compute_score(self.piece)
                self.canvas.move(self.piece, 50, 0)
                self.x_piece += 1
                self.number_of_moves += 1

        if self.number_of_moves >= self.max_number_of_moves:
            self.canvas.create_text(
                250, 530, text="Score : " + str(self.score), font=("Arial", 12)
            )

    def create_grid(self):
        for i in range(0, 11):
            self.canvas.create_line(
                10 + i * 50, 10, 10 + i * 50, 510, fill="black", width=1
            )

        for i in range(0, 11):
            self.canvas.create_line(
                10, 10 + i * 50, 510, 10 + i * 50, fill="black", width=1
            )

    def init_grid_representation(self):
        for i in range(0, 10):
            self.grid_representation.append([0] * 10)

        self.grid_representation[0][5] = 20
        self.grid_representation[1][9] = 30
        self.grid_representation[2][7] = 30
        self.grid_representation[3][2] = 20
        self.grid_representation[4][0] = 10
        self.grid_representation[5][8] = 10
        self.grid_representation[6][4] = 10
        self.grid_representation[7][3] = 30
        self.grid_representation[8][7] = 20

    def display_values_in_grid(self):
        for row in self.grid_representation:
            for cell in row:
                if cell != 0:
                    self.canvas.create_text(
                        row.index(cell) * 50 + 35,
                        self.grid_representation.index(row) * 50 + 35,
                        text=str(cell),
                        font=("Arial", 10),
                    )

    def compute_score(self, piece):
        (top_x, top_y, _, _) = self.canvas.coords(piece)
        value = self.grid_representation[self.y_piece][self.x_piece]

        if value > 0:
            self.score += value
            #self.canvas.create_text(top_x + 15, top_y + 15, text="***", font=("Arial", 15))
            self.canvas.create_rectangle(10 + self.x_piece * 50, 10 + self.y_piece * 50, 10 + self.x_piece * 50 + 50, 10 + self.y_piece * 50 + 50, fill = "blue")
            self.grid_representation[self.y_piece][self.x_piece] = 0
        else:
            self.canvas.create_rectangle(10 + self.x_piece * 50, 10 + self.y_piece * 50, 10 + self.x_piece * 50 + 50, 10 + self.y_piece * 50 + 50, fill = "blue")


def main():
    print("Hello from numbers collector!")
    app = NumbersCollectorApp()
    app.display_values_in_grid()
    app.mainloop()


if __name__ == "__main__":
    main()
