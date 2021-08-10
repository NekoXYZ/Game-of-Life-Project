
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

public class Component_trialing_display
{
    final int WIDTH = 52;
    final int HEIGHT = 26;

    String currentBoard [][] = new String[WIDTH][HEIGHT];
    String nextBoard [][] = new String[WIDTH][HEIGHT];
    public Component_trialing_display()
    {
        constructBoard();
        printBoard();
        runGame();
    }

    void constructBoard(){
        for (int x=0; x<WIDTH;x++){
            for (int y=0; y<HEIGHT;y++){
                currentBoard [x][y]= ("-");
            }
        }
    }

    void printBoard(){
        for (int y=0; y<HEIGHT;y++){
            for (int x=0; x<WIDTH;x++){
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

                case "turn off":
                turnOff();
                printBoard();
                break;

                case "check":
                int xValue = input.nextInt(); //what x position
                int yValue = input.nextInt(); //what y position
                System.out.println(checkNeighbors(xValue,yValue));
                break;

                case "next stage":
                case "step":
                case "next":
                calculateNextState();
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
        int xValue = input.nextInt();//what x position
        int yValue = input.nextInt();//what y position
        currentBoard[xValue][yValue] = "X";//Turn the tile at (x,y) to an X (on)
    }

    void turnOff(){
        Scanner input = new Scanner(System.in); //creating input
        int xValue = input.nextInt(); //what x position
        int yValue = input.nextInt(); //what y position
        currentBoard[xValue][yValue] = "-"; //Turn the tile at (x,y) to an O (off)
    }

    int checkNeighbors(int x, int y){ //counts alive neighbors
        int neighbors = 0;
        if (x!=0) 
            if (currentBoard [x-1][y].equals("X")) 
                neighbors++;
        if (x+1!=WIDTH) 
            if (currentBoard [x+1][y].equals("X")) 
                neighbors++;
        if (y+1!=HEIGHT){ //if y+1 is not out of bounds
            if (currentBoard [x][y+1].equals("X")) 
                neighbors++;
            if (x!=0)//if x-1 is not out of bounds
                if (currentBoard [x-1][y+1].equals("X")) //if the tile to the bottom left is on
                    neighbors++; //increase neighbors
            if (x+1!=WIDTH) //if x+1 is not out of bounds
                if (currentBoard [x+1][y+1].equals("X")) 
                    neighbors++;
        }
        if (y!=0){//if y-1 is not out of bounds
            if (currentBoard [x][y-1].equals("X")) 
                neighbors++;
            if (x!=0) //if x-1 is not out of bounds
                if (currentBoard [x-1][y-1].equals("X")) 
                    neighbors++;
            if (x+1!=WIDTH) //if x+1 is not out of bounds
                if (currentBoard [x+1][y-1].equals("X")) 
                    neighbors++;
        }
        return neighbors;
    }
    
    //calculate what the next board state will be, then set the current board to be equal to those values.
    void calculateNextState(){
        for (int x=0; x<WIDTH;x++){
            for (int y=0; y<HEIGHT; y++){
                int neighbors = checkNeighbors(x,y);

                if (neighbors<=1) nextBoard[x][y]="-";
                else if (neighbors == 2) nextBoard[x][y]=currentBoard[x][y];
                else if (neighbors == 3) nextBoard[x][y]="X";
                else if (neighbors >= 4) nextBoard[x][y]="-";
            }
        }
        //set the current board to be the same as nextboard
        for (int x=0; x<WIDTH;x++){ 
            for (int y=0; y<HEIGHT; y++){
                currentBoard[x][y] = nextBoard[x][y];
            }
        }
    }
   }
