import java.util.Scanner;

public class TicTacToe {

    public Scanner in;
    private boardPiece[][] board = {{new boardPiece(), new boardPiece(), new boardPiece()},
            {new boardPiece(), new boardPiece(), new boardPiece()},
            {new boardPiece(), new boardPiece(), new boardPiece()}};
    private char turn = 'X';
    private boolean win = false;
    private int count = 0;

    public void play() {
        printBoard();
        while (!win)
            move();
    }

    public void printBoard() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(board[x][y].piece);
            }
            System.out.println();
        }
    }

    public void move() {
        int move = 0;
        String valid = "";
        System.out.println("Player " + turn + ", enter move (1 - 9): ");
        move = in.nextInt();
        valid = checkMove(move);
        while (valid != "ok") {
            System.out.println("INVALID MOVE: " + valid);
            move = in.nextInt();
            valid = checkMove(move);
        }

        count++;
        board[(move - 1) / 3][(move - 1) % 3].piece = "_" + turn + "_|";
        board[(move - 1) / 3][(move - 1) % 3].player = turn;
        board[(move - 1) / 3][(move - 1) % 3].used = true;

        printBoard();

        if (count >= 5)
            checkWin(move);

        if (turn == 'X')
            turn = 'O';
        else
            turn = 'X';
    }

    public String checkMove(int move) {
        if (move < 1 || move > 9)
            return "Enter number 1 - 9 only: ";
        else if (board[(move - 1) / 3][(move - 1) % 3].used)
            return " Already used, Enter another move (1 - 9): ";
        else
            return "ok";
    }

    public void checkWin(int move) {
        for (int x = 0; x < 3; x++) { //Horizontal
            if ((board[x][0].used && board[x][1].used && board[x][2].used) &&
                    (board[x][0].player == board[x][1].player && board[x][0].player == board[x][2].player)) {
                System.out.println("Congratulations Player " + turn + "! You win ! wohoo");
                win = true;
                return;
            }
        }
        for (int y = 0; y < 3; y++) {
            if ((board[0][y].used && board[1][y].used && board[2][y].used) &&
                    (board[0][y].player == board[1][y].player && board[0][y].player == board[2][y].player)) {
                System.out.println("Congratulations Player " + turn + "!You win! yohooo");
                win = true;
                return;
            }
        }

        if ((board[0][0].used && board[1][1].used && board[2][2].used) &&
                (board[0][0].player == board[1][1].player && board[0][0].player == board[2][2].player)) {
            System.out.println("Congratulations Player " + turn + "!!! You win!");
            win = true;
            return;
        }

        if ((board[2][0].used && board[1][1].used && board[0][2].used) &&
                (board[2][0].player == board[1][1].player && board[2][0].player == board[0][2].player)) {
            System.out.println("Congratulations Player " + turn + "!!! You win!");
            win = true;
            return;
        }

        if (count == 9) {
            System.out.println("Draw! Nobody Wins (´???`)");
            win = true;
            return;
        }
    }

    public void init() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                board[x][y] = new boardPiece();
            }
        }
        turn = 'X';
        win = false;
        count = 0;
    }

    class boardPiece {
        public String piece;
        public char player;
        public boolean used;

        boardPiece() {
            piece = "___|";
            used = false;
        }
    }
}
