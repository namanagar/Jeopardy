
/**
 * The Player class allows us to create Player objects that have a name (String) and an amount of winnings (int)
 * 
 * @author Naman/Jack/Evan
 * @version (a version number or a date)
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
}
