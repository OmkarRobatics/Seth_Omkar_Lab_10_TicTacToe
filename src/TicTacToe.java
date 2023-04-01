import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String playerMove;
    private static final String[][] board = new String[ROW][COL];

    public static void main(String[] args) {
        int moveRowCoordinate = 0;
        int moveColCoordinate = 0;
        boolean validMove = false;
        int moveCnt = 0;
        boolean win = false;
        boolean tie = false;

        SafeInput.prettyHeader("Tic Tac Toe Game");
        System.out.println();
        System.out.println("     Board");
        clearBoard();
        display();
        Scanner in = new Scanner(System.in);

        do {
            playerMove = "X";
            moveRowCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the row you would like to play on: ", 1, 3);
            moveColCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the column you would like to play on: ", 1, 3);

            moveRowCoordinate = moveRowCoordinate - 1;
            moveColCoordinate = moveColCoordinate - 1;
            validMove = isValidMove(moveRowCoordinate, moveColCoordinate);
            if (validMove) {
                board[moveRowCoordinate][moveColCoordinate] = playerMove;
                display();
            } else {
                do {
                    System.out.println("You entered a invalid move please try again");
                    moveRowCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the row you would like to play on: ", 1, 3);
                    moveColCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the column you would like to play on: ", 1, 3);
                    validMove = isValidMove(moveRowCoordinate, moveColCoordinate);
                    if (validMove) {

                        board[moveRowCoordinate][moveColCoordinate] = playerMove;
                        display();
                    }
                } while (!validMove);

            }
            moveCnt++;
            System.out.println("Number of moves: " + moveCnt);
            if (moveCnt >= 5) {
                win = isWin("X");
                tie = isTie();
                if (win) {
                    System.out.println("player 1, you win!");
                    break;
                }
                else if(tie){
                    System.out.println("The game is a tie.");
                    break;
                }
            }

            // second player
            playerMove = "O";
            moveRowCoordinate = SafeInput.getRangedInt(in, "Player 2, please enter the number of the row you would like to play on: ", 1, 3);
            moveColCoordinate = SafeInput.getRangedInt(in, "Player 2, please enter the number of the column you would like to play on: ", 1, 3);

            moveRowCoordinate = moveRowCoordinate - 1;
            moveColCoordinate = moveColCoordinate - 1;
            validMove = isValidMove(moveRowCoordinate, moveColCoordinate);
            if (validMove) {

                board[moveRowCoordinate][moveColCoordinate] = playerMove;
                display();
            } else {
                do {
                    System.out.println("You entered a invalid move please try again");
                    moveRowCoordinate = SafeInput.getRangedInt(in, "Player 2, please enter the number of the row you would like to play on: ", 1, 3);
                    moveColCoordinate = SafeInput.getRangedInt(in, "Player 2, please enter the number of the column you would like to play on: ", 1, 3);
                    moveRowCoordinate = moveRowCoordinate - 1;
                    moveColCoordinate = moveColCoordinate - 1;
                    validMove = isValidMove(moveRowCoordinate, moveColCoordinate);
                    if (validMove) {

                        board[moveRowCoordinate][moveColCoordinate] = playerMove;
                        display();
                    }
                } while (!validMove);

            }
            moveCnt++;
            System.out.println("Number of moves: " + moveCnt);
            if (moveCnt >= 5) {
                win = isWin("O");
                tie = isTie();

                if (win) {
                    System.out.println("Player 2, you win!");
                }
              else if(tie)
                {
                    System.out.println("The game is a tie.");
                }
            }
        } while (!win || !tie);
    }


    private static void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        for (int row = 0; row < ROW; row++) {
            System.out.print("| ");
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        boolean retVal = board[row][col].equalsIgnoreCase(" ");
        return retVal;
    }

    private static boolean isWin(String playerMove) {
        return isColWin(playerMove) || isRowWin(playerMove) || isDiagonalWin(playerMove);
    }

    private static boolean isDiagonalWin(String playerMove) {
        return board[0][0].equals(playerMove) && board[1][1].equals(playerMove) && board[2][2].equals(playerMove) || board[0][2] == playerMove && board[1][1] == playerMove && board[2][0] == playerMove;
    }

    private static boolean isRowWin(String playerMove) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(playerMove) && board[row][1].equals(playerMove) && board[row][2].equals(playerMove)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String playerMove) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(playerMove) && board[1][col].equals(playerMove) && board[2][col].equals(playerMove)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isTie() {
        boolean rowXPresent = false;
        boolean colXPresent = false;
        boolean diagonalXPresent = false;
        boolean rowOPresent = false;
        boolean colOPresent = false;
        boolean diagonalOPresent = false;

        // checking in rows

        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X")) {
                rowXPresent = true;
            }
        }
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals("X") && board[1][col].equals("X") && board[2][col].equals("X")) {
                colXPresent = true;
            }
        }
        if (board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X") || board[0][2].equalsIgnoreCase("X") && board[1][1].equals("X") && board[2][0].equals("X"))
        {
            diagonalXPresent = true;
        }
        for (int row = 0; row < ROW; row++) {
                if (board[row][0].equals("O") || board[row][1].equals("O") || board[row][2].equals("O")) {
                    rowOPresent = true;
                }
            }
            for (int col = 0; col < COL; col++) {
                if (board[0][col].equals("O") && board[1][col].equals("O") && board[2][col].equals("O")) {
                    colOPresent = true;
                }
            }
            if (board[0][0].equals("O") && board[1][1].equals("O") && board[2][2].equals("O") || board[0][2].equalsIgnoreCase("O") && board[1][1].equals("O") && board[2][0].equals("O"))
            {
                diagonalOPresent = true;
            }
        if (rowXPresent && rowOPresent && colXPresent && colOPresent && diagonalXPresent && diagonalOPresent)
        {
            return true;
        }
        return false;
    }



}