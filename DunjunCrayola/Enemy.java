
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    public Enemy()
    {
        super();
        
        //Default Enemy Characteristics
        setMaxHealth(10);
        setHealth(10);
        setAlignment(Alignment.EVIL);
    }
}
