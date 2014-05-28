
/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    private Direction direction;
    
    public Projectile()
    {
        super();
        direction = direction.NORTH;
    }
    
    //Potentially need to override move method of Actor class to produce desired behavior
    
    @Override
    public void takeTurn()
    {
        switch(direction)
        {
            case NORTH:
                move(getRow() - 1, getColumn(), getTileGrid());
                break;
            case SOUTH:
                move(getRow() + 1, getColumn(), getTileGrid());
                break;
            case EAST:
                move(getRow(), getColumn() + 1, getTileGrid());
                break;
            case WEST:
                move(getRow(), getColumn() - 1, getTileGrid());
                break;
            default:
                //Do Nothing
                break;
        }
    }
}
