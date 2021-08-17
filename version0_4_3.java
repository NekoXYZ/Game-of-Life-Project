
/**
 * 
 * Purpose of v4: add in a save funtion for easier testing and ease of use 
 *
 * Individual Purpose: add a command to read and print a file
 *
 * @author Corwin
 * @version 14/05/21
 */

import java.util.Scanner; //import scanners for input
import java.io.IOException; //handle errors
import java.io.File; //File handler
import java.io.FileWriter; //writes the files

public class version0_4_3
{
    final int WIDTH = 40;
    final int HEIGHT = 90;

    String currentBoard [][] = new String[WIDTH][HEIGHT];
    String nextBoard [][] = new String[WIDTH][HEIGHT];
    public version0_4_3()
    {
        constructBoard();
        printBoard();
        runGame();
    }

    void constructBoard()//Creates a WIDTH x HEIGHT board full of "-"
    {
        for (int x=0; x<WIDTH;x++){
            for (int y=0; y<HEIGHT;y++){
                currentBoard [x][y]= ("-");
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
                case "on":
                case "activate":
                turnOn();
                printBoard();
                break;

                case "turn off":
                case "off":
                case "deactivate":
                turnOff();
                printBoard();
                break;

                case "check":
                int xValue = input.nextInt(); //what x position
                int yValue = input.nextInt(); //what y position
                System.out.println(checkNeighbors(xValue,yValue));
                break;

                case "next stage":
                case "next state":
                case "next step":
                case "step":
                case "next":
                case "s":
                calculateNextState();
                printBoard();
                break;

                case "save":
                save();
                break;

                case "load":
                load();
                break;

                default :  
                System.out.println("Invalid input"); 
                break; 

            }
        }
    }

    void save(){
        Scanner input2 = new Scanner(System.in); //creating input 2
        System.out.println("enter filename");
        String fileName = input2.nextLine(); //receive the input
        File file =new File (fileName+".csv."); // makes a file by the name of the players input
        try{
            FileWriter writer = new FileWriter(file); //make the filewrite
            for (int x=0; x<WIDTH;x++){
                for (int y=0; y<HEIGHT;y++){ //go through the array
                    writer.write(currentBoard[x][y]+",");//write the value of each y position in this x value and add a ,
                }
                writer.write("\n"); //next line
            }

            writer.flush(); 
            writer.close();
        } catch(IOException e) {};
    }

    void load(){
        Scanner input3 = new Scanner(System.in); //creating input 3
        System.out.println("enter filename");
        String fileName = input3.nextLine(); //receive the input
        File fileRead = new File(fileName); //generate file handle
        String SavedLines[] = new String[HEIGHT];
        String AllLinesAllElements[][]=new String[HEIGHT][WIDTH];//keep all lines that are read in
        int lineCount=0; //keep track of how many lines are read

        try {
            Scanner reader = new Scanner(fileRead); //open the file with the scanner
            while(reader.hasNextLine()){ //Read in the file and stop at the file end
                String line=reader.nextLine();
                SavedLines[lineCount]=line;
                lineCount++;
            }
            
            for (int i=0; i<lineCount; i++){ //test to see if all of the lines have been read in correctly
                
                System.out.println(SavedLines[i]);
            }

        } catch (IOException e) {System.out.println(e);}

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

                if (neighbors == 2) nextBoard[x][y]=currentBoard[x][y];
                else if (neighbors == 3) nextBoard[x][y]="X";
                else  nextBoard[x][y]="-";
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
