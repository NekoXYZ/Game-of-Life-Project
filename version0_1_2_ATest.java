
/**
 * Purpose of overall Version: to get a functional 2d grid
 * Purpose: have the 2d grid print out the position (a1-z26)
 *
 *
 *
 * @author Corwin
 * @version 14/05/21
 */
public class version0_1_2_ATest
{
    String board [][] = new String[26][26];
    public version0_1_2_ATest()
    {
       constructBoard();
       
    }
    
    void constructBoard(){
        for (int x=0; x<26;x++){
            for (int y=0; y<26;y++){
               board [x][y]= ("a" + y);
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
