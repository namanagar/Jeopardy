import java.util.*; 
import java.io.*;

/**
 * Logic behind display and functionality of Jeopardy Game
 * 
 * @author Evan Sotos, Naman Agarwal, Jack Chalmers
 * @version December 11 2016
 */

public class Board
{
   
    //Arraylists for game itself (made for scalability)
    private ArrayList<Question> myBoard;
    private ArrayList<Player> playerList;
    
    //Storage values for arraylists and player shifting logic
    private String question, answer, choice1, choice2, choice3, choice4;
    private int dollarAmount, currentPlayer, pickedCategories;
    
    
    /**
     * Constructor for objects of class Jeopardy
     */
    public Board()
    {
        myBoard = new ArrayList<Question>();
        playerList = new ArrayList<Player>();
        pickedCategories = 0; //Instantiates the number of questions currently off the board
    }
    
    
    /**
     * Called from within the driver class, loads game and display
     * 
     * @param  none
     * @return none
     */
    public void startGame() throws IOException, FileNotFoundException, InterruptedException
    {
        System.out.println("\f");
        inputCategories();
        createPlayers();
        drawBoard();
        currentPlayer = playerList.size(); //Initiate current player variable prior to allowing player to pick
        
        pickPlayer();
    }

    /**
     * Generates between 2 and 4 players based on user input
     * 
     * @param  none
     * @return none
     */
    public void createPlayers()
    {
        boolean input = true;
        int numPlayers;
        Scanner scan = new Scanner(System.in);
        System.out.print("How many players are there in the game? (2-4): ");
        while(input) //Executes to prevent premature game termination
        {
            try { //Try/Catch utilized to protect against poor user input
                numPlayers = scan.nextInt();
                if (numPlayers <= 4 && numPlayers > 1)
                {
                    input = false;
                    String playerInstance; 
                    String[] players = new String[numPlayers];
                    int k = 0;
                    for (int i = 0; i < numPlayers; i++)
                    {
                        System.out.print("Enter Name of Player " + (i + 1) + ": ");
                        if (i == 0 && k == 0)
                            scan.nextLine();
                        playerInstance = scan.nextLine();
                        boolean loadPlayer = true;
                        for (int j = 0; j < numPlayers; j++)
                            {    
                                if (playerInstance.equalsIgnoreCase(players[j]))
                                    {
                                        System.out.println("Player: " + playerInstance + " already exists");
                                        i--;
                                        loadPlayer = false;
                                    }
                                else if (j == (numPlayers-1) && loadPlayer)
                                    {
                                        playerList.add(new Player(playerInstance));
                                        players[k] = playerInstance;
                                        k++;
                                    } 
                            }
                    }
                }
                else
                {
                    System.out.print("Enter a number between 2 and 4: ");
                    scan.nextLine();                    
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid number, please enter a number between 2 and 4: ");
                scan.nextLine();
            } catch (Exception e) { //Catchall message (Theoretically should never run)
                System.out.print("Uncaught error: ");
                scan.nextLine();
            }
        }
        System.out.println("\f"); //Clear UI
       
    }
    
    
    /**
     * Inputs questions from text file
     * 
     * @param  none
     * @return none
     */
    public void inputCategories() throws IOException, FileNotFoundException
    {
        Scanner inF = new Scanner(new File("questions.txt")); //Read from Txt file
        while (inF.hasNextLine())
        {
            
            question = inF.nextLine();
            choice1 = inF.nextLine();
            choice2 = inF.nextLine();
            choice3 = inF.nextLine();
            choice4 = inF.nextLine();
            answer = inF.nextLine();
            dollarAmount = inF.nextInt();  
            inF.nextLine();
            
            myBoard.add(new Question(question, choice1, choice2, choice3, choice4, answer, dollarAmount)); //Generate question in arraylist
        }
        
    }
    
    
    /**
     * Generates homepage UI, live updates on draw due to object referencing
     * 
     * @param  none
     * @return none
     */
    public void drawBoard()
        {
            System.out.println("\f");
            System.out.println("        [1]             [2]            [3]            [4]");
            System.out.println("     Technology      Colleges         Random        Rappers");
            System.out.println("|_______________|_______________|_______________|_______________|");           
            for (int i = 0; i < 3; i++)
            {
                System.out.println("|               |               |               |               |");
                System.out.println("|      "+myBoard.get(i).getDisplayValue()+"      |      "+myBoard.get(i+3).getDisplayValue()+"      |      "+myBoard.get(i+6).getDisplayValue()+"      |      "+myBoard.get(i+9).getDisplayValue()+"      |    ["+(i+1)+"]");
                System.out.println("|               |               |               |               |");
                System.out.println("|---------------|---------------|---------------|---------------|");
            }
            System.out.println("                             Winnings");
            System.out.println("-----------------------------------------------------------------");
            for (int i = 0; i < playerList.size(); i++)
            {
                System.out.print("| "+playerList.get(i).getName() + ": " + playerList.get(i).getWinnings() + "$     ");
            }
            System.out.println("\n-----------------------------------------------------------------");
        }
    
        
    /**
     * Generates question page of the UI
     * 
     * @param  boardNum = Row and Column of question, used to find location within arraylist
     * @return none
     */    
    public void drawBoard(int boardNum) throws IOException, InterruptedException
        {
                boolean input = true;
                int playerAnswer = currentPlayer;
                int pLoops = 0; //Determines how to shift player input within the question page
                
                System.out.print("\f");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("|    "+myBoard.get(boardNum).getQuestion()+"    |");
                System.out.println("-----------------------------------------------------------------");
                for (int i = 0; i < 4; i++)
                    {
                        System.out.println("                         |    "+myBoard.get(boardNum).getChoices(i)+"    |");
                        System.out.println("-----------------------------------------------------------------");
                    }
                
                System.out.print("[--20 Seconds given to answer each question before auto-pass--]");
                while(input)
                {
                        System.out.print("\n"+playerList.get(playerAnswer).getName()+"'s turn: ");  
                    
                        int timer = 20; // wait 20 seconds at most
                        BufferedReader answer = new BufferedReader(new InputStreamReader(System.in));
                        long startTime = System.currentTimeMillis();
                        while ((System.currentTimeMillis() - startTime) < timer * 1000
                                && !answer.ready()) {
                        }
                        
                        if (answer.ready()) {
                            if (answer.readLine().equalsIgnoreCase(myBoard.get(boardNum).getAnswer()))
                                {
                                    input = false;
                                    playerList.get(playerAnswer).setWinnings(myBoard.get(boardNum).getDollarAmount());
                                    System.out.println("\n\n-----------------------------------------------------------------");
                                    System.out.println("                             Answer: " + myBoard.get(boardNum).getAnswer());
                                    System.out.println("                          Returning home in 5 seconds");
                                    System.out.println("-----------------------------------------------------------------");
                                    Thread.sleep(5000);
                                    drawBoard();
                                    pickPlayer();
                                }
                            else
                                {
                                    input = false;
                                    playerList.get(playerAnswer).setWinnings(myBoard.get(boardNum).getDollarAmount()*-1);
                                    System.out.println("\n\n-----------------------------------------------------------------");
                                    System.out.println("                             Answer: " + myBoard.get(boardNum).getAnswer());
                                    System.out.println("                          Returning home in 5 seconds");
                                    System.out.println("-----------------------------------------------------------------");
                                    Thread.sleep(5000);
                                    drawBoard();
                                    pickPlayer();
                                }
                        } else if (pLoops < playerList.size() - 1) {
                            if (playerAnswer < (playerList.size() - 1))
                                playerAnswer++;
                            else 
                                playerAnswer = 0;
                            pLoops++;
                        } else 
                        {
                            System.out.println("Nobody answered! Returning to board in 4 seconds");
                            input = false;
                            System.out.println("\n\n-----------------------------------------------------------------");
                            System.out.println("                             Answer: " + myBoard.get(boardNum).getAnswer());
                            System.out.println("-----------------------------------------------------------------");
                            Thread.sleep(4000);
                            drawBoard();
                            pickPlayer();
                        }
                    }
    }
    
    
    /**
     * Prints end page of game
     * 
     * @param  none
     * @return none
     */
    public void printOutcome()
    {
        int maxPlayer = 0;
        
        for (int i = 0; i < playerList.size(); i++)
        {
            if (playerList.get(i).getWinnings() > playerList.get(maxPlayer).getWinnings())
                {
                    maxPlayer = i;
                }
        }
        System.out.println("\f");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("                             Winner");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("The Winner is: " + playerList.get(maxPlayer).getName() + " with: " + playerList.get(maxPlayer).getWinnings() + "$");
        System.out.println("                             Playerboard");
        System.out.println("-----------------------------------------------------------------");
        for (int i = 0; i < playerList.size(); i++)
            System.out.println(playerList.get(i).getName() + " has a final score of: " + playerList.get(i).getWinnings());
    }
    
    
    /**
     * Player shifting on homepage and logic callouts for selecting questions
     * 
     * @param  none
     * @return none
     */
    public void pickPlayer() throws IOException, InterruptedException
    {
          if (pickedCategories == myBoard.size())
                    printOutcome();
          else {
           if (currentPlayer < playerList.size() - 1)
            currentPlayer++;
           else
            currentPlayer = 0;
           
           int column = getColumn();
           int row = getRow();
            
           if (myBoard.get((row+column)).getPickedYet())
            {
               if (currentPlayer > 0)
                currentPlayer--;
               else
                currentPlayer = (playerList.size()-1);
                System.out.println("That option has already been picked!");
                pickPlayer();
            } else {
                pickedCategories++;
                myBoard.get(row+column).setPickedYet();
                drawBoard(row+column);
           }
        }
    }
    
    
    /**
     * Called from within pickPlayer() method, used to find column of stored question
     * 
     * @param  none
     * @return column = column or category of question, used to locate Y-Index of question within the grid
     */
    public int getColumn()
        {
          Scanner scan = new Scanner(System.in);
          boolean input = true;
          int column = 0;
          System.out.print(playerList.get(currentPlayer).getName() + ", enter a column number (1-4): ");
          while (input)//"Idiot-proof" column input loop
           {
               try {
                   column = scan.nextInt();
                   if (column > 0 && column < 5)
                    {
                        input = false;
                        return 3*(column-1);
                    } else {
                        System.out.print("\nEnter a number between 1 and 4: ");
                        scan.nextLine();
                    }
               } catch (InputMismatchException e) {
                System.out.print("\nInvalid number, please enter a number between 1 and 4: ");
                scan.nextLine();
               } catch (Exception e) {
                System.out.println("Uncaught error");
                scan.nextLine();
               }
            }
          return column;
        }
    
        
    /**
     * Called from within the pickPlayer() method, used to find row of stored question
     * 
     * @param  none
     * @return row = Row of stored question, utilized to find X-Index of question within the grid
     */    
    public int getRow()
    {
         Scanner scan = new Scanner(System.in);
         boolean input = true;
         int row = 0;
         System.out.print(playerList.get(currentPlayer).getName() + ", enter a row number (1-3): ");
         while (input) //"Idiot-proof" Row input loop
           {
               try {
                   row = scan.nextInt();
                   if (row > 0 && row < 4)
                    {
                        input = false;
                        return (row-1);
                    } else {
                        System.out.print("\nEnter a number between 1 and 3");
                        scan.nextLine();
                    }
               } catch (InputMismatchException e) {
                System.out.print("\nInvalid number, please enter a number between 1 and 3");
                scan.nextLine();
               } catch (Exception e) {
                System.out.println("Uncaught error");
                scan.nextLine();
               }
            }
         return row;
    }
}
