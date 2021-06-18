 
/**
 * 
 * Purpose: add in the function to 
 *
 *
 *
 * @author Corwin
 * @version 14/05/21
 */

import java.util.Scanner; //import scanners for input

public class version0_3_2
{
    String currentBoard [][] = new String[26][52];
    String nextBoard [][] = new String[26][52];
    public version0_3_2()
    {
       constructBoard();
       printBoard();
       runGame();
    }
    
    void constructBoard(){
        for (int y=0; y<26;y++){
            for (int x=0; x<52;x++){
               currentBoard [y][x]= ("O");
            }
        }
    }
    
    void printBoard(){
        for (int y=0; y<26;y++){
            for (int x=0; x<52;x++){
               System.out.print(currentBoard[y][x]);
            }
            System.out.println();
        }
    }
    
    void runGame(){
        String command;
        String initialCommand;
        boolean awaitingInput=true;
        Scanner input = new Scanner(System.in); //creating input
        while(awaitingInput) {
            initialCommand = input.nextLine();
            command = initialCommand.toLowerCase();
            switch (command) {
                case "quit":
                case "end" : //quit the game
                awaitingInput = false; //stops the while loop from running
                    break;
                
                case "turn on":
                turnOn();
                printBoard();
                    break;
                    
                case "check neighbors":
                calculateNextState();
                    break;
                
                default :  
                System.out.println("Invalid input"); 
                    break; 
                    
            }
        }
    }
   
    void turnOn(){
        Scanner input = new Scanner(System.in); //creating input
        int xValue = input.nextInt();
        int yValue = input.nextInt();
        currentBoard[xValue][yValue] = "X";
    }
    
    void calculateNextState(){
        for (int y=0; y<26;y++){
            for (int x=0; x<52; x++){
                int neighbors = 0;
                if (y-1!=-1) if (nextBoard [x][y-1].equals("X")) neighbors++;
                if (y+1!=26) if (nextBoard [x][y+1].equals("X")) neighbors++;
                if (x+1!=53){
                    if (nextBoard [x+1][y].equals("X")) neighbors++;
                    if (y-1!=-1) if (nextBoard [x+1][y-1].equals("X")) neighbors++;
                    if (y+1!=26) if (nextBoard [x+1][y+1].equals("X")) neighbors++;
                }
                if (x-1!=-1){
                    if (nextBoard [x-1][y].equals("X")) neighbors++;
                    if (y-1!=-1) if (nextBoard [x-1][y-1].equals("X")) neighbors++;
                    if (y+1!=26) if (nextBoard [x-1][y+1].equals("X")) neighbors++;
                }
                
                System.out.println(x+""+y+""+neighbors);
            }
        }
    }
    
}
