import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/**
 * Write a description of class Actor here.
 * 
 * @author Greg McDonald
 * @version (a version number or a date)
 */
public class Actor
{
    private Actor[][] actorGridReference;
    private Tile[][] tileGridReference;
    private int row, column;
    
    private BufferedImage image;
    
    private int attack, magic, defense, resistance;
    private int health, maxHealth, mana, maxMana;
    private Weapon weapon;
    private Armor armor;
    private ArrayList<Item> inventory;
    
    private ArrayList<StatusEffect> statusEffects;
    
    public Actor()
    {
        actorGridReference = null;
        tileGridReference = null;
        row = -1;
        column = -1;
         
        image = null;
        
        attack = 0;
        magic = 0;
        defense = 0;
        resistance = 0;
        maxHealth = 1;
        health = maxHealth;
        maxMana = 1;
        mana = maxMana;
        weapon = null;
        armor = null;
        inventory = new ArrayList<Item>();
        statusEffects = new ArrayList<StatusEffect>();
    }
    
    public boolean move(int r, int c)
    {
        if(validLocationWithinGrid(r, c) && (r != this.row || c != this.column) && tileGridReference[r][c].canEnter() && actorGridReference[r][c] == null)
        {
            actorGridReference[this.row][this.column] = null;
            actorGridReference[r][c] = this;
            this.row = r;
            this.column = c;
            return true;
        }
        else
        {
            //removeSelfFromGrid();
            return false;
        }
    }
    
    public void takeTurn()
    {
        //Perform no by Default
        for(StatusEffect statusEffect : getStatusEffects())
        {
            statusEffect.update();
        }
    }
    
    //Combat
    public void performAttack(Direction d)
    {
        Weapon weapon = getWeapon();
        if(weapon != null)
        {
            int targetRow = getRow();
            int targetCol = getColumn();
            switch(d)
            {
                case NORTH:
                    targetRow--;
                    break;
                case SOUTH:
                    targetRow++;
                    break;
                case EAST:
                    targetCol++;
                    break;
                case WEST:
                    targetCol--;
                    break;
                default:
                    //Do Nothing
                    break;
            }
            switch(weapon.getWeaponType())
            {
                case MELEE:
                    //Not yet implemented
                    break;
                case RANGED:
                    if(isEmpty(targetRow, targetCol))
                    {
                        Projectile p = new Projectile();
                        p.setDirection(d);
                        p.setAttack(getAttack() + weapon.getAttack());
                        p.setMagic(getMagic() + weapon.getMagic());
                        for(StatusEffect statusEffect : weapon.getStatusEffects())
                        {
                            p.addStatusEffect(statusEffect);
                        }
                        p.putSelfIntoGrid(targetRow, targetCol, actorGridReference, tileGridReference);
                    }
            }
        }
    }
    
    //Drawing
    public void draw(Graphics g)
    {
        if(image != null)
            ((Graphics2D)g).drawImage(image, column * 32, row * 32, null);
        else
        {
            g.setColor(Color.BLUE);
            g.fillRect(column * 32, row * 32, 32, 32);
        }
    }
    
    //Stat Handling   
    public BufferedImage getImage(){return image;}
    public int getAttack(){return attack;}
    public int getMagic(){return magic;}
    public int getDefense(){return defense;}
    public int getResistance(){return resistance;}
    public int getHealth(){return health;}
    public int getMaxHealth(){return maxHealth;}
    public int getMana(){return mana;}
    public int getMaxMana(){return maxMana;}
    public Weapon getWeapon(){if(weapon != null)return weapon;else return new Weapon();}
    public Armor getArmor(){if(armor != null)return armor;else return new Armor();}
    public ArrayList<Item> getInventory(){return inventory;}
    
