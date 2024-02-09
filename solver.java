import java.util.*;

class solver
{
    private static final int GRID_SIZE = 9;

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        int i, j;
        for (i = 0; i < GRID_SIZE; i++)
        {
            System.out.println("Enter the " + (i+1) + " row value: ");
            for (j = 0; j < GRID_SIZE; j++)
            {
                board[i][j] = s.nextInt();
            }
        }
        printBoard(board);
        System.out.println();
        if (solveBoard(board))
        {
            System.out.println("Solved Successfully");
            System.out.println();
        }
        else
        {
            System.out.println("Unsolvable board :( ");
            System.out.println();
        }
        printBoard(board);
    }

    private static void printBoard(int[][] board)
    {
        int row, column;
        for (row = 0; row < GRID_SIZE; row++)
        {
            if (row % 3 == 0 && row != 0)
            {
                System.out.println("----------");
            }
            for (column = 0; column < GRID_SIZE; column++)
            {
                if (column % 3 == 0 && column != 0)
                {
                    System.out.print("|");
                }
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row)
    {
        int i;
        for (i = 0; i < GRID_SIZE; i++)
        {
            if (board[row][i] == number)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column)
    {
        int i;
        for (i = 0; i < GRID_SIZE; i++)
        {
            if (board[i][column] == number)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column)
    {
        int i, j, localBoxRow = row - row % 3, localBoxColumn = column - column % 3;
        for (i = localBoxRow; i < localBoxRow + 3; i++)
        {
            for (j = localBoxColumn; j < (localBoxColumn + 3); j++)
            {
                if (board[i][j] == number)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column)
    {
        return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column) && !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board)
    {
        int row, column, numberToTry;
        for (row = 0; row < GRID_SIZE; row++)
        {
            for (column = 0; column < GRID_SIZE; column++)
            {
                if (board[row][column] == 0)
                {
                    for (numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++)
                    {
                        if (isValidPlacement(board, numberToTry, row, column))
                        {
                            board[row][column] = numberToTry;
                            if (solveBoard(board))
                            {
                                return true;
                            }
                            else
                            {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
