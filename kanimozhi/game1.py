import tkinter as tk
from tkinter import messagebox
import random

class TicTacToe:
    def __init__(self):
        self.window = tk.Tk()
        self.window.title("Tic-Tac-Toe")
        self.window.geometry("400x450")
        self.window.resizable(False, False)

        self.current_player = "X"
        self.board = [[None] * 3 for _ in range(3)]

        self.create_widgets()
        self.window.mainloop()

    def create_widgets(self):
        self.frame = tk.Frame(self.window, bg="lightgray")
        self.frame.pack(expand=True)

        for i in range(3):
            for j in range(3):
                self.board[i][j] = tk.Button(
                    self.frame,
                    text="",
                    font=("Arial", 20, "bold"),
                    width=5,
                    height=2,
                    command=lambda row=i, col=j: self.on_click(row, col),
                    bg="white",
                    fg="black",
                    relief=tk.RAISED
                )
                self.board[i][j].grid(row=i, column=j, padx=5, pady=5)

        self.reset_button = tk.Button(
            self.window, text="Restart Game", font=("Arial", 12, "bold"),
            command=self.reset_board, bg="blue", fg="white"
        )
        self.reset_button.pack(pady=10)

    def on_click(self, row, col):
        if self.board[row][col]["text"] == "":
            glitter_colors = ["#FFD700", "#FF69B4", "#00FFFF", "#FFA500", "#8A2BE2", "#FF4500"]
            random_glitter = random.choice(glitter_colors)

            self.board[row][col]["text"] = self.current_player
            self.board[row][col].config(fg=random_glitter)

            if self.check_winner(row, col):
                messagebox.showinfo("Game Over", f"Player {self.current_player} wins!")
                self.reset_board()
            elif self.is_draw():
                messagebox.showinfo("Game Over", "It's a draw!")
                self.reset_board()
            else:
                self.current_player = "O" if self.current_player == "X" else "X"

    def check_winner(self, row, col):
        b, p = self.board, self.current_player

        return (
            all(b[row][i]["text"] == p for i in range(3)) or
            all(b[i][col]["text"] == p for i in range(3)) or
            all(b[i][i]["text"] == p for i in range(3)) or
            all(b[i][2-i]["text"] == p for i in range(3))
        )

    def is_draw(self):
        return all(self.board[i][j]["text"] != "" for i in range(3) for j in range(3))

    def reset_board(self):
        for i in range(3):
            for j in range(3):
                self.board[i][j]["text"] = ""
                self.board[i][j].config(bg="white", fg="black")
        self.current_player = "X"

if __name__ == "__main__":
    TicTacToe()
