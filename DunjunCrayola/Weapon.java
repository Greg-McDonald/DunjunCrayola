import java.util.ArrayList;
/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends Item
{
    private WeaponType weaponType;
    private int attackBonus, magicBonus;
    private ArrayList<StatusEffect> statusEffects;
    
    public Weapon()
    {
        super();
        weaponType = WeaponType.MELEE;
        attackBonus = 0;
        magicBonus = 0;
        statusEffects = new ArrayList<StatusEffect>();
    }
    
    //Fields
    public ArrayList<StatusEffect> getStatusEffects(){return statusEffects;}
    public boolean addStatusEffect(StatusEffect statusEffect){return statusEffects.add(statusEffect);}
    public boolean removeStatusEffect(StatusEffect statusEffect){return statusEffects.remove(statusEffect);}
    
    public WeaponType getWeaponType(){return weaponType;}
    public void setWeaponType(WeaponType type){this.weaponType = type;}
    
    public int getAttack(){return attackBonus;}
    public void setAttack(int attack){this.attackBonus = attack;}
    
    public int getMagic(){return magicBonus;}
    public void setMagic(int magic){this.magicBonus = magic;}
}
