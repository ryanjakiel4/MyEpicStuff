import java.util.Scanner;
import java.util.ArrayList;
public class Minesweeper
{
   private static int numRows;
   private static int numCols;
   private static String input;
   private static int mines;
   public static void main(String[] args)
   {
      int row;
      int col;
      Scanner scanny = new Scanner(System.in);
      System.out.println("How many rows  on the board?");
      numRows = scanny.nextInt();
      System.out.println("How many cols  on the board?");
      numCols = scanny.nextInt();
      System.out.println("How many mines on the board?");
      mines = scanny.nextInt();
      String dummy = scanny.nextLine();
      String[][] board = new String[numRows][numCols];
      String[][] subBoard = new String[numRows][numCols];
      subBoard = generateBoard();
      for(int r = 0; r < numRows; r++)
         for(int c = 0; c < numCols; c++)
            board[r][c] = "-";
      boolean gameover = false;
      while(!gameover)
      {
         displayBoard(board);
         System.out.println("Pick a point (row,column).");
         input = scanny.nextLine();
         if(input.split(",").length == 2)
         {
            row = Integer.parseInt(input.split(",")[0]);
            col = Integer.parseInt(input.split(",")[1]);
            if(subBoard[row][col].equals("*"))
            {
               gameover = true;
            }
            board[row][col] = subBoard[row][col];
            if(subBoard[row][col].equals("0"))
            {
               board = showSurroundingZeros(board, subBoard, row, col);
               board = showNumbersAroundZeros(board, subBoard);
            }
         }
         else
         {
            System.out.println("Unreadable input.");
         }
         
            
          
      }
      System.out.println("YOU LOST");
      displayBoard(subBoard);
   }
   public static void displayBoard(String[][] board)
   {
      System.out.println("MINESWEEPER");
      System.out.print("  ");
      for(int c = 0; c < numCols; c++)
      {
         System.out.print(" " + c);
      }
      System.out.println();
      for(int n = 0; n < numCols*2+2; n++)
      {
         if(n < 3)
            System.out.print(" ");
         else
            System.out.print("_");
      }
      System.out.println();
      for(int r= 0; r < numRows; r++)
      {
         System.out.print(r + "|");
         for(int c = 0; c < numCols; c++)
         {
            System.out.print(" " + board[r][c]);
         }
         System.out.println();
      }
      System.out.println();
   }
   public static String[][] generateBoard()
   {
      String[][] newBoard = new String[numRows][numCols];
      for(int r= 0; r < numRows; r++)
      {
         for(int c = 0; c < numCols; c++)
         {
            newBoard[r][c] = " ";
         }
      }
      int ro = 0;
      int co = 0;
      for(int m = 0; m < mines; m++)
      {
         ro = (int)(Math.random()*numRows);
         co = (int)(Math.random()*numCols);
         if(!newBoard[ro][co].equals("*"))
         {
            newBoard[ro][co] = "*";
         }
         else
         {
            m--;
         }
      }
      for(int r = 0; r < numRows; r++)
      {
         for(int c = 0; c < numCols; c++)
         {
            if(!newBoard[r][c].equals("*"))
            {
               newBoard[r][c] = countSurroundingMines(newBoard, r, c);
            }
         }
      }
      return newBoard;
   }
   public static String countSurroundingMines(String[][] board, int row, int col)
   {
      int count = 0;
      if(row > 0 && board[row-1][col].equals("*"))
      {
         count++;
      }
      if(col > 0 && board[row][col-1].equals("*"))
      {
         count++;
      }
      if(row < numRows-1 && board[row+1][col].equals("*"))
      {
         count++;
      }
      if(col < numCols-1 && board[row][col+1].equals("*"))
      {
         count++;
      }
      if(row > 0 && col > 0 && board[row-1][col-1].equals("*"))
      {
         count++;
      }
      if(row > 0 && col < numCols-1 && board[row-1][col+1].equals("*"))
      {
         count++;
      }
      if(row < numRows-1 && col > 0 && board[row+1][col-1].equals("*"))
      {
         count++;
      }
      if(row < numRows-1 && col < numCols-1 && board[row+1][col+1].equals("*"))
      {
         count++;
      }
      return count + "";
   }
   public static String[][] showSurroundingZeros(String[][] board, String[][] subBoard, int row, int col)
   {
      if(row > 0 && subBoard[row-1][col].equals("0") && board[row-1][col].equals("-"))
      {
         board[row-1][col] = "0"; 
         board = showSurroundingZeros(board, subBoard, row-1,  col);
      }
      if(col > 0 && subBoard[row][col-1].equals("0") && board[row][col-1].equals("-"))
      {
         board[row][col-1] = "0";  
         board = showSurroundingZeros(board, subBoard, row,  col-1);
      }
      if(row < numRows-1 && subBoard[row+1][col].equals("0") && board[row+1][col].equals("-"))
      {
         board[row+1][col] = "0"; 
         board = showSurroundingZeros(board, subBoard, row+1,  col);
      }
      if(col < numCols-1 && subBoard[row][col+1].equals("0") && board[row][col+1].equals("-"))
      {
         board[row][col+1] = "0";
         board = showSurroundingZeros(board, subBoard, row,  col+1);
      }
      return board;
   }
   public static String[][] showNumbersAroundZeros(String[][] board, String[][] subBoard)
   {
      for(int r = 0; r < numRows; r++)
      {
         for(int c = 0; c < numCols; c++)
         {
            if(board[r][c].equals("0"))
            {
               if(r > 0 && board[r-1][c].equals("-"))
               {
                  board[r-1][c] = subBoard[r-1][c]; 
               }
               if(c > 0 && board[r][c-1].equals("-"))
               {
                  board[r][c-1] = subBoard[r][c-1];  
               }
               if(r < numRows-1 && board[r+1][c].equals("-"))
               {
                  board[r+1][c] = subBoard[r+1][c]; 
               }
               if(c < numCols-1 && board[r][c+1].equals("-"))
               {
                  board[r][c+1] = subBoard[r][c+1];
               }
               if(r > 0 && c > 0 && board[r-1][c-1].equals("-"))
               {
                  board[r-1][c-1] = subBoard[r-1][c-1]; 
               }
               if(c > 0 && r < numRows-1 && board[r+1][c-1].equals("-"))
               {
                  board[r+1][c-1] = subBoard[r+1][c-1];  
               }
               if(r < numRows-1 && c < numCols-1 && board[r+1][c+1].equals("-"))
               {
                  board[r+1][c+1] = subBoard[r+1][c+1]; 
               }
               if(c < numCols-1 && r > 0 && board[r-1][c+1].equals("-"))
               {
                  board[r-1][c+1] = subBoard[r-1][c+1];
               }
            }
         }
      }
      return board;
   }
}