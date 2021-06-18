 
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

public class version0_3_3
{
    final int WIDTH = 26;
    final int HEIGHT = 52;
    
    String currentBoard [][] = new String[WIDTH][HEIGHT];
    String nextBoard [][] = new String[WIDTH][HEIGHT];
    public version0_3_3()
    {
       constructBoard();
       printBoard();
       runGame();
    }
    
    void constructBoard(){
        for (int x=0; x<WIDTH;x++){
            for (int y=0; y<HEIGHT;y++){
               currentBoard [x][y]= ("O");
            }
        }
    }
    
    void printBoard(){
        for (int x=0; x<WIDTH;x++){
            for (int y=0; y<HEIGHT;y++){
               System.out.print(currentBoard[x][y]);
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
        currentBoard[yValue][xValue] = "X";
    }
    
    void calculateNextState(){
        for (int x=0; x<WIDTH;x++){
            for (int y=0; y<HEIGHT; y++){
                int neighbors = 0;
                if (x!=0) if (currentBoard [x-1][y].equals("X")) neighbors++;
                if (x+1!=WIDTH) if (currentBoard [x+1][y].equals("X")) neighbors++;
                if (y+1!=HEIGHT){
                    if (currentBoard [x][y+1].equals("X")) neighbors++;
                    if (x-1!=-1) if (currentBoard [x-1][y+1].equals("X")) neighbors++;
                    if (x+1!=WIDTH) if (currentBoard [x+1][y+1].equals("X")) neighbors++;
                }
                if (y!=0){
                    if (currentBoard [x][y-1].equals("X")) neighbors++;
                    if (x-1!=-1) if (currentBoard [x-1][y-1].equals("X")) neighbors++;
                    if (x+1!=WIDTH) if (currentBoard [x+1][y-1].equals("X")) neighbors++;
                }
                
                System.out.println(x+""+y+""+neighbors);
            }
        }
    }
    
}
