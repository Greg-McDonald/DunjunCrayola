
/**
 * Write a description of class Player here.
 * 
 * @author Greg McDonald 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int level; //Base Level Exp is 100
    private int exp;
    private int nextLevelExp;
    
    public Player()
    {
        super();
        level = 1;
        exp = 0;
        nextLevelExp = 100;
        
        //Default Player Statistics
        setMaxHealth(100);
        setHealth(getMaxHealth());
        setMaxMana(100);
        setMana(getMaxMana());
        setAttack(1);
        setMagic(1);
        setDefense(0);
    }
    
    public void levelUp()
    {
        if(exp >= nextLevelExp)
        {
            exp = 0;
            nextLevelExp = calculateLevelUpExpRequirement(level, level++);
            incrementStatistics();
        }
    }
    
    public void incrementStatistics()
    {
        
    }
    
    public int calculateLevelUpExpRequirement(int currentLevel, int levelToReach)
    {
        int expCost = 0;
        while(currentLevel < levelToReach)
            expCost += levelCost(currentLevel++);
        return expCost;
    }
    
    private int levelCost(int levelToReach)
    {
        return (int)(Math.pow(1.5,(double)levelToReach - 1.0) * 100.0);
    }
    
    //Fields
    public int getLevel(){return level;}
    public void setLevel(int level){this.level = level;}
    
    public int getExperience(){return exp;}
    public void setExperience(int experience){this.exp = experience;}
}
