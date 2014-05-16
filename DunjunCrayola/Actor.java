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
    private Actor[][] gridReference;
    private int row, column;
    
    private BufferedImage image;
    
    private int attack, magic, defense;
    private int health, maxHealth, mana, maxMana;
    private Item weapon;
    private Item armor;
    private ArrayList<Item> inventory;
    
    public Actor()
    {
        gridReference = null;
        row = -1;
        column = -1;
         
        image = null;
        
        attack = 1;
        magic = 1;
        defense = 0;
        maxHealth = 10;
        health = maxHealth;
        maxMana = 10;
        mana = maxMana;
        weapon = null;
        armor = null;
        inventory = new ArrayList<Item>();
    }
    
    public boolean move(int r, int c)
    {
        if(validLocationWithinGrid(r, c))
        {
            gridReference[r][c] = this;
            gridReference[this.row][this.column] = null;
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
    
    //Drawing
    public void draw(Graphics g)
    {
        if(image != null)
            ((Graphics2D)g).drawImage(image, column * 32, row * 32, null);
        else
        {
            g.setColor(Color.BLACK);
            g.fillRect(column * 32, row * 32, 32, 32);
        }
    }
    
    //Stat Handling   
    public BufferedImage getImage(){return image;}
    public int getAttack(){return attack;}
    public int getMagic(){return magic;}
    public int getDefense(){return defense;}
    public int getHealth(){return health;}
    public int getMaxHealth(){return maxHealth;}
    public int getMana(){return mana;}
    public int getMaxMana(){return maxMana;}
    public Item getWeapon(){return weapon;}
    public Item getArmor(){return armor;}
    public ArrayList<Item> getInventory(){return inventory;}
    
    public void setImage(BufferedImage image){this.image = image;}
    public void setAttack(int attack){this.attack = attack;}
    public void setMagic(int magic){this.magic = magic;}
    public void setDefense(int defense){this.defense = defense;}
    public void setHealth(int health){this.health = health;}
    public void setMaxHealth(int maxHealth){this.maxHealth = maxHealth;}
    public void setMana(int mana){this.mana = mana;}
    public void setMaxMana(int maxMana){this.maxMana = maxMana;}
    public void setWeapon(Item weapon){this.weapon = weapon;}
    public void setArmor(Item armor){this.armor = armor;}
    
    public boolean addToInventory(Item i){inventory.add(i);return true;}
    public boolean removeFromInventory(Item i){inventory.remove(i);return true;}
    
    //Grid Handling
    public boolean putSelfIntoGrid(int r, int c, Actor[][] grid)
    {
        if(grid != null)
        {
            gridReference = grid;
            gridReference[r][c] = this;
            this.row = r;
            this.column = c;
            return true;
        }
        else
        {
            this.row = -1;
            this.column = -1;
            return false;
        }
    }
    
    public boolean removeSelfFromGrid()
    {
        if(gridReference != null)
        {
            gridReference[row][column] = null;
            return true;
        }
        return false;
    }
    
    public Actor[][] getGrid() {return gridReference;}
    
    public int getRow(){return row;}
    
    public int getColumn(){return column;}
    
    //Helper Methods
    public boolean validLocationWithinGrid(int r, int c)
    {
        if(gridReference != null)
        {
            if(r < 0 || r >= gridReference.length)
                return false;
            else if(c < 0 || c > gridReference[r].length - 1)
                return false;
            return true;
        }
        return false;
    }
}
