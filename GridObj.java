/*
 * 
 */

/**
 *
 * @author patel
 */
public class GridObj {
    
    private int xCoord;
    private int yCoord;
    /*
    0 = path, 1 = wall, 2 = destination
    */
    private int state;
    
    public GridObj(int xCoord, int yCoord, int state){
        
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.state = state;
        
    }//end constructor()
    
    public void setXCoord(int xCoord){this.xCoord = xCoord;}
    public void setYCoord(int yCoord){this.yCoord = yCoord;}
    public void setState(int state){this.state = state;}
    
    public int getXCoord(){return this.xCoord;}
    public int getYCoord(){return this.yCoord;}
    public int getState(){return this.state;}
    
    public String toString(){
        return "x : " + getXCoord() + ", y : " + getYCoord() + " state : " + getState();
    }//end toString()
    
}//end class()
