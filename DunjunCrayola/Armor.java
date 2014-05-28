import java.util.ArrayList;
/**
 * Write a description of class Armor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Armor extends Item
{
    private int defenseBonus, resistanceBonus;
    private ArrayList<StatusEffect> statusEffects;
    
    public Armor()
    {
        super();
        defenseBonus = 0;
        resistanceBonus = 0;
        statusEffects = new ArrayList<StatusEffect>();
    }
    
    public ArrayList<StatusEffect> getStatusEffects(){return statusEffects;}
    public void addStatusEffect(StatusEffect statusEffect){statusEffects.add(statusEffect);}
    
    public int getDefense(){return defenseBonus;}
    public void setDefence(int defense){this.defenseBonus = defense;}
    
    public int getResistance(){return resistanceBonus;}
    public void setResistance(int resistance){this.resistanceBonus = resistance;}
}
