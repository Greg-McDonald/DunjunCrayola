
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Write a description of class GameFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable
{
    private final int TILE_WIDTH = 32;
    private final int TILE_HEIGHT = 32;
    
    private Map<String, BufferedImage> imageDirectory;
    private ArrayList<Button> buttons;
    private Actor[][] actorGrid;
    private Tile[][] tileGrid;
    private Actor player;
    private Location selectedCell;

    public GamePanel()
    {
        this(1280,704);
    }

    public GamePanel(int panelWidth, int panelHeight)
    {
        //super.setLocation(0,0);
        super.setPreferredSize(new Dimension(panelWidth, panelHeight));

        //super.addMouseListener(this);
        addMouseListener(this);

        //super.addMouseMotionListener(this);
        addMouseMotionListener(this);

        //super.addKeyListener(this);
        //addKeyListener(this);

        //setFocusable(true);
        //requestFocusInWindow();
        
        //Load all images
        imageDirectory = new HashMap<String, BufferedImage>();
        loadAllImages();
        
        //Initialize button array and create all buttons
        buttons = new ArrayList<Button>();
        Button button = (new Button(this) {
            public void performClickAction(){
                takeTurn();
            }
        });
        button.setLocation(800, 10);
        button.setImage(imageDirectory.get("defaultButton"));
        buttons.add(button);
        
        //Accounting for inventory and info panel space
        int rightHorizontalTileOffset = 7;
        int verticalTileOffset = 0;
        if(panelWidth < rightHorizontalTileOffset * TILE_WIDTH)
            rightHorizontalTileOffset = 0;
        if(panelHeight < verticalTileOffset * TILE_HEIGHT)
            verticalTileOffset = 0;
        //Creation and sizing of game play grid with respect to window size, may change to standard amount in future
        actorGrid = new Actor[panelHeight / 32 - verticalTileOffset][panelWidth / 32 - rightHorizontalTileOffset];
        tileGrid = new Tile[panelHeight / 32 - verticalTileOffset][panelWidth / 32 - rightHorizontalTileOffset];
        
        //Builds a random tile layout and distirbutes enemies randomly, will definitly change this system
        buildTileLayout();
        randomlyPlaceEnemies();
        
        //Player creation
        player = new Player();
        selectedCell = new Location(0,0);
        player.setImage(imageDirectory.get("player1"));
        Weapon defaultWeapon = new Weapon();
        defaultWeapon.setAttack(1);
        defaultWeapon.setWeaponType(WeaponType.RANGED);
        player.setWeapon(defaultWeapon);
        player.putSelfIntoGrid(0,0,actorGrid, tileGrid);
    }

    //Updating Method
    public void run()
    {

    }
    
    public void takeTurn()
    {
        actorsTakeTurns();
        unlockAllActors();
    }

    public void actorsTakeTurns()
    {
        player.move(selectedCell.getRow(), selectedCell.getColumn());
        for(int r = 0; r < actorGrid.length; r++)
        {
            for(int c = 0; c < actorGrid[r].length; c++)
            {
                if(actorGrid[r][c] != null)
                    actorGrid[r][c].takeTurn();
            }
        }
    }

    public void unlockAllActors()
    {
        for(int r = 0; r < actorGrid.length; r++)
        {
            for(int c = 0; c < actorGrid[r].length; c++)
            {
                if(actorGrid[r][c] != null)
                    actorGrid[r][c].unlock();
            }
        }
    }

    public Location getPlayerLocation()
    {
        return new Location(player.getRow(), player.getColumn());
    }

    //World Generation Methods
    public void buildTileLayout()
    {
        for(int r = 0; r < tileGrid.length; r++)
        {
            for(int c = 0; c < tileGrid[r].length; c++)
            {
                Tile newTile = new Tile();
                if(Math.random() < .3)
                {
                    newTile.setWalkable(false);
                    newTile.setImage(imageDirectory.get("tile2"));
                }
                else
                {
                    newTile.setWalkable(true);
                    newTile.setImage(imageDirectory.get("dirt1"));
                }
                tileGrid[r][c] = newTile;
            }
        }
    }

    public void randomlyPlaceEnemies()
    {
        //assert tileGrid.length == actorGrid.length;
        for(int r = 0; r < actorGrid.length; r++)
        {
            //assert tileGrid[r].length == actorGrid[r].length;
            for(int c = 0; c < actorGrid[r].length; c++)
            {
                if(tileGrid[r][c].isWalkable() && actorGrid[r][c] == null)
                {
                    if(Math.random() < .1)
                    {
                        Enemy enemy = new Enemy();
                        enemy.setImage(imageDirectory.get("skeleton1"));
                        enemy.putSelfIntoGrid(r,c,actorGrid,tileGrid);
                    }
                }
            }
        }
    }

    public void loadAllImages()
    {
        imageDirectory.put("tile1", ImageLoader.loadImage("Images/tile_1.png"));
        imageDirectory.put("tile2", ImageLoader.loadImage("Images/tile_2.png"));
        imageDirectory.put("wall1", ImageLoader.loadImage("Images/wall_1.png"));
        imageDirectory.put("wall2", ImageLoader.loadImage("Images/wall_2.png"));
        imageDirectory.put("dirt1", ImageLoader.loadImage("Images/dirt_1.png"));
        imageDirectory.put("dirt2", ImageLoader.loadImage("Images/dirt_2.png"));

        imageDirectory.put("player1", ImageLoader.loadImage("Images/player_v1.png"));
        imageDirectory.put("slime1", ImageLoader.loadImage("Images/slime_1.png"));
        imageDirectory.put("skeleton1", ImageLoader.loadImage("Images/skelly_1.png"));
        
        imageDirectory.put("arrow", ImageLoader.loadImage("Images/arrow.png"));
        
        imageDirectory.put("defaultButton", ImageLoader.loadImage("Images/defaultButton.png"));
    }

    //Painting
    public void paintComponent(Graphics g)
    {
        clearScreen(g, Color.GRAY);
        //Draw all tiles
        for(int r = 0; r < tileGrid.length; r++)
        {
            for(int c = 0; c < tileGrid[r].length; c++)
            {
                if(tileGrid[r][c] != null)
                    tileGrid[r][c].draw(r,c,g);
            }
        }
        //Draw all Actors
        for(int r = 0; r < actorGrid.length; r++)
        {
            for(int c = 0; c < actorGrid[r].length; c++)
            {
                if(actorGrid[r][c] != null)
                    actorGrid[r][c].draw(g);
                g.setColor(Color.BLACK);
                g.drawRect(c * 32, r * 32, 32, 32);
            }
        }
        //Draw all buttons
        for(Button button : buttons)
        {
            button.draw(g);
        }
        
        //Draw Selected Cell
        g.setColor(Color.RED);
        g.drawRect(selectedCell.getColumn() * 32 + 1, selectedCell.getRow() * 32 + 1, 30, 30);

        drawDebugWindow(g, Color.RED);
    }

    public void drawDebugWindow(Graphics g, Color color)
    {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("Position: (" + player.getRow() + ", " + player.getColumn() + ")");
        lines.add("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        lines.add("Atk: " + player.getAttack() + " Mgc: " + player.getMagic() + " Def: " + player.getDefense() + " Res: " + player.getResistance());

        g.setColor(color);
        int verticalOffset = 10;
        for(String s : lines)
        {
            g.drawString(s, 2, verticalOffset);
            verticalOffset += 12;
        }
    }

    public void clearScreen(Graphics g, Color color)
    {
        g.setColor(color);
        g.fillRect(0,0,getWidth(), getHeight());   
    }

    //Key Handling Methods
    public void keyPressed(KeyEvent ke)
    {
        //player.move(3,3);
        if(ke.getKeyCode() == KeyEvent.VK_DOWN)
        {
            //player.move(player.getRow() + 1, player.getColumn());
            selectedCell.setLocation(player.getRow() + 1, player.getColumn());
        }
        if(ke.getKeyCode() == KeyEvent.VK_UP)
        {
            //player.move(player.getRow() - 1, player.getColumn());
            selectedCell.setLocation(player.getRow() - 1, player.getColumn());
        }
        if(ke.getKeyCode() == KeyEvent.VK_LEFT)
        {
            //player.move(player.getRow(), player.getColumn() - 1);
            selectedCell.setLocation(player.getRow(), player.getColumn() - 1);
        }
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            //player.move(player.getRow(), player.getColumn() + 1);
            selectedCell.setLocation(player.getRow(), player.getColumn() + 1);
        }
        if(ke.getKeyCode() == KeyEvent.VK_SPACE)
        {
            player.performAttack(getPlayerLocation().getDirectionTowards(selectedCell), imageDirectory);
        }
        if(ke.getKeyCode() == KeyEvent.VK_T)
        {
            actorsTakeTurns();
            unlockAllActors();
        }
        repaint();
    }

    public void keyReleased(KeyEvent ke)
    {

    }

    public void keyTyped(KeyEvent ke){}

    //Mouse Button Handling Methods
    public void mousePressed(MouseEvent me)
    {
        for(Button b : buttons)
        {
            b.handleMousePress(me);
        }
        repaint();
    }

    public void mouseReleased(MouseEvent me)
    {
        for(Button b : buttons)
        {
            b.handleMouseRelease(me);
        }
        repaint();
    }

    public void mouseClicked(MouseEvent me){}

    //Mouse Motion
    public void mouseMoved(MouseEvent me)
    {
        for(Button b : buttons)
        {
            b.handleMouseMove(me);
        }
        repaint();
    }

    public void mouseDragged(MouseEvent me)
    {
        for(Button b : buttons)
        {
            b.handleMouseDragged(me);
        }
        repaint();
    }

    public void mouseEntered(MouseEvent me){}

    public void mouseExited(MouseEvent me){}

    //Helper Methods
    public void delay(int milliseconds)
    {
        try{
            Thread.sleep(milliseconds);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
