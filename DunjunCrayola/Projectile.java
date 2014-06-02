import java.util.ArrayList;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    private CardinalDirection direction;

    public Projectile()
    {
        super();
        direction = direction.NORTH;
    }

    //Important Note: When turns are taken, Projectiles should be moved first to avoid collisions with the firing Actor
    @Override
    public void act()
    {
        super.act();
        for(StatusEffect statusEffect : getStatusEffects()) //Undoes the decay of effects on projectiles, as they exist only to be applied. They should not apply their effects or decay
        {
            statusEffect.setCyclesRemaining(1 + statusEffect.getCyclesRemaining());
        }
        switch(direction)
        {
            case NORTH:
            move(getRow() - 1, getColumn());
            break;
            case SOUTH:
            move(getRow() + 1, getColumn());
            break;
            case EAST:
            move(getRow(), getColumn() + 1);
            break;
            case WEST:
            move(getRow(), getColumn() - 1);
            break;
            default:
            //Do Nothing
            break;
        }
    }

    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        if(getImage() != null)
            ((Graphics2D)g).drawImage(getImage(), getColumn() * 32, getRow() * 32, null);
        else
        {
            g.setColor(Color.CYAN);
            g.fillRect(getColumn() * 32 + 8, getRow() * 32 + 8, 16, 16);
        }
    }

    //Combat
    public void hitActor(Actor actor)
    {
        if(actor.getAlignment() != this.getAlignment())
        {
            actor.dealPhysicalDamage(this.getAttack());
            actor.dealMagicDamage(this.getMagic());
            for(StatusEffect statusEffect : getStatusEffects())
            {
                actor.addStatusEffect(statusEffect);
            } 
        }
    }

    //Overriden Behaviors of Actor Class
    //Move now deals damage if an Actor occupies the space it attempts to move into
    public boolean move(int r, int c)
    {
        if(validLocationWithinGrid(r, c))
        {
            Actor[][] actorGrid = getActorGrid();
            if((r != getRow() || c != getColumn()) && getTileGrid()[r][c].canEnter() && actorGrid[r][c] == null)
            {
                actorGrid[getRow()][getColumn()] = null;
                actorGrid[r][c] = this;
                setRow(r);
                setColumn(c);
                return true;
            }
            else if(actorGrid[r][c] != null)
            {
                if(!(actorGrid[r][c] instanceof Projectile))
                {
                    hitActor(actorGrid[r][c]);
                    removeSelfFromGrid();
                }
                else if(!actorGrid[r][c].isLocked())
                {
                    actorGrid[r][c].takeTurn();
                    move(r,c);
                }
                return true;
            }
            removeSelfFromGrid();
            return false;
        }
        else
        {
            removeSelfFromGrid();
            return false;
        }
    }

    //Does not begin effects when added, as the effects only exist to be applied to the eventual target
    @Override
    public boolean addStatusEffect(StatusEffect statusEffect){return getStatusEffects().add(statusEffect);}

    @Override
    public boolean removeStatusEffect(StatusEffect effect)
    {
        for(int i = 0; i < getStatusEffects().size(); i++)
        {
            if(getStatusEffects().get(i).equals(effect))
            {
                getStatusEffects().remove(i);
                return true;
            }
        }
        return false;
    }

    //Fields
    public void setDirection(CardinalDirection direction){this.direction = direction;}

    public CardinalDirection getDirection(){return direction;}
}
