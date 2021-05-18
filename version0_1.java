
/**
 * Purpose: to print out a 26x26 grid with a two dimensional array
 *
 * @author Corwin
 * @version 14/05/21
 */
public class version0_1
{
    int board [][] = new int[26][26];
    public version0_1()
    {
       constructBoard();
       
    }
    
    void constructBoard(){
        for (int x=0; x<26;x++){
            for (int y=0; y<26;y++){
               board [x][y]=(x+1)*(y+1);
            }
        }
        for (int x=0; x<26;x++){
            for (int y=0; y<26;y++){
               System.out.print(board[x][y]);
            }
            System.out.println();
        }
    }
}
