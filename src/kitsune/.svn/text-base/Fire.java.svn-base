package kitsune;

import java.io.Serializable;

/**
 * This class represents the fire
 * @author Guillaume
 */
public class Fire  implements Serializable {

    private static final int size = 20;
    private static final int ignitionValue=14;
    private static final int peakBurn=300;
    private static final int otherBurn=50;


    int[][] fire;
    int[][] timeMap; //keeps track of time for each cell
    Level parent;

    public Fire(Level level){
        this.parent=level;
        this.fire= new int[this.size][this.size];
        this.timeMap= new int[this.size][this.size];
    }

    public void fireStep(){
        for (int i=0;i<this.size;i++){
            for (int j=0; j<this.size;j++){
                this.burn(i,j);
            }
        }
    }

    public int[][] getArray(){
        return this.fire;
    }

    public void increaseFireIntensity(int i, int j){ //this function is called when designing the building
        if (this.fire[i][j]==7){
            this.fire[i][j]=0;
        } else {
            this.fire[i][j]+=1;
        }
    }

    private void burn(int i, int j){
        if (this.fire[i][j]==7){ //if the cell is at the peak of its intensity
            if (this.timeMap[i][j]>=this.peakBurn){ //if its been burning for more than the decided amount of time
                this.fire[i][j]=1; //it's burnt
                this.timeMap[i][j]=0; //we reset its timer
            } else { //else we just increase its counter
                this.timeMap[i][j]+=1;
            }
        } else if (this.fire[i][j]==0){ //if the cell is not burned
            if (neighborPressure(i,j)>=this.ignitionValue){ //if the ignition conditions are met
                this.fire[i][j]=2; //we ignite the cell
            }
        } else if (this.fire[i][j]!=1) { //if the cell is burning
            if (this.timeMap[i][j]>=this.otherBurn){ //if its been burning for long enough
                this.fire[i][j]+=1;
                this.timeMap[i][j]=0;
            } else { //else we just increase its counter
                this.timeMap[i][j]+=1;
            }
        }
    }

    private int neighborPressure(int i, int j){ //returns a numerical value- the higher it is, the more pressure the given cell has to burn
        int pressure=0;

        //straight axis
        if (j!=19 && !this.hasWallRight(j,i))
            pressure+=this.fire[i][j+1];
        
        if (j!=0 && !this.hasWallLeft(j,i))
            pressure+=this.fire[i][j-1];

        if (i!=19 && !this.hasWallDown(j,i))
            pressure+=this.fire[i+1][j];

        if (i!=0 && !this.hasWallUp(j,i))
            pressure+=this.fire[i-1][j];

        //diagonals
        if (j!=0 && i!=0 && !this.hasWallUp(j,i) && !this.hasWallLeft(j,i))
            pressure+=this.fire[i-1][j-1];
        if (j!=19 && i!=19 && !this.hasWallDown(j,i) && !this.hasWallRight(j,i))
            pressure+=this.fire[i+1][j+1];
        if (j!=19 && i!=0 && !this.hasWallUp(j,i) && !this.hasWallRight(j,i))
            pressure+=this.fire[i-1][j+1];
        if (i!=19 && j!=0 && !this.hasWallDown(j,i) && !this.hasWallLeft(j,i))
            pressure+=this.fire[i+1][j-1];

        //DOOR EFFECT - divides by two

        return pressure;
    }

    //wall detectors
   private boolean hasWallRight(int i, int j){
        UndirectedGraph walls = this.parent.getWalls();

        int node=i+(j*this.size); //we first calculate the square's top left node index

        node= node+1; //then the square's top right hand corner node index

        if (walls.edgeExists(node,node+this.size)) //check for a wall
            return true;
        return false;
    }

   private boolean hasWallLeft(int i, int j){
        UndirectedGraph walls = this.parent.getWalls();

        int node=i+(j*this.size); //we first calculate the square's top left node index

        if (walls.edgeExists(node,node+this.size)){ //check for a wall
            return true;}
        return false;
    }

   private boolean hasWallUp(int i, int j){
        UndirectedGraph walls = this.parent.getWalls();

        int node=i+(j*this.size); //we first calculate the square's top left node index

        if (walls.edgeExists(node,node+1)){ //check for a wall
            return true;}
        return false;
    }

   private boolean hasWallDown(int i, int j){
        UndirectedGraph walls = this.parent.getWalls();

        int node=i+(j*this.size); //we first calculate the square's top left node index

        if (walls.edgeExists(node+this.size,node+this.size+1)){ //check for a wall
            return true;}
        return false;
    }

   //door detectors
}