    public void setImage(BufferedImage image){this.image = image;}
    public void setAttack(int attack){this.attack = attack;}
    public void setMagic(int magic){this.magic = magic;}
    public void setDefense(int defense){this.defense = defense;}
    public void setResistance(int resistance){this.resistance = resistance;}
    public void setHealth(int health){if(health > this.health)this.health = getMaxHealth();else this.health = health;}
    public void setMaxHealth(int maxHealth){this.maxHealth = maxHealth;}
    public void setMana(int mana){if(mana > this.mana)this.mana = getMaxMana();else this.mana = mana;}
    public void setMaxMana(int maxMana){this.maxMana = maxMana;}
    public void setWeapon(Weapon weapon){this.weapon = weapon;}
    public void setArmor(Armor armor){this.armor = armor;}
    
    public boolean addToInventory(Item i){inventory.add(i);return true;}
    public boolean removeFromInventory(Item i){return inventory.remove(i);}
    
    public boolean addStatusEffect(StatusEffect s){s.addEffectToActor(this);return true;}
    public boolean removeStatusEffect(StatusEffect s)
    {
        for(StatusEffect effect : statusEffects)
        {
            if(s.equals(effect))
            {
                effect.removeEffectFromActor();
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<StatusEffect> getStatusEffects(){return statusEffects;}
    
    //Damage
    public void dealTrueDamage(int damageAmount)
    {
        health -= damageAmount;
    }
    
    public void dealPhysicalDamage(int damageAmount)
    {
        int actualDamage = damageAmount - (defense + getArmor().getDefense());
        if(actualDamage > 0)
            dealTrueDamage(actualDamage);
        else
            dealTrueDamage(0);
    }    
    
    public void dealMagicDamage(int damageAmount)
    {
        int actualDamage = damageAmount - (resistance + getArmor().getResistance());
        if(actualDamage > 0)
            dealTrueDamage(actualDamage);
        else
            dealTrueDamage(0);
    } 
    
    //I Don't Know What to Label This
    public boolean equals(Actor a)
    {
        return a.getAttack() == this.getAttack() && a.getMagic() == this.getMagic() && a.getDefense() == this.getDefense() && a.getResistance() == this.getResistance() && a.getHealth() == this.getHealth() && a.getMaxHealth() == this.getMaxHealth() 
               && a.getMana() == this.getMana() && a.getMaxMana() == this.getMaxMana() && a.getImage().equals(this.getImage());
    }
    
    //Grid Handling
    public boolean putSelfIntoGrid(int r, int c, Actor[][] actorGrid, Tile[][] tileGrid)
    {
        boolean successfulPut = true;
        if(actorGrid != null)
        {
            actorGridReference = actorGrid;
            actorGridReference[r][c] = this;
            this.row = r;
            this.column = c;
        }
        else
        {
            this.row = -1;
            this.column = -1;
            successfulPut = false;
        }
        
        if(tileGrid != null)
        {
            tileGridReference = tileGrid;
        }
        else
        {
            successfulPut = false;
        }
        
        return successfulPut;
    }
    
    public boolean removeSelfFromGrid()
    {
        if(actorGridReference != null)
        {
            actorGridReference[row][column] = null;
            return true;
        }
        return false;
    }
    
    public Actor[][] getActorGrid() {return actorGridReference;}
    
    public Tile[][] getTileGrid() {return tileGridReference;}
    
    public int getRow(){return row;}
    
    public final void setRow(int r){row = r;}
    
    public int getColumn(){return column;}
    
    public final void setColumn(int c){column = c;}
    
    //Helper Methods
    public boolean validLocationWithinGrid(int r, int c)
    {
        if(actorGridReference != null)
        {
            if(r < 0 || r >= actorGridReference.length)
                return false;
            else if(c < 0 || c > actorGridReference[r].length - 1)
                return false;
            return true;
        }
        return false;
    }
    
    public boolean isEmpty(int r, int c)
    {
        if(actorGridReference != null && tileGridReference != null)
        {
            return actorGridReference[r][c] == null && tileGridReference[r][c].canEnter();
        }
        return false;
    }
}
