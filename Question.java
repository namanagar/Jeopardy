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
    private Array[String] choices = new Array[4];
    private int dollarAmount;
    private boolean pickedYet;

    /**
     * Constructor for objects of class Question
     */
    public Question(String question, Array[] choices, String answer, int dollarAmount, boolean pickedYet)
    {
       this.question = question;
       this.answer = answer;
       this.choices = choices;
       this.dollarAmount = dollarAmount;
       this.pickedYet = pickedYet;
    }
}
