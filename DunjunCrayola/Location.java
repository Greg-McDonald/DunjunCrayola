
/**
 * Write a description of class Location here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Location
{
    public int row;
    public int column;
    
    public Location(int row, int column)
    {
        this.row = row;
        this.column = column;
    }
    
    public void setLocation(int row, int column)
    {
        this.row = row;
        this.column = column;
    }
    
    public CardinalDirection getDirectionTowards(Location loc)
    {
        if(loc.row == this.row && loc.column == this.column)
            return CardinalDirection.NOT_CARDINAL;
        else if(loc.row < this.row && loc.column == this.column)
            return CardinalDirection.NORTH;
        else if(loc.row > this.row && loc.column == this.column)
            return CardinalDirection.SOUTH;
        else if(loc.row == this.row && loc.column < this.column)
            return CardinalDirection.WEST;
        else if(loc.row == this.row && loc.column > this.column)
            return CardinalDirection.EAST;
        else
            return CardinalDirection.NOT_CARDINAL;
    }
}
