/**
 * The Question class allows us to create questions
 *
 * @author Naman/Jack/Evan
 * @version (a version number or a date)
 */
public class Question
{
    // instance variables
    private String question, answer;
    private String[] choices = new String[4];
    private int dollarAmount;
    private boolean pickedYet;

    /**
     * Constructor for objects of class Question
     */
    public Question(String question, String[] choices, String answer, int dollarAmount)
    {
       this.question = question;
       this.answer = answer;
       this.choices = choices;
       this.dollarAmount = dollarAmount;
       pickedYet = false;
    }
}
