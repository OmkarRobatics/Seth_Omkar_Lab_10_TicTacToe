import java.util.Scanner;

public class TicTacToe {
    private static String playerMove = "X";
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    public static void main(String[] args)
    {
        int moveRowCoordinate = 0;
        int moveColCoordinate = 0;
        boolean validMove = false;
        System.out.println("Tic Tac Toe Game");
    clearBoard();
    display();
    Scanner in = new Scanner(System.in);
    moveRowCoordinate = SafeInput.getRangedInt(in,"Player 1, please enter the number of the row you would like to play on: ",1,3);
    moveColCoordinate = SafeInput.getRangedInt(in,"Player 1, please enter the number of the column you would like to play on: ",1,3);

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
           moveRowCoordinate = SafeInput.getRangedInt(in,"Player 1, please enter the number of the row you would like to play on: ",1,3);
           moveColCoordinate = SafeInput.getRangedInt(in,"Player 1, please enter the number of the column you would like to play on: ",1,3);

       }while (validMove == false);
   }
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

            }while (validMove == false);
        }
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
        if (board[row][col].equalsIgnoreCase(" "));
        {
            retVal = true;
        }
        return retVal;
    }
    private static boolean isDiagnalWin(String playerMove)
    {

        if(board[1][1] == playerMove && board[2][2] == playerMove && board[3][3] == playerMove || board[1][3] == playerMove && board[2][2] == playerMove && board[3][1] == playerMove )
        {
            System.out.println("You win");
            return true;
        }
        return false;
    }


}