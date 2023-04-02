import java.util.Scanner;
/* pseudocode
 - display title
 - clear board (important if user wants to play again)
 - display board
 - get coordinates from player 1.
 - check if it is valid
 - get coordinates from player 2.
 - check if valid
 - keep getting moves from players
 - check for win or tie
 - announce result
 - ask user if they want to player again.
 - repeat if they say yes. end program if they say no.
*/
public class TicTacToe {
    private static final int ROW = 3; // row length of TTT board
    private static final int COL = 3; // column length of TTT board

    private static final String[][] board = new String[ROW][COL]; // array for TTT board

    public static void main(String[] args) {
        // var declarations
        String playerMove; // X or O move
        int moveRowCoordinate = 0;
        int moveColCoordinate = 0;
        boolean validMove = false;
        int moveCnt = 0;
        boolean win = false;
        boolean tie = false;
        boolean playAgain = false;

        SafeInput.prettyHeader("Tic Tac Toe Game"); // outputs page title
        System.out.println();
        do {
            System.out.println("     Board"); // title for board
            clearBoard(); // sets all values in array to space
            display(); // displays TTT board
            Scanner in = new Scanner(System.in); // sets up scanner class

            do {
                playerMove = "X"; // sets player move to first player X
                // prompts user for coordinates
                moveRowCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the row you would like to play on: ", 1, 3);
                moveColCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the column you would like to play on: ", 1, 3);
                // converts coordinates to index
                moveRowCoordinate = moveRowCoordinate - 1;
                moveColCoordinate = moveColCoordinate - 1;
                validMove = isValidMove(moveRowCoordinate, moveColCoordinate); // checks for valid move
                if (validMove)
                {
                    board[moveRowCoordinate][moveColCoordinate] = playerMove; // places X on board
                    display(); // displays board with change
                } else {
                    do {
                        System.out.println("You entered a invalid move please try again"); // outputs message of mistake
                        // prompts user for coordinates
                        moveRowCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the row you would like to play on: ", 1, 3);
                        moveColCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the column you would like to play on: ", 1, 3);
                        // converts coordinates to index
                        moveRowCoordinate = moveRowCoordinate - 1;
                        moveColCoordinate = moveColCoordinate - 1;
                        validMove = isValidMove(moveRowCoordinate, moveColCoordinate);
                        if (validMove) {

                            board[moveRowCoordinate][moveColCoordinate] = playerMove; // places X on board
                            display(); // displays board with change
                        }
                    } while (!validMove); // prompts user again if they do not enter valid move

                }
                moveCnt++; // adds one to move counter
                System.out.println("Number of moves: " + moveCnt); // outputs move count
                if (moveCnt >= 5)
                {
                    win = isWin("X"); // tests for win
                    tie = isTie(); // tests for tie
                    if (win)
                    {
                        System.out.println("player 1, you win!"); // outputs win message
                        break; // breaks loop. Asks users if they want to play again.
                    } else if (tie)
                    {
                        System.out.println("The game is a tie."); // outputs tie message
                        break; // breaks loop. Asks users if they want to play again.
                    }
                }

                // second player
                playerMove = "O"; // sets player move to second player, O.
                // prompts user for coordinates
                moveRowCoordinate = SafeInput.getRangedInt(in, "Player 2, please enter the number of the row you would like to play on: ", 1, 3);
                moveColCoordinate = SafeInput.getRangedInt(in, "Player 2, please enter the number of the column you would like to play on: ", 1, 3);
                // converts coordinates to index
                moveRowCoordinate = moveRowCoordinate - 1;
                moveColCoordinate = moveColCoordinate - 1;
                validMove = isValidMove(moveRowCoordinate, moveColCoordinate); // tests for valid move
                if (validMove)
                {
                    board[moveRowCoordinate][moveColCoordinate] = playerMove; // places O on board
                    display(); // displays board with change
                } else // invalid move
                {
                    do {
                        System.out.println("You entered a invalid move please try again"); // outputs error message
                        // prompts user for coordinates
                        moveRowCoordinate = SafeInput.getRangedInt(in, "Player 2, please enter the number of the row you would like to play on: ", 1, 3);
                        moveColCoordinate = SafeInput.getRangedInt(in, "Player 2, please enter the number of the column you would like to play on: ", 1, 3);
                        // converts coordinates to index
                        moveRowCoordinate = moveRowCoordinate - 1;
                        moveColCoordinate = moveColCoordinate - 1;
                        validMove = isValidMove(moveRowCoordinate, moveColCoordinate); // tests for valid move
                        if (validMove) {
                            board[moveRowCoordinate][moveColCoordinate] = playerMove; // places O on board
                            display(); // displays board with the change
                        }
                    } while (!validMove); // continues to ask for coordinates if they are invalid

                }
                moveCnt++; // adds one to move counter
                System.out.println("Number of moves: " + moveCnt); // displays number of moves
                if (moveCnt >= 5)
                {
                    win = isWin("O"); // tests for win
                    tie = isTie(); // tests for tie

                    if (win)
                    {
                        System.out.println("Player 2, you win!"); // gives win message
                    } else if (tie)
                    {
                        System.out.println("The game is a tie."); // gives tie message
                    }
                }
            } while (!win && !tie); // continues to next move if no win or tie. If there is a win or tie it will ask users if they want to play again
            playAgain = SafeInput.getYNConfirm(in, "Would you like to play again? [respond with Y or N]: "); // prompts users to play again
        }while (playAgain); // repeats everything if they answer with yes.
        }


