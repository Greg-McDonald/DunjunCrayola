
/**
 * Write a description of class Location here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Location
{
    private int row;
    private int column;
    
    public Location(int row, int column)
    {
        this.row = row;
        this.column = column;
    }
    
    public int getRow(){return row;}
    public int getColumn(){return column;}
    
    public void setRow(int row){this.row = row;}
    public void setColumn(int column){this.column = column;}
    
    public int getX(){return row;}
    public int getY(){return column;}
    
    public void setX(int x){this.row = x;}
    public void setY(int y){this.column = y;}
    
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
