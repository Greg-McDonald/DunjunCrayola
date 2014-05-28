import java.util.ArrayList;
/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends Item
{
    private int attackBonus, magicBonus;
    private ArrayList<StatusEffect> statusEffects;
    
    public Weapon()
    {
        super();
        attackBonus = 0;
        magicBonus = 0;
        statusEffects = new ArrayList<StatusEffect>();
    }
    
    public ArrayList<StatusEffect> getStatusEffects(){return statusEffects;}
    public void addStatusEffect(StatusEffect statusEffect){statusEffects.add(statusEffect);}
    
    public int getAttack(){return attackBonus;}
    public void setAttack(int attack){this.attackBonus = attack;}
    
    public int getMagic(){return magicBonus;}
    public void setMagic(int magic){this.magicBonus = magic;}
}
