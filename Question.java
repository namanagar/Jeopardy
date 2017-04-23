/**
 * The Question class allows us to create questions
 *
 * @author Naman/Jack/Evan
 * @version December 11 2016
 */
public class Question
{
    // instance variables
    private String question, answer, displayValue;
    private String[] choices = new String[4];
    private int dollarAmount;
    private boolean pickedYet;

    /**
     * Constructor for objects of class Question
     */
    public Question(String question, String choice1, String choice2, String choice3, String choice4, String answer, int dollarAmount)
    {
       this.question = question;
       this.answer = answer;
       choices[0] = choice1;
       choices[1] = choice2;
       choices[2] = choice3;
       choices[3] = choice4;
       this.dollarAmount = dollarAmount;
       displayValue = Integer.toString(dollarAmount);
       pickedYet = false;
    }
    
    
    /**
     * Gets stored value of question for object
     * 
     * @param  none
     * @return question
     */
    public String getQuestion()
        {
            return question;
        }
    
        
    /**
     * Gets stored value of answer for object
     * 
     * @param  none
     * @return answer
     */    
    public String getAnswer()
        {
            return answer;
        }
    
        
    /**
     * Gets stored value of answer choices for object in position (i) of the array
     * 
     * @param  i = position within array
     * @return choices[i] = value of choice stored within that index of the array
     */    
    public String getChoices(int i)
        {
            return choices[i];
        }
    
        
    /**
     * Gets stored value of dollar amount for object
     * 
     * @param  none
     * @return dollarAmount = value of question
     */    
    public int getDollarAmount()
        {
            return dollarAmount;
        }
    
        
    /**
     * Gets stored display value for object
     * 
     * @param  none
     * @return displayValue = returns value that is displayed within the UI
     */    
    public String getDisplayValue()
        {
            return displayValue;
        }
    
        
    /**
     * Returns if question has been selected or not
     * 
     * @param  none
     * @return pickedYet = boolean true or false
     */    
    public Boolean getPickedYet()
        {
            return pickedYet;
        }
    
    /**
     * Sets stored value of pickedYet for object to true, along with updating displayValue
     * 
     * @param  none
     * @return none
     */    
    public void setPickedYet()
        {
            pickedYet = true;
            displayValue = "---";
        }
}
