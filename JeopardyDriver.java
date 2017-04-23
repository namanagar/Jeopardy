import java.util.Scanner;
import java.io.*;

/**
 * Driver for Jeopardy Game
 * 
 * @author Evan Sotos, Naman Agarwal, Jack Chalmers
 * @version December 11 2016
 */
public class JeopardyDriver
{
    public static void main(String [] args) throws IOException, FileNotFoundException, InterruptedException
    {
        boolean playGame = true;
        Scanner scan = new Scanner(System.in); //Input object for Scanner
        while (playGame)
        {
            Board newBoard = new Board(); // Creates new object of Board class
            newBoard.startGame();
            
            System.out.print("\n\t\t\tPlay again (y or n): ");
            if (scan.nextLine().equalsIgnoreCase("Y"))
                playGame = true;
            else
            {
                playGame = false;
                System.out.print("\f");
                System.out.println("\n\n\t\t\tGame Over!");
            }
       }
        
    }
}
