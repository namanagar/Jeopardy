
/**
 * The Player class allows us to create Player objects that have a name (String) and an amount of winnings (int)
 * 
 * @author Naman/Jack/Evan
 * @version December 11 2016
 */
public class Player
{
    // instance variables
    private String name;
    private int winnings;

    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
       this.name = name;
       winnings = 0;
    }
    
    
    /**
     * Adds or removes parsed value from player's total winnings
     * 
     * @param  int val = weight of question answered
     * @return none
     */
    public void setWinnings(int val)
        {
            winnings += val;
        }
    
        
    /**
     * Returns player's net winnings
     * 
     * @param  none
     * @return winnings = net winnings of player
     */
    public int getWinnings()
        {
            return winnings;
        }
    
        
    /**
     * Returns name of player
     * 
     * @param  none
     * @return name
     */
    public String getName()
       {
           return name;
       }
}
