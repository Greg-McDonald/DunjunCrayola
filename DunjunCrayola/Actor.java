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
    
    public boolean move(int row, int column)
    {
        if(validLocationWithinGrid(row, column))
        {
            gridReference[this.row][this.column] = null;
            gridReference[row][column] = this;
            this.row = row;
            this.column = column;
            return true;
        }
        return false;
    }
    
    //Drawing
    public void draw(Graphics g)
    {
        if(image != null)
            ((Graphics2D)g).drawImage(image, row * 32, column * 32, null);
        else
        {
            g.setColor(Color.BLACK);
            g.fillRect(row * 32, column * 32, 32, 32);
        }
    }
    
    //Grid Handling
    public boolean putSelfIntoGrid(int row, int column, Actor[][] grid)
    {
        if(grid != null)
        {
            gridReference = grid;
            gridReference[row][column] = this;
            this.row = row;
            this.column = column;
            return true;
        }
        return false;
    }
    
    public Actor[][] getGrid() {return gridReference;}
    
    //Helper Methods
    public boolean validLocationWithinGrid(int row, int column)
    {
        if(gridReference != null)
        {
            boolean valid = true;
            if(row < 0 || row > gridReference.length - 1)
                valid = false;
            if(column < 0 || column > gridReference[row].length - 1)
                valid = false;
            return valid;
        }
        return false;
    }
}
