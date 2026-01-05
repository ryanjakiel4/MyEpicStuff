/*Start
   intitalized board
   randomize mines
   if user input =! mine placement
      output new board with user inputted space and surrounding number of mines 
      else
      output: YOU GOT A MINE 
   End
*/

import java.util.Scanner;
import java.util.ArrayList;
public class Minesweeper{

   private String[][] userInput = new String[8][8];

   public static void main(String[] args){
      Scanner input = new Scanner(System.in);
      
      initBoard();
      
      System.out.println("Enter points:");
      int a = input.nextInt();
      int b = input.nextInt();
         
      for (int row = 0; row < userInput.length; row++) { // Sets all the values equal to "_"
         for (int column = 0; column < userInput[row].length; column++) {
            userInput[row][column] = "_";
         }
      }
            
      while(userInput[a][b] != "*"){
      
         countAdjacentMines(a,b);
         initBoard();  
                 
      }
   
      if(userInput[a][b] == "*"){
         setMines(true);
         System.out.println("***BOOM! YOU GOT A MINE!***");
      }
   
   
   
   }

   public static void initBoard(){
   // initialize a game board using 2 dimensional arrays with labeled axis' and blank 8x8 playing space
      int[][] board = new int[11][9];
      String[][] underscore = new String[11][9];
      
      underscore[9][0] = "";
      underscore[10][0] = "";
   
         
      for(int column = 0; column < 9; column++){ //blank spaces above "column"
         underscore[10][column] = "";
      }
             
      for (int row = 1; row < board.length-2; row++) { //Sets each row value equal to 1-8
         underscore[row][0] = ""+row;    
      } 
      
      for (int column = 1; column < 9; column++) { //Sets each column value equal to 1-8
         underscore[10][column] = ""+column;    
      }
      for (int column = 1; column < 9; column++) { //blank spaces between labeled columns and board
         underscore[9][column] = "";    
      }
      for (int column = 0; column < 9; column++) { //blank spaces between labeled columns and board
         underscore[0][column] = "";    
      }         
   
      for (int row = 1; row < board.length-2; row++) { // Sets all the middle values equal to "_"
         for (int column = 1; column < 9; column++) {
            underscore[row][column] = "_";
            
             
         }
      }
      underscore[0][0] = "Row";
      underscore[10][0] = "Column";
         
      for (int row = 0; row < board.length; row++) //Prints board
      {		
         for (int column = 0; column < 9; column++) 
         {
            System.out.print(underscore[row][column] + "\t"); 
         }
         System.out.println();
      }
    
   }

   public static void setMines(boolean display){
      
      for(int i = 0; i < 10; i++){
         int a = (int)(Math.random()*8);
         int b = (int)(Math.random()*8);
         String case1 = userInput[a][b]; 
         
         if(case1.equals("*")){
            i--;
         }        
         else
            userInput[a][b] = "*";
      
      }
      
   }

   public static void countAdjacentMines(int row, int column){
   //count how many lines surround the point that user inputs (row, column) and output the new gameboard with this number
      
      int counter = 0;
      
      if(userinput[a+1][b].equals ("*")) //counts the number of mines surrounding a single point... I will do the same thing for the 8 surrounding points too.
         counter++;
      if(useringput[a-1][b].equals("*"))
         counter++;
      if(userinput[a][b+1].equals("*"))
         counter++;
      if(userinput[a][b-1].equals("*"))
         counter++;
      if(userinput[a+1][b+1].equals("*"))
         counter++;
      if(userinput[a+1][b-1].equals("*"))
         counter++;  
      if(userinput[a-1][b+1].equals("*"))
         counter++;
      if(userinput[a-1][b-1].equals("*"))
         counter++;
      userinput[a][b].equals(Integer.parseInt(counter));
                                             
      if(userinput[a][b].equals("0")) {                           
         int counter1 = 0;
         if(userInput[a-2][b-1].equals("*"))
            counter1++;
         if(userInput[a-2][b-2].equals("*"))
            counter1++;
         if(userInput[a-1][b-2].equals("*"))
            counter1++;
         userinput[a-1][b-1].equals(Integer.parseInt(counter1));
      }
      int counter2 = 0;
      if(userInput[a-2][b-1].equals("*"))
         counter2++;
      if(userInput[a-2][b].equals("*"))
         counter2++;
      if(userInput[a-2][b+1].equals("*"))
         counter2++;
      userinput[a-1][b].equals(Integer.parseInt(counter2));
                       
                // int counter3 = 0;
   //                 if(userInput[][].equals("*"))
   //                   counter3++;
   //                      if(userInput[][].equals("*"))
   //                         counter3++;
   //                         if(userInput[][].equals("*"))
   //                            counter3++;
   //                            userinput[][].equals(Integer.parseInt(counter3));
   }
}