    private static void clearBoard() {
        // loop to touch rows
        for (int row = 0; row < ROW; row++)
        {
            // loop to touch columns
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = " "; // sets every square to space on board.
            }
        }
    }

    private static void display() {
        for (int row = 0; row < ROW; row++) {
            System.out.print("| "); // places first line of |
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col] + " | "); // places rest of |
            }
            System.out.println(); // new line
        }
    }

    private static boolean isValidMove(int row, int col) {
        boolean retVal = board[row][col].equalsIgnoreCase(" "); // if the square is empty the player is playing a valid move
        return retVal;
    }

    private static boolean isWin(String playerMove)
    {
        return isColWin(playerMove) || isRowWin(playerMove) || isDiagonalWin(playerMove); // if there is a win in any way true is returned
    }

    private static boolean isDiagonalWin(String playerMove) {
        return board[0][0].equals(playerMove) && board[1][1].equals(playerMove) && board[2][2].equals(playerMove) || board[0][2].equals(playerMove) && board[1][1].equals(playerMove) && board[2][0].equals(playerMove); // checks for win in either diagonal
    }

    private static boolean isRowWin(String playerMove) {
        for (int row = 0; row < ROW; row++) // touches all values in row
        {
            if (board[row][0].equals(playerMove) && board[row][1].equals(playerMove) && board[row][2].equals(playerMove)) // checks for win in row by checking if player move is in those squares
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String playerMove) {
        for (int col = 0; col < COL; col++) // touches all values in column
        {
            if (board[0][col].equals(playerMove) && board[1][col].equals(playerMove) && board[2][col].equals(playerMove)) // checks for win in column by checking if player move is in those squares
            {
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

        // checking in rows X
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X")) {
                rowXPresent = true; // sets to true if present
            } else{
                rowXPresent = false; // sets to false if not present
                break; // breaks out of for loop
            }
        }
        //checking cols X
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals("X") || board[1][col].equals("X") || board[2][col].equals("X")) {
                colXPresent = true; // sets to true if present
            } else{
                colXPresent = false; // sets to false if not present
                break; // breaks out of for loop
            }
        }
// checking for diagonals X
        if ((board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) && (board[0][2].equalsIgnoreCase("X") || board[1][1].equals("X") || board[2][0].equals("X")))
        {
            diagonalXPresent = true; // sets to true if present
        }
// checks for rows O
        for (int row = 0; row < ROW; row++) {
                if (board[row][0].equals("O") || board[row][1].equals("O") || board[row][2].equals("O")) {
                    rowOPresent = true; // sets to true if present
                } else{
                    rowOPresent = false; // sets to false if not present
                    break; // breaks out of for loop
                }
            }
        // checking for cols O
            for (int col = 0; col < COL; col++) {
                if (board[0][col].equals("O") || board[1][col].equals("O") || board[2][col].equals("O")) {
                    colOPresent = true; // sets to true if present
                } else{
                    colOPresent = false; // sets to false if not present
                    break; // breaks out of for loop
                }
            }
// checks for diagonal O
            if ((board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O")) && (board[0][2].equalsIgnoreCase("O") || board[1][1].equals("O") || board[2][0].equals("O")))
            {
                diagonalOPresent = true; // sets to true if present
            }
        if (rowXPresent && rowOPresent && colXPresent && colOPresent && diagonalXPresent && diagonalOPresent) // tests if all win vectors are blocked
        {
            return true; // reports a tie if all win vectors are blocked
        }

        return false; // else reports false
    }
}