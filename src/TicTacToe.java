import java.util.Scanner;

public class TicTacToe {
    private static String playerMove;
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    public static void main(String[] args)
    {
        int moveRowCoordinate = 0;
        int moveColCoordinate = 0;
        boolean validMove = false;
        int moveCnt = 0;
        boolean win = false;
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
        if (validMove == true) {

            board[moveRowCoordinate][moveColCoordinate] = playerMove;
            display();
        } else {
            do {
                System.out.println("You entered a invalid move please try again");
                moveRowCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the row you would like to play on: ", 1, 3);
                moveColCoordinate = SafeInput.getRangedInt(in, "Player 1, please enter the number of the column you would like to play on: ", 1, 3);
                validMove = isValidMove(moveRowCoordinate, moveColCoordinate);
                if (validMove == true) {

                    board[moveRowCoordinate][moveColCoordinate] = playerMove;
                    display();
                }
            } while (validMove == false);


        }
        moveCnt++;


   // second player
   playerMove = "O";
        moveRowCoordinate = SafeInput.getRangedInt(in,"Player 2, please enter the number of the row you would like to play on: ",1,3);
        moveColCoordinate = SafeInput.getRangedInt(in,"Player 2, please enter the number of the column you would like to play on: ",1,3);

        moveRowCoordinate = moveRowCoordinate - 1;
        moveColCoordinate = moveColCoordinate - 1;
        validMove = isValidMove(moveRowCoordinate,moveColCoordinate);
        if (validMove == true) {

            board[moveRowCoordinate][moveColCoordinate] = playerMove;
            display();
        }
        else
        {
            do {
                System.out.println("You entered a invalid move please try again");
                moveRowCoordinate = SafeInput.getRangedInt(in,"Player 2, please enter the number of the row you would like to play on: ",1,3);
                moveColCoordinate = SafeInput.getRangedInt(in,"Player 2, please enter the number of the column you would like to play on: ",1,3);
                moveRowCoordinate = moveRowCoordinate - 1;
                moveColCoordinate = moveColCoordinate - 1;
                validMove = isValidMove(moveRowCoordinate,moveColCoordinate);
                if (validMove == true)
                {

                    board[moveRowCoordinate][moveColCoordinate] = playerMove;
                    display();
                }
            }while (validMove == false);

        }
        moveCnt++;
        win = isWin(playerMove);
        if(win)
        {
            System.out.println("You win!");
        }
    }while (!win);
     }


    private static void clearBoard()
    {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }
    private static void display()
    {
        for(int row=0; row < ROW; row++)
        {
            System.out.print("| ");
            for(int col=0; col < COL; col++)
            {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }
    private static boolean isValidMove(int row,int col) {
        boolean retVal = false;
        if (board[row][col].equalsIgnoreCase(" "))
        {
            retVal = true;
        }
        return retVal;
    }
    private static boolean isWin(String playerMove)
    {
        if(isColWin(playerMove) || isRowWin(playerMove) || isDiagonalWin(playerMove))
        {
            return true;
        }
        return false;
    }
    private static boolean isDiagonalWin(String playerMove)
    {
        if(board[0][0].equals(playerMove)  && board[1][1].equals(playerMove) && board[2][2].equals(playerMove)|| board[0][2] == playerMove && board[1][1] == playerMove && board[2][0] == playerMove )
        {
            return true;

        }
        return false;
    }
    private static boolean isRowWin(String playerMove)
    {
        for(int row =0; row < ROW; row++)
        {
            if(board[row][0].equals(playerMove) && board[row][1].equals(playerMove) && board[row][2].equals(playerMove))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isColWin(String playerMove)
    {
for(int col = 0; col < COL; col++)
    {
        if(board[col][0].equals(playerMove) && board[col][1].equals(playerMove) && board[col][2].equals(playerMove))
        {
            return true;
        }
    }
        return false;
}

}