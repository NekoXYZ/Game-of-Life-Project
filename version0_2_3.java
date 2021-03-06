
/**
 * Purpose of overall Version: to add player input through typing commands
 * Purpose: to add a toLowerCase so that the commands are no longer case sensitive
 *
 *
 *
 * @author Corwin
 * @version 14/05/21
 */

import java.util.Scanner; //import scanners for input

public class version0_2_3
{
    String board [][] = new String[26][52];
    public version0_2_3()
    {
       constructBoard();
       printBoard();
       runGame();
    }
    
    void constructBoard(){
        for (int y=0; y<26;y++){
            for (int x=0; x<52;x++){
               board [y][x]= ("O");
            }
        }
    }
    
    void printBoard(){
        for (int y=0; y<26;y++){
            for (int x=0; x<52;x++){
               System.out.print(board[y][x]);
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
        board[xValue][yValue] = "X";
    }
}
